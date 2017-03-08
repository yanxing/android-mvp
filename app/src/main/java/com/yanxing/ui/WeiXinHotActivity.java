package com.yanxing.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yanxing.R;
import com.yanxing.adapterlibrary.RecyclerViewAdapter;
import com.yanxing.base.BaseActivity;
import com.yanxing.iview.WeiXinHotView;
import com.yanxing.model.WeiXinHot;
import com.yanxing.presenter.WeiXinHotPresenter;
import com.yanxing.util.AppUtil;
import com.yanxing.util.ErrorCodeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;

/**
 * 微信精选列表,网络数据和非UI逻辑在WeiXinHotPresenter里面
 * Created by lishuangxiang on 2016/11/23.
 */
public class WeiXinHotActivity extends BaseActivity<WeiXinHotView, WeiXinHotPresenter>
        implements WeiXinHotView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.ptrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;

    private RecyclerViewAdapter<WeiXinHot.NewslistBean> mRecyclerViewAdapter;
    private List<WeiXinHot.NewslistBean> mNewsList = new ArrayList<>();
    private String mType = "旅行";//内容分类
    /**
     * 下拉刷新
     */
    private boolean mPullDownFresh = true;
    private int mCurrentPage = 1;
    private static final int PAGE_SIZE = 10;
    private static final String[] TYPE = new String[]{"旅行", "摄影", "美文", "美食", "职场", "健康", "美女"};

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_wei_xin_hot;
    }

    @Override
    protected void afterInstanceView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter<WeiXinHot.NewslistBean>(mNewsList,
                R.layout.adapter_wei_xin_hot) {
            @Override
            public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
                holder.setText(R.id.title, mDataList.get(position).getTitle());
                SimpleDraweeView picImg = (SimpleDraweeView) holder.findViewById(R.id.simple_drawee_view);
                picImg.setImageURI(Uri.parse(mDataList.get(position).getPicUrl()));
                holder.setText(R.id.src, getString(R.string.official_account) + mDataList.get(position).getDescription());
                holder.setText(R.id.time, AppUtil.formate(mDataList.get(position).getCtime()));
                //点击事件
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), WeiXinHotDetailActivity.class);
                        intent.putExtra("url", mDataList.get(position).getUrl());
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //下拉刷新
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPullDownFresh = true;
                mPresenter.loadData(mType, PAGE_SIZE, 1);
            }
        });
        mPtrFrameLayout.autoRefresh(true);
        //上拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mPresenter.isSlideToBottom(mRecyclerView)) {
                    mPullDownFresh = false;
                    mPresenter.loadData(mType, PAGE_SIZE, ++mCurrentPage);
                }
            }
        });
    }

    @Override
    public void setData(WeiXinHot weiXinHot) {
        if (ErrorCodeUtil.isErrorSuccess(weiXinHot.getCode())) {
            if (mPullDownFresh) {
                mNewsList.clear();
                mNewsList.addAll(weiXinHot.getNewslist());
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
            } else {
                mNewsList.addAll(weiXinHot.getNewslist());
                mRecyclerViewAdapter.update(mNewsList);
            }
            mPtrFrameLayout.refreshComplete();
        } else {
            showToast(weiXinHot.getMsg());
        }
    }

    @Override
    public Observable.Transformer<WeiXinHot, WeiXinHot> rxLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected WeiXinHotPresenter initPresenter() {
        return new WeiXinHotPresenter(this,getApplicationContext());
    }


    @OnClick({R.id.travel, R.id.shoot, R.id.beauty_passage
            , R.id.beauty_food, R.id.work, R.id.health, R.id.belle})
    public void onClick(View view) {
        mCurrentPage = 1;
        mNewsList.clear();
        switch (view.getId()) {
            case R.id.travel:
                mType = TYPE[0];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.shoot:
                mType = TYPE[1];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.beauty_passage:
                mType = TYPE[2];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.beauty_food:
                mType = TYPE[3];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.work:
                mType = TYPE[4];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.health:
                mType = TYPE[5];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
            case R.id.belle:
                mType = TYPE[6];
                mPresenter.loadData(mType, PAGE_SIZE, mCurrentPage);
                break;
        }
    }
}
