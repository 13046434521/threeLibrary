package com.jtl.router_api;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Router (){};

    private Map<String,Class> classMap = new HashMap();
    public Router$$BindView router$$BindView = new Router$$BindView();
    public static Router getInstance(){
        return RouterHoler.ROUTER;
    }

    public void init(Context context){

    }

    public void putClass(String path,Class clazz){
        classMap.put(path,clazz);
    }

    public void inject(Activity activity){
        // 1. 通过注册，获取生成的类
        // 2. 生成的类里有id和名称
        // 3. 将activity传入辅助类中，for循环生成findviewbyid
        router$$BindView.init(activity);
    }

    private static class RouterHoler{
        private static final Router ROUTER = new Router();
    }
}
