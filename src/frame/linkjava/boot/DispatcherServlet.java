package frame.linkjava.boot;

import frame.linkjava.boot.handler.Do;
import frame.linkjava.boot.handler.DoManager;
import frame.linkjava.boot.handler.DoMapping;
import frame.linkjava.boot.handler.DoMappingManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet
{
    public DoMappingManager doMappingManager = null;

    public DispatcherServlet()
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        try{
            DoMapping dm = DoMappingManager.getDoMappings(this.getDoName(request));
            //获取classname
            Do d = DoManager.createDo(dm.getClassname());

            //执行业务逻辑
            String result = d.execute(request,response);

            if(result == null){
                response.sendError(404,"not found result");
            }

            //重定向
            response.sendRedirect(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        this.doGet(request,response);
    }

    //获取请求名
    private String getDoName(HttpServletRequest request)
    {
        //获取uri
        String uri = request.getRequestURI();
        //获取上下文路径
        String contextPath = request.getContextPath();
        //获取doPath
        String doPath = uri.substring(contextPath.length());
        //获取doName
        String doName;
        doName = doPath.substring(1,doPath.lastIndexOf('.')).trim();
        return doName;
    }

    //webserver自动加载框架配置文件
    public void init(ServletConfig config) throws ServletException
    {
        //读取配置文件
        String configStr = config.getInitParameter("linkjava");

        String[] fileName = null;

        if(configStr == null || configStr.isEmpty()) {
            fileName = new String[]{"linkjava.xml"};
        } else {
            fileName = configStr.split(",");
        }

        //根据配置文件产生
        this.doMappingManager = new DoMappingManager(fileName);

    }

}
