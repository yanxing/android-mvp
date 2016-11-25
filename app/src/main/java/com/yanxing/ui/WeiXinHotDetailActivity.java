package com.yanxing.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yanxing.R;
import com.yanxing.base.BaseActivity;
import com.yanxing.base.BasePresenter;
import com.yanxing.util.DownloadImageUtil;

import butterknife.BindView;

/**
 * 精选详情，由于只是显示一个网页，逻辑简单，避免过度设计，不用mvp方式写
 * Created by lishuangxiang on 2016/11/23.
 */

public class WeiXinHotDetailActivity extends BaseActivity {

    @BindView(R.id.detail)
    WebView mWebView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_wei_xin_hot_detail;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void afterInstanceView() {
        final String url = getIntent().getStringExtra("url");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(url);

        //长按某个图片可以保存到相册,附加的（有些图片挺好看的）
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WebView.HitTestResult result = ((WebView) view).getHitTestResult();
                if (result.getType() == WebView.HitTestResult.IMAGE_TYPE) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WeiXinHotDetailActivity.this);
                    String save[] = new String[]{getString(R.string.save_img_to_photo)};
                    builder.setItems(save, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DownloadImageUtil.getInstance().downloadImage(result.getExtra(),
                                    new DownloadImageUtil.DownloadListener() {
                                        @Override
                                        public void finish() {
                                            showToast(getString(R.string.yi_save_photo));
                                        }
                                    });
                        }
                    }).show();
                }
                return true;
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
