package com.jtl.router_api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private Router (){};
    private Map<Class,Object> classMap = new HashMap();
    public Router$$BindView router$$BindView = new Router$$BindView();
    public static Router getInstance(){
        return RouterHolder.ROUTER;
    }

    public void putClass(String path,Class clazz){
        try {
            Class cl = Class.forName(path);
            Object o = cl.newInstance();

            classMap.put(clazz,o);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    public void inject(Activity activity){
        try {
            Class clazz = Class.forName(activity.getPackageName()+"."+activity.getLocalClassName()+"$$BindView");
            Constructor constructor = clazz.getConstructor();

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method:methods){
                Log.w("inject",method.getName()+" --- "+activity.getClass());
            }

            Object bindView = constructor.newInstance(null);
            constructor.setAccessible(true);

            Method bindViewMethod = clazz.getDeclaredMethod("init",activity.getClass());
            bindViewMethod.invoke(bindView,activity);


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static class RouterHolder {
        private static final Router ROUTER = new Router();
    }
}
