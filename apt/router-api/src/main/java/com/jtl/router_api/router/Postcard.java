package com.jtl.router_api.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * 作者:jtl
 * 日期:Created in 2023/1/6 14:58
 * 描述:数据保存类。以及跳转业务逻辑
 * 更改:
 */

public class Postcard {

    private final String clazzPath;
    private final Bundle mDataBundle = new Bundle();
    private final Context currentContext;
    public Postcard(Context context,String clazzPath) {
        this.clazzPath = clazzPath;
        this.currentContext = context;

    }
    public Postcard withData(String name,Object parameter){
        addData(name,parameter);
        return this;
    }

    private void addData(String name, Object parameter){
        if (parameter instanceof Integer){
            mDataBundle.putInt(name, (Integer) parameter);
        }else if (parameter instanceof Long){
            mDataBundle.putLong(name, (Long) parameter);
        }
        else if  (parameter instanceof Byte){
            mDataBundle.putByte(name, (Byte) parameter);
        }
        else if  (parameter instanceof Float){
            mDataBundle.putFloat(name, (Float) parameter);
        }
        else if  (parameter instanceof Double){
            mDataBundle.putDouble(name, (Double) parameter);
        }
        else if  (parameter instanceof Boolean){
            mDataBundle.putBoolean(name, (Boolean) parameter);
        }
        else if  (parameter instanceof Character){
            mDataBundle.putChar(name, (Character) parameter);
        }
        else if  (parameter instanceof Short){
            mDataBundle.putShort(name, (Short) parameter);
        }
        else if(parameter instanceof String){
            mDataBundle.putString(name, (String) parameter);
        }else if(parameter instanceof Bundle){
            mDataBundle.putBundle(name, (Bundle) parameter);
        }else{
            throw new IllegalArgumentException("没有匹配类选项，请包装成Bundle再使用");
        }
    }

    public void navigation(){
        ClassHelper classHelper = ClassHelper.newBuilder()
                .setPath(clazzPath)
                .compileClass()
                .build();

        Intent intent = new Intent();

        // Non activity, need FLAG_ACTIVITY_NEW_TASK
        if (!(currentContext instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtras(mDataBundle);
        intent.setClass(currentContext, classHelper.getaClass());
        currentContext.startActivity(intent);
    }
}
