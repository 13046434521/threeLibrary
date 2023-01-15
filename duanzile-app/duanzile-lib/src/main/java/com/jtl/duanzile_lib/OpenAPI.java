package com.jtl.duanzile_lib;

import static com.jtl.duanzile_lib.OpenAPI.BasePath;
import static com.jtl.duanzile_lib.OpenAPI.Home_Attention_List;
import static com.jtl.duanzile_lib.OpenAPI.Home_Attention_Recommend;
import static com.jtl.duanzile_lib.OpenAPI.Home_Latest;
import static com.jtl.duanzile_lib.OpenAPI.Home_Picture;
import static com.jtl.duanzile_lib.OpenAPI.Home_Recommend;
import static com.jtl.duanzile_lib.OpenAPI.Home_Search;
import static com.jtl.duanzile_lib.OpenAPI.Home_Text;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@StringDef({BasePath,Home_Attention_List,Home_Attention_Recommend,
        Home_Search,Home_Latest,Home_Picture,Home_Recommend,Home_Text})
public @interface OpenAPI {
    // BaseUrl
    String BasePath ="http://tools.cretinzp.com/jokes";

    // 获取关注的用户发布的段子列表
    String Home_Attention_List ="/home/attention/list";

    // 获取主页的推荐关注数据
    String Home_Attention_Recommend ="/home/attention/recommend";

    // 搜索段子
    String Home_Search ="/home/jokes/search";

    //获取主页的最新列表数据
    String Home_Latest ="/home/latest";

    // 获取主页的纯图片列表数据
    String Home_Picture ="/home/pic";

    // 获取主页的推荐列表数据
    String Home_Recommend ="/home/recommend";

    // 获取主页的纯文列表数据
    String Home_Text ="/home/text";
}
