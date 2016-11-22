package com.yanxing.dao;

import com.yanxing.model.WeiXinHot;
import com.yanxing.util.ConstantValue;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 微信精选
 * Created by lishuangxiang on 2016/11/22.
 */

public interface WeiXinDao {

    /**
     * 获取微信精选
     * @param number 每页数量
     * @param word   关键字搜索
     * @param page   当前第几页
     * @return
     */
    @GET(ConstantValue.WEIXIN_HOT)
    @Headers("apikey:"+ConstantValue.APIKEY)
    Observable<WeiXinHot> getWeiXinHot(@Query("num") int number
            , @Query("word") String word, @Query("page") int page);
}
