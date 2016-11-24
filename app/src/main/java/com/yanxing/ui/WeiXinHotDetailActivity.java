package com.yanxing.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.yanxing.R;
import com.yanxing.base.BaseActivity;
import com.yanxing.base.BasePresenter;
import com.yanxing.util.DownloadImageUtil;

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
        final String url = getIntent().getStringExtra("url");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(url);
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WebView.HitTestResult result = ((WebView) view).getHitTestResult();
                int type = result.getType();
                //图片
                if (type == WebView.HitTestResult.IMAGE_TYPE) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WeiXinHotDetailActivity.this);
                    String save[] = new String[]{"保存图片到相册"};
                    builder.setItems(save, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DownloadImageUtil.getInstance().downloadImage(result.getExtra(),
                                    new DownloadImageUtil.DownloadListener() {
                                        @Override
                                        public void finish() {
                                            showToast("已保存到相册");
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
