package frame.linkjava.boot.handler;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DoMappingManager
{

    //保存所有的domapping
    private static Map<String,DoMapping> doMappings = new HashMap<String,DoMapping>();

    //加载DoMapping配置文件
    public DoMappingManager(){}

    public static void setDoMappings(String doName,DoMapping doMappings)
    {
        DoMappingManager.doMappings.put(doName,doMappings);
    }

    public static DoMapping getDoMappings(String doName) throws Exception
    {
        DoMapping dm = null;
        if(!(doName == null || doName.isEmpty()))
        {
            dm = DoMappingManager.doMappings.get(doName);
        }

        if(dm == null){
            throw new Exception("config not found in the linkjava.xml");
        }

        return dm;

    }

    public DoMappingManager(String[] XmlFileNames)
    {
        for(String XmlFileName : XmlFileNames){
            encodeXml(XmlFileName);
        }
    }

    //初始化解析框架配置文件
    private void encodeXml(String XmlFileName)
    {
        try{
            //判断文件是否为空
            if(XmlFileName == null || XmlFileName.isEmpty()){
                throw new Exception("XmlFile not exists");
            }

            //输入流用于加载配置文件
            InputStream is = this.getClass().getResourceAsStream("/"+XmlFileName);

            //读取Xml文件
            Document doc = new SAXReader().read(is);

            //获取根节点
            Element root = doc.getRootElement();

            for (Iterator<Element> it = root.elementIterator("action"); it.hasNext();) {
                Element actionChild = it.next();
                // do something
                //遍历获取do节点
                for(Iterator<Element> dochild = actionChild.elementIterator("do");dochild.hasNext();){
                    Element doElement = dochild.next();
                    DoMapping dm = new DoMapping();
                    //获取do节点的name值
                    dm.setName(doElement.attributeValue("name"));
                    //获取do节点的class值
                    dm.setClassname(doElement.attributeValue("class"));
                    //遍历dochild节点
                    for(Iterator<Element> result = doElement.elementIterator("result"); result.hasNext();){
                        Element resultElement = result.next();
                        //获取name和value值
                        String resultName = resultElement.attributeValue("name");
                        String resultValue = resultElement.getText();
                        //判断数据是否为空
                        if(resultName == null || "".equals(resultName) || resultName.isEmpty()){
                            resultName = "success";
                        }
                        //将每个封装好的result添加到DoMapping中
                        dm.addResult(resultName,resultValue);
                    }
                    //将DoMapping存放在DoMappings中
                    doMappings.put(dm.getName(),dm);
                }
            }
            //获取root子节点
//            Element actionChild = (Element)root.get"action");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
