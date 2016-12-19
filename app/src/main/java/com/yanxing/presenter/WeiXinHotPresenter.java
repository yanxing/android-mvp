package com.yanxing.presenter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.yanxing.base.BasePresenter;
import com.yanxing.dao.WeiXinDao;
import com.yanxing.iview.WeiXinHotView;
import com.yanxing.model.WeiXinHot;
import com.yanxing.util.ConstantValue;
import com.yanxing.util.FileUtil;
import com.yanxing.util.LogUtil;
import com.yanxing.util.OkHttpOffLineCache;


import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public class WeiXinHotPresenter extends BasePresenter<WeiXinHotView> {

    private File mFile = new File(FileUtil.getStoragePath() + ConstantValue.CACHE);
    private Cache mCache;
    private Object mRx;

    public WeiXinHotPresenter(WeiXinHotView weiXinHotVie,Object rxFragmentOrActivity) {
        this.mView = weiXinHotVie;
        this.mRx=rxFragmentOrActivity;
        if (!(mRx instanceof RxFragmentActivity)&&!(mRx instanceof RxFragment)){
            throw new IllegalArgumentException("rxFragmentOrActivity is RxFragment or RxFragmentActivity");
        }
        if (!mFile.exists()) {
            mFile.mkdirs();
        }
        mCache = new Cache(mFile, ConstantValue.MAX_DISK_CACHE_VERYLOW_SIZE);
    }

    /**
     * 加载数据
     *
     * @param word        关键字
     * @param pageSize    每页的数量
     * @param currentPage 当前页
     */
    public void loadData(String word, int pageSize, int currentPage, Context context) {
        //离线缓存
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new OkHttpOffLineCache(context))
                .cache(mCache).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        WeiXinDao weiXinDao = retrofit.create(WeiXinDao.class);
        Observable.Transformer<WeiXinHot,WeiXinHot> transformer;
        if (mRx instanceof RxFragmentActivity){
            transformer=((RxFragmentActivity)mRx).bindToLifecycle();
        }else{
            transformer=((RxFragment)mRx).bindToLifecycle();
        }
        weiXinDao.getWeiXinHot(pageSize, word, currentPage)
                .compose(transformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeiXinHot>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("WeiXinHotPresenter", e.toString());
                    }

                    @Override
                    public void onNext(WeiXinHot weiXinHot) {
                        mView.setData(weiXinHot);
                    }
                });
    }

    /**
     * RecyclerView是否滑动到了底部
     *
     * @param recyclerView
     * @return
     */
    public boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

}
