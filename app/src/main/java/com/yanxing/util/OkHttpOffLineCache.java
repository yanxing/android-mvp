package com.yanxing.util;

import android.content.Context;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp离线缓存，无网络时读取缓存，有网络时使用最新数据
 * Created by lishuangxiang on 2016/11/25.
 */

public class OkHttpOffLineCache implements okhttp3.Interceptor {

    private Context mContext;

    public OkHttpOffLineCache(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        //无网络，超时时间为1周
        if (!NetworkStateUtil.isNetworkConnected(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            response = chain.proceed(request);
            int maxStale = 60 * 60 * 24 * 7;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        } else {
            // 有网络时,不使用缓存
            response = chain.proceed(request);
            response.newBuilder()
                    .header("Cache-Control", "public,no-cache")
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        }
        return response;
    }
}
