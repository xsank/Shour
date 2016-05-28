package org.nerdboy.chatbot.utils;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class ClassUtil {

    public static Class<?> getClass(String path) {
        ClassLoader classLoader = ClassUtil.class.getClassLoader();
        Class clazz = null;
        try {
            clazz = classLoader.loadClass(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Object getObject(String path) {
        Class clazz = getClass(path);
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
