package com.jtl.router_api;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class Router$$BindView {

    public Map<View,Integer> idmap =new HashMap<>();


    public void init(Activity activity){
        idmap.forEach((view, value) -> {
            view = activity.findViewById(value);
            Log.w("Router$$BindView",view+"");
            // activity.key = activity.findViewById(value);
        });

    }

}
