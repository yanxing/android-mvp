package com.yanxing.base;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public abstract class BasePresenter<T> {

    public T mView;

    /**
     * 解绑
     */
    public void onDestroy() {
        this.mView = null;
    }

}
