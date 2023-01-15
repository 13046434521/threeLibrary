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

import com.jtl.duanzile_lib.bean.HomeBean;
import com.jtl.duanzile_lib.bean.SearchBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServer {

    @POST(OpenAPI.Home_Latest)
    Call<Observable<HomeBean>> home();

    @POST(OpenAPI.Home_Attention_List)
    Call<Observable<HomeBean>> homeAttentionList();

    @POST(OpenAPI.Home_Attention_Recommend)
    Call<Observable<HomeBean>> homeAttentionRecommend();

    @POST(OpenAPI.Home_Search)
    Call<Observable<SearchBean>> homeSearch();

    @POST(OpenAPI.Home_Picture)
    Call<Observable<HomeBean>> homePicture();

    @POST(OpenAPI.Home_Recommend)
    Call<Observable<HomeBean>> homeRecommend();

    @POST(OpenAPI.Home_Text)
    Call<Observable<HomeBean>> homeText();

}