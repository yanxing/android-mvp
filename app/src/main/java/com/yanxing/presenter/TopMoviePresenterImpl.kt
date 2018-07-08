package com.yanxing.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.yanxing.model.api.MovieAPI
import com.yanxing.base.BasePresenter
import com.yanxing.model.Movie
import com.yanxing.networklibrary.AbstractObserver
import com.yanxing.networklibrary.RetrofitManage
import com.yanxing.networklibrary.Transformer
import com.yanxing.view.MovieView

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
class TopMoviePresenterImpl() :TopMoviePresenter , BasePresenter<MovieView>() {

    private lateinit var mLifecycleProvider: LifecycleProvider<ActivityEvent>
    private lateinit var mContext: Context


    constructor(lifecycleProvider: LifecycleProvider<ActivityEvent>,context: Context) : this() {
            this.mLifecycleProvider=lifecycleProvider
        this.mContext=context
    }

    override fun getTopMovie(start: Int, count: Int) {
        RetrofitManage.getInstance().retrofit.create(MovieAPI::class.java)
                .getTopMovie(start,count)
                .compose(Transformer<Movie>().iOMainNoProgress(mLifecycleProvider))
                .subscribe(object : MovieAbstractObserver<Movie>(mContext){

                    override fun onCall(t: Movie?) {
                        mIView?.getMovieList(t)
                    }

                })

    }
}