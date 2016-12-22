package com.yanxing.base;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public abstract class BasePresenter<T> {

    public T mView;

    /**
     * 销毁持有的View，看到一种说法：说不是任何情况下Activity的onDestory方法都会被调用，故对View进行弱引用
     */
    public void onDestroy() {
        this.mView = null;
    }

}
