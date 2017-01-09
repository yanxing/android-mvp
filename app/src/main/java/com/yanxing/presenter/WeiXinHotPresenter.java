package com.yanxing.presenter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yanxing.base.BasePresenter;
import com.yanxing.base.RetrofitManage;
import com.yanxing.dao.WeiXinDao;
import com.yanxing.iview.WeiXinHotView;
import com.yanxing.model.WeiXinHot;
import com.yanxing.util.LogUtil;


import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public class WeiXinHotPresenter extends BasePresenter<WeiXinHotView> {

    private Context mContext;

    public WeiXinHotPresenter(WeiXinHotView weiXinHotVie,Context context) {
        this.mView = weiXinHotVie;
        this.mContext=context;
    }

    /**
     * 加载数据
     *
     * @param word        关键字
     * @param pageSize    每页的数量
     * @param currentPage 当前页
     */
    public void loadData(String word, int pageSize, int currentPage) {
        WeiXinDao weiXinDao = RetrofitManage.getInstance().getRetrofit(mContext).create(WeiXinDao.class);
        weiXinDao.getWeiXinHot(pageSize, word, currentPage)
                .compose(mView.rxLifecycle())
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
