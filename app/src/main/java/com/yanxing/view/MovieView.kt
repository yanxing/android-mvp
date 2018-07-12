package com.yanxing.view

import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.yanxing.base.BaseView
import com.yanxing.model.Movie
import com.yanxing.networklibrary.refresh.PullToRefresh

/**
 * PullToRefresh retrofit请求完成或出错，重置刷新组件状态
 * @author 李双祥 on 2018/7/5.
 */
interface MovieView : BaseView , OnLoadMoreListener, OnRefreshListener, PullToRefresh{

    fun getMovieList(movie: Movie?)
}