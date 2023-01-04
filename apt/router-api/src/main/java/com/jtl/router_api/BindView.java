package com.jtl.router_api;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * 作者:jtl
 * 日期:Created in 2023/1/4 19:46
 * 描述:静态内部类 BindView 对应BindView注解
 * 更改:
 */
public class BindView {
    private BindView(){}

    public static BindView getInstance(){
        return BindViewHolder.BIND_VIEW;
    }

    //BindView 自动生成findViewById
    public void inject(Activity activity){
        try {
            Class clazz = Class.forName(activity.getPackageName()+"."+activity.getLocalClassName()+"$$BindView");
            Constructor constructor = clazz.getConstructor();

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method:methods){
                Log.w("inject",method.getName()+" --- "+activity.getClass());
            }

            Object bindView = constructor.newInstance((Object) null);
            constructor.setAccessible(true);

            Method bindViewMethod = clazz.getDeclaredMethod("init",activity.getClass());
            bindViewMethod.invoke(bindView,activity);


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static class BindViewHolder {
        private static final BindView BIND_VIEW = new BindView();
    }
}
