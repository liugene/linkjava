package frame.linkjava.boot.handler;

public class DoManager
{

    public static Do createDo(String className)
    {

        //生产class对象
        Class clazz = null;

        try{
            //判断当前线程是否有该Do线程运行
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(clazz == null){
            try{
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //生产do实例
        Do d = null;
        try {
            d = (Do)clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //返回
        return d;

    }

}
