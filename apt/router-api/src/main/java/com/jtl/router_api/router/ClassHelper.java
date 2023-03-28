package com.jtl.router_api.router;

import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
/**
 * 作者:jtl
 * 日期:Created in 2023/1/6 01:46
 * 描述:反射辅助类，建造者模式
 * 更改:
 */
public class ClassHelper {
    private final String path;
    private final Class aClass;
    private final Constructor constructor;
    private final HashMap<String, Method> methodHashMap;
    private final Object instance;

    public ClassHelper(String path, Class aClass, Constructor constructor, HashMap<String, Method> methodHashMap, Object instance) {
        this.path = path;
        this.aClass = aClass;
        this.constructor = constructor;
        this.methodHashMap = methodHashMap;
        this.instance = instance;
    }

    public static ClassBuilder newBuilder(){
        return new ClassBuilder();
    }

    public String getPath() {
        return path;
    }

    public Class getaClass() {
        return aClass;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public Method getMethod(String methodName) {
        return methodHashMap.get(methodName);
    }

    public Object newInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "ClassHelper{" +
                "path='" + path + '\'' +
                ", aClass=" + aClass +
                ", constructor=" + constructor +
                ", methodHashMap=" + methodHashMap +
                ", instance=" + instance +
                '}';
    }

    public static class ClassBuilder{
        private String path;
        private Class aClass;
        private Constructor constructor;
        private Object instance;

        private HashMap<String, Method> methodHashMap = new HashMap<>();

        public ClassBuilder setPath(String path){
            this.path = path;
            return this;
        }

        public ClassBuilder compileClass() {
            if (TextUtils.isEmpty(path))
                return null;
            try {
                aClass = Class.forName(path);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return this;
        }

        public ClassBuilder compileConstructor(Class<?>... parameterTypes) {
            try {
                if (aClass==null){
                    compileClass();
                }
                constructor = aClass.getConstructor(parameterTypes);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return this;
        }

        public ClassBuilder compileMethods() {
            if (aClass==null){
                compileClass();
            }
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method:methods){
                methodHashMap.put(method.getName(),method);
            }

            return this;
        }
        public ClassBuilder newInstance() {
            try {
                if (constructor==null){
                    compileConstructor();
                }
                instance = constructor.newInstance();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }

            return this;
        }
        public ClassHelper build(){
            return new ClassHelper(path,aClass,constructor,methodHashMap,instance);
        }
    }
}
