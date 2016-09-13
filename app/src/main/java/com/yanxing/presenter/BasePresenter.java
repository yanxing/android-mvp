package com.yanxing.presenter;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public abstract class BasePresenter<T> {

    public T mView;

    /**
     * 退出销毁持有的Activity
     */
    public void onDestroy() {
        this.mView = null;
    }

}
