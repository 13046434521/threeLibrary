package com.jtl.router_api.router;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 作者:jtl
 * 日期:Created in 2023/1/6 15:01
 * 描述:路由的实际业务处理类。通过反射获取Router$$Route类
 * 更改:
 */

class _Router {
    private final Context currentContext;
    private final ClassHelper classRouterHelper;
    public _Router(Context context) {
        currentContext = context;

        String path = currentContext.getPackageName() + "." + "Router$$Route";
        classRouterHelper = ClassHelper.newBuilder()
                .setPath(path)
                .compileMethods()
                .newInstance()
                .build();
    }


    public Postcard build(String path) {
        String clazzPath = parsePath(path);

        return new Postcard(currentContext,clazzPath);
    }

    private String parsePath(String path){
        Method method = classRouterHelper.getMethod("inject");
        Object obj = classRouterHelper.newInstance();
        String clazzPath = null;
        try {
            clazzPath = (String) method.invoke(obj, path);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return clazzPath;
    }
}
