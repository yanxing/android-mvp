package com.yanxing

import android.support.v7.widget.LinearLayoutManager
import com.facebook.drawee.view.SimpleDraweeView
import com.yanxing.base.MVPBaseActivity
import com.yanxing.model.Movie
import com.yanxing.presenter.TopMoviePresenterImpl
import com.yanxing.util.RecyclerViewAdapter
import com.yanxing.util.StatusBarUtil
import com.yanxing.view.MovieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * 列表显示豆瓣电影top排行榜
 * @author 李双祥 on 2018/7/5.
 */
class MainActivity : MVPBaseActivity<MovieView, TopMoviePresenterImpl>(), MovieView{


    private lateinit var mMovieAdapter: RecyclerViewAdapter<Movie.SubjectsBean>
    private var mMovieList = ArrayList<Movie.SubjectsBean>()
    private var mIndex = 0


    override fun getLayoutResID(): Int {
        return R.layout.activity_main
    }

    override fun afterInstanceView() {
        StatusBarUtil.setStatusBarDark6(this)
        StatusBarUtil.setStatusBarDarkIcon(window, true)
        StatusBarUtil.setStatusBarDarkMode(true, this)
        mPresenter?.initPresenter(applicationContext)
        initMovieAdapter()
    }

    private fun initMovieAdapter() {
        mMovieAdapter = object : RecyclerViewAdapter<Movie.SubjectsBean>(mMovieList, R.layout.item_movie) {
            override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
                val simpleDraweeView = holder?.findViewById<SimpleDraweeView>(R.id.image)
                simpleDraweeView?.setImageURI(mDataList[position].images.medium)
                holder?.setText(R.id.name, mDataList[position].title)
                holder?.setText(R.id.average, mDataList[position].rating.average.toString())
                holder?.setText(R.id.director, "导演： " + mDataList[position].directors[0]?.name)
                holder?.setText(R.id.cast, "主演： " + mDataList[position].casts[0]?.name)
                holder?.setText(R.id.time, "上映年份：" + mDataList[position].year)
            }
        }
        swipeToLoadLayout.initSwipeToLoadLayout(this)
        swipeToLoadLayout.setOnLoadMoreListener(this)
        swipeToLoadLayout.setOnRefreshListener(this)
        swipe_target.layoutManager = LinearLayoutManager(this)
        swipe_target.adapter = mMovieAdapter
        swipeToLoadLayout.isRefreshing = true
    }

    override fun createPresenter(): TopMoviePresenterImpl {
        return TopMoviePresenterImpl()
    }

    override fun getMovieList(movie: Movie?) {
        if (movie != null) {
            if (mIndex == 0) {
                mMovieList.clear()
            }
            mMovieList.addAll(movie.subjects)
            mMovieAdapter.update(mMovieList)
        }
    }

    override fun onRefresh() {
        mIndex = 0
        mPresenter?.getTopMovie(mIndex)
    }

    override fun onLoadMore() {
        mIndex++
        mPresenter?.getTopMovie(mIndex)
    }

    override fun refreshComplete() {
        swipeToLoadLayout.refreshComplete()
    }


    public override fun onPause() {
        Fresco.getImagePipeline().pause()
        super.onPause()
    }

    public override fun onResume() {
        Fresco.getImagePipeline().resume()
        super.onResume()
    }

}