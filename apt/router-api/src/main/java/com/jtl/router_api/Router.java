package com.jtl.router_api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jtl.router_annotation.Route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 作者:jtl
 * 日期:Created in 2023/1/4 19:46
 * 描述:路由类
 * 更改:
 */
@Route(path = "test")
public
class Router {
    private Router() {
    }

    private Context context;
    ClassHelper classRouterHelper;

    public static Router getInstance(){
        return Router.RouterHolder.ROUTER;
    }

    public void init(Application application){
        context = application.getApplicationContext();
        String path = context.getPackageName()+"."+"Router$$Route";
        classRouterHelper = ClassHelper.newBuilder()
                .setPath(path)
                .compileMethods()
                .newInstance()
                .build();

        Log.d(this.toString(),classRouterHelper.toString());
    }
    public void navigation(Activity activity , String path)  {
        Method method = classRouterHelper.getMethod("inject");
        Object obj = classRouterHelper.newInstance();
        try {
            String clazzPath = (String) method.invoke(obj,path);
            ClassHelper classHelper = ClassHelper.newBuilder()
                    .setPath(clazzPath)
                    .compileClass()
                    .build();

            Intent intent = new Intent(activity,classHelper.getaClass());
            activity.startActivity(intent);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static class RouterHolder {
        private static final Router ROUTER = new Router();
    }
}
