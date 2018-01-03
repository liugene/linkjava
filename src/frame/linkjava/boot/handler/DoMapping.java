package frame.linkjava.boot.handler;

import java.util.HashMap;
import java.util.Map;

public class DoMapping
{

    private String name;

    private String classname;

    private Map<String,String> resultMapping = new HashMap<String,String>();

    //获得关联关系
    public String getResult(String resultName)
    {
        return resultMapping.get(resultName);
    }

    //添加关联关系
    public void addResult(String resultName,String result)
    {
        resultMapping.put(resultName,result);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setClassname(String classname)
    {
        this.classname = classname;
    }

    public String getClassname()
    {
        return classname;
    }

}
