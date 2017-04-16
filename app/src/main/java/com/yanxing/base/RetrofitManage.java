package com.yanxing.base;

import android.content.Context;

import com.yanxing.util.ConstantValue;
import com.yanxing.util.FileUtil;
import com.yanxing.util.OkHttpOffLineCache;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 配置缓存和服务器地址
 * Created by lishuangxiang on 2016/12/26.
 */
public class RetrofitManage {

    private Cache mCache;
    private Retrofit.Builder mBuilder;

    private RetrofitManage() {
        File file = new File(FileUtil.getStoragePath() + ConstantValue.CACHE);
        if (!file.exists()) {
            file.mkdirs();
        }
        mCache = new Cache(file, ConstantValue.MAX_DISK_CACHE_VERYLOW_SIZE);
        mBuilder=new Retrofit.Builder()
                .baseUrl(ConstantValue.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static RetrofitManage getInstance() {
        return SingletonHolder.retrofitManage;
    }

    private static class SingletonHolder {
        private static final RetrofitManage retrofitManage = new RetrofitManage();
    }

    public Retrofit getRetrofit(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new OkHttpOffLineCache(context))
                .cache(mCache)
                .build();
        return getRetrofit(client);
    }

    private Retrofit getRetrofit(OkHttpClient okHttpClient){
        return mBuilder.client(okHttpClient).build();
    }
}
