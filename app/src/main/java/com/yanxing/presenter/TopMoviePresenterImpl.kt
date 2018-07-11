package com.yanxing.presenter

import android.content.Context
import com.yanxing.model.api.MovieAPI
import com.yanxing.base.BasePresenter
import com.yanxing.model.Movie
import com.yanxing.networklibrary.RetrofitManage
import com.yanxing.networklibrary.Transformer
import com.yanxing.view.MovieView
import javax.inject.Inject

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
class TopMoviePresenterImpl() : TopMoviePresenter, BasePresenter<MovieView>() {

    private lateinit var mContext: Context

    @Inject
    constructor(context: Context) : this() {
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