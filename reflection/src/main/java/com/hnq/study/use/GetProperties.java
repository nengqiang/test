package com.hnq.study.use;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 利用反射和配置文件，可以使应用程序更新时，对源码无需进行任何修改
 * 只需要将新类发送给客户端，并修改配置文件即可
 *
 * @author henengqiang
 * @date 2019/04/04
 */
public class GetProperties {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        GetProperties getIt = new GetProperties();
        // 通过反射获取Class对象
        Class stuClass = Class.forName(getIt.getValue("className"));
        // 获取show()方法
        Method method = stuClass.getMethod(getIt.getValue("methodName"));
        // 调用show()方法
        method.invoke(stuClass.getConstructor().newInstance());
    }

    /**
     * 根据key从配置文件中获取value
     * @param key   key
     * @return      value
     */
    private String getValue(String key) throws IOException {
        // 获取配置文件对象
        Properties pro = new Properties();
        // 获取输入流
        FileReader in = new FileReader(this.getClass().getResource("/pro.properties").getPath());
        // 将流加载到配置文件中
        pro.load(in);
        in.close();
        return pro.getProperty(key);
    }


}
