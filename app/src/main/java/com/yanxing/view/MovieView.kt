package com.yanxing.view

import com.yanxing.base.BaseView
import com.yanxing.model.Movie

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
interface MovieView : BaseView {

    fun getMovieList(movie: Movie?)
}