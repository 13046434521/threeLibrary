package com.jtl.router_api;

/**
 * 作者:jtl
 * 日期:Created in 2023/1/4 19:46
 * 描述:
 * 更改:
 */

class Router {

    private Router() {
    }
    public static Router getInstance(){
        return Router.RouterHolder.ROUTER;
    }

    private static class RouterHolder {
        private static final Router ROUTER = new Router();
    }
}
