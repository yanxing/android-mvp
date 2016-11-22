package com.yanxing;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yanxing.adapterlibrary.RecyclerViewAdapter;
import com.yanxing.base.BaseActivity;
import com.yanxing.iview.WeiXinHotView;
import com.yanxing.model.WeiXinHot;
import com.yanxing.presenter.WeiXinHotPresenter;
import com.yanxing.util.ErrorCodeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * mvp demo
 */
public class WeiXinHotActivity extends BaseActivity<WeiXinHotView, WeiXinHotPresenter>
        implements WeiXinHotView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RecyclerViewAdapter<WeiXinHot.NewslistBean> mRecyclerViewAdapter;
    private List<WeiXinHot.NewslistBean> mNewsList = new ArrayList<>();

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterInstanceView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter<WeiXinHot.NewslistBean>(mNewsList,
                R.layout.adapter_wei_xin_hot) {
            @Override
            public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
                holder.setText(R.id.title, mNewsList.get(position).getTitle());
                SimpleDraweeView picImg = (SimpleDraweeView) holder.findViewById(R.id.simple_drawee_view);
                picImg.setImageURI(Uri.parse(mNewsList.get(position).getPicUrl()));
            }
        };
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPresenter.loadData("旅游", 10, 1);
    }

    @Override
    public void setData(WeiXinHot weiXinHot) {
        if (ErrorCodeUtil.isErrorSuccess(weiXinHot.getCode())) {
            mNewsList = weiXinHot.getNewslist();
            mRecyclerViewAdapter.update(mNewsList);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
        }
    }

    @Override
    protected WeiXinHotPresenter initPresenter() {
        return new WeiXinHotPresenter(this);
    }
}
