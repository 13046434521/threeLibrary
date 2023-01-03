package com.jtl.router_api;

import android.app.Activity;
import android.content.Context;

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

    public void init(Context application){

    }

//    public void inject(Activity activity){
//        // 1. 通过注册，获取生成的类
//        // 2. 生成的类里有id和名称
//        // 3. 将activity传入辅助类中，for循环生成findviewbyid
//        classMap.get(activity);
//        router$$BindView.init(activity);
//    }

    public void inject(Activity activity){
        try {
            Class clazz = Class.forName(activity.getPackageName()+activity.getLocalClassName()+"$$BindView");
            IBindView bindView = (IBindView) clazz.newInstance();
            bindView.init(activity);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        // 1. 通过注册，获取生成的类
        // 2. 生成的类里有id和名称
        // 3. 将activity传入辅助类中，for循环生成findviewbyid
        classMap.get(activity);
        router$$BindView.init(activity);
    }

    private static class RouterHolder {
        private static final Router ROUTER = new Router();
    }
}
