package com.jtl.router_api.router;

import android.app.Application;
import android.content.Context;


/**
 * 作者:jtl
 * 日期:Created in 2023/1/4 19:46
 * 描述:路由类
 * 更改:
 */
public class Router {
    private Router() {
    }

    private _Router _mRouter;

    public static Router getInstance() {
        return Router.RouterHolder.ROUTER;
    }

    public void init(Application application) {

        _mRouter = new _Router(application);
    }

    public Postcard build(String path){
        return _mRouter.build(path);
    }


    private static class RouterHolder {
        private static final Router ROUTER = new Router();
    }
}
