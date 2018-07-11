package com.yanxing.presenter

import android.content.Context
import com.yanxing.model.api.MovieAPI
import com.yanxing.base.BasePresenter
import com.yanxing.model.Movie
import com.yanxing.networklibrary.RetrofitManage
import com.yanxing.networklibrary.Transformer
import com.yanxing.view.MovieView

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
class TopMoviePresenterImpl : TopMoviePresenter, BasePresenter<MovieView>() {

    private lateinit var mContext: Context


    /**
     * @param lifecycleProvider 用于取消订阅
     * @param pullToRefresh retrofit请求完成或出错，重置刷新组件状态
     */
    fun initPresenter(context: Context) {
        this.mContext = context
    }

    override fun getTopMovie(index:Int) {
        val count=10
        val start=index*count
        RetrofitManage.getInstance().retrofit.create(MovieAPI::class.java)
                .getTopMovie(start, count)
                .compose(Transformer<Movie>().iOMainNoProgress(mIView))
                .subscribe(object : MovieAbstractObserver<Movie>(mContext, mIView) {

                    override fun onCall(t: Movie?) {
                        mIView?.getMovieList(t)
                    }

                })

    }
}