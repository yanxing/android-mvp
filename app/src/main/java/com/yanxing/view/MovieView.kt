package com.yanxing.view

import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.yanxing.base.BaseView
import com.yanxing.model.Movie
import com.yanxing.networklibrary.refresh.PullToRefresh

/**
 * LifecycleProvider 用于取消RxJava订阅；PullToRefresh retrofit请求完成或出错，重置刷新组件状态
 * @author 李双祥 on 2018/7/5.
 */
interface MovieView : BaseView ,OnLoadMoreListener, OnRefreshListener,PullToRefresh, LifecycleProvider<ActivityEvent> {

    fun getMovieList(movie: Movie?)
}