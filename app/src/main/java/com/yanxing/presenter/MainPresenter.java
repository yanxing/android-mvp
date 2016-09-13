package com.yanxing.presenter;


import android.util.Log;

import com.yanxing.dao.DouBanDao;
import com.yanxing.iview.MainView;
import com.yanxing.model.DouBan;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        this.mView = mainView;
    }

    public void loadData(){
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DouBanDao douBanDao = retrofit.create(DouBanDao.class);
        douBanDao.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DouBan>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainPresenter",e.toString());
                    }

                    @Override
                    public void onNext(DouBan douBan) {
                        Log.d("MainPresenter",douBan.toString());
                       mView.setData(douBan);
                    }
                });
    }
}
