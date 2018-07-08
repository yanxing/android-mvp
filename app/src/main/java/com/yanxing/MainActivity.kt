package com.yanxing

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.yanxing.base.MVPBaseActivity
import com.yanxing.model.Movie
import com.yanxing.presenter.TopMoviePresenterImpl
import com.yanxing.util.RecyclerViewAdapter
import com.yanxing.view.MovieView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
class MainActivity : MVPBaseActivity<MovieView, TopMoviePresenterImpl>() {

    override fun createPresenter(lifecycleProvider: LifecycleProvider<ActivityEvent>,context: Context): TopMoviePresenterImpl {
        return TopMoviePresenterImpl()
    }

    private lateinit var mMovieAdapter: RecyclerViewAdapter<Movie.SubjectsBean>
    private var mMovieList:ArrayList<Movie.SubjectsBean>?=null


    override fun getLayoutResID(): Int {
        return R.layout.activity_main
    }

    override fun afterInstanceView() {
        initMovieAdapter()
        mPresenter
    }

    private fun initMovieAdapter(){
        mMovieAdapter=object :RecyclerViewAdapter<Movie.SubjectsBean>(mMovieList,R.layout.item_movie){
            override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

            }
        }
        swipeToLoadLayout.initSwipeToLoadLayout(this)
        swipe_target.layoutManager=LinearLayoutManager(this)
        swipe_target.adapter=mMovieAdapter
    }



}