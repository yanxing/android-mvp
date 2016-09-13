package com.yanxing;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.yanxing.presenter.BasePresenter;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends RxFragmentActivity {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=initPresenter();
    }

    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
