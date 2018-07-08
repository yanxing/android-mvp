package com.yanxing.model.api

import com.yanxing.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author 李双祥 on 2018/7/5.
 */
interface MovieAPI {

    /**
     * 获取top电影
     * @param start
     * @param count
     */
    @GET("top250")
    fun getTopMovie(@Query("start") start: Int, @Query("count") count: Int): Observable<Movie>
}