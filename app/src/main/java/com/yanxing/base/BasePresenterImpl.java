package com.yanxing.base;

/**
 * @author 李双祥 on 2017/11/20.
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter {

    protected T mBaseView;

    @Override
    public void onDestroy() {
        mBaseView=null;
    }
}
