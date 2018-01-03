package frame.linkjava.boot.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Do
{
    public String SUCCESS = "success";
    public String ERROR = "error";

    //接受请求
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
