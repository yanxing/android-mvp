package com.yanxing.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.yanxing.model.api.MovieAPI
import com.yanxing.base.BasePresenter
import com.yanxing.model.Movie
import com.yanxing.networklibrary.RetrofitManage
import com.yanxing.networklibrary.Transformer
import com.yanxing.networklibrary.refresh.PullToRefresh
import com.yanxing.view.MovieView

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
class TopMoviePresenterImpl : TopMoviePresenter, BasePresenter<MovieView>() {

    private lateinit var mLifecycleProvider: LifecycleProvider<ActivityEvent>
    private lateinit var mContext: Context
    private var mPullToRefresh: PullToRefresh? = null


    /**
     * @param lifecycleProvider 用于取消订阅
     * @param pullToRefresh retrofit请求完成或出错，重置刷新组件状态
     */
    fun initPresenter(lifecycleProvider: LifecycleProvider<ActivityEvent>, context: Context, pullToRefresh: PullToRefresh) {
        this.mLifecycleProvider = lifecycleProvider
        this.mContext = context
        this.mPullToRefresh = pullToRefresh
    }

    override fun getTopMovie(index:Int) {
        val count=10
        val start=index*count
        RetrofitManage.getInstance().retrofit.create(MovieAPI::class.java)
                .getTopMovie(start, count)
                .compose(Transformer<Movie>().iOMainNoProgress(mLifecycleProvider))
                .subscribe(object : MovieAbstractObserver<Movie>(mContext, mPullToRefresh) {

                    override fun onCall(t: Movie?) {
                        mIView?.getMovieList(t)
                    }

                })

    }
}