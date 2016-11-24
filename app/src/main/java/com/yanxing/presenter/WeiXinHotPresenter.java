package com.yanxing.presenter;


import android.support.v7.widget.RecyclerView;

import com.yanxing.base.BasePresenter;
import com.yanxing.dao.WeiXinDao;
import com.yanxing.iview.WeiXinHotView;
import com.yanxing.model.WeiXinHot;
import com.yanxing.util.ConstantValue;
import com.yanxing.util.LogUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public class WeiXinHotPresenter extends BasePresenter<WeiXinHotView> {

    public WeiXinHotPresenter(WeiXinHotView weiXinHotView) {
        this.mView = weiXinHotView;
    }

    /**
     * 加载数据
     * @param word 关键字
     * @param pageSize 每页的数量
     * @param currentPage 当前页
     */
    public void loadData(String word,int pageSize,int currentPage){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WeiXinDao weiXinDao = retrofit.create(WeiXinDao.class);
        weiXinDao.getWeiXinHot(pageSize,word,currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeiXinHot>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("WeiXinHotPresenter",e.toString());
                    }

                    @Override
                    public void onNext(WeiXinHot weiXinHot) {
                       mView.setData(weiXinHot);
                    }
                });
    }

    /**
     * RecyclerView是否滑动到了底部
     * @param recyclerView
     * @return
     */
    public boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null){
            return false;
        }
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
