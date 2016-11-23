package com.yanxing.ui;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.yanxing.R;
import com.yanxing.base.BaseActivity;
import com.yanxing.base.BasePresenter;

import butterknife.BindView;

/**
 * 显示微信精选详情，由于只是显示一个网页，逻辑简单，不用mvp方式写
 * Created by lishuangxiang on 2016/11/23.
 */

public class WeiXinHotDetailActivity extends BaseActivity {

    @BindView(R.id.detail)
    WebView mWebView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_wei_xin_hot_detail;
    }

    @Override
    protected void afterInstanceView() {
        String url=getIntent().getStringExtra("url");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(url);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
