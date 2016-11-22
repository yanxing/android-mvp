package com.yanxing.base;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.yanxing.util.CommonUtil;

import butterknife.ButterKnife;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends RxFragmentActivity {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtil.setStatusBarDarkMode(true, this);
        initImmersionStatus();
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        afterInstanceView();
    }

    /**
     * 子类布局ID
     */
    protected abstract int getLayoutResID();

    /**
     * 实例化控件之后操作
     */
    protected abstract void afterInstanceView();

    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    /**
     * 使用沉浸式状态栏
     */
    public void initImmersionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
