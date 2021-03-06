package com.yanxing.presenter

import android.content.Context
import com.yanxing.networklibrary.AbstractObserver
import com.yanxing.networklibrary.model.BaseModel
import com.yanxing.networklibrary.refresh.PullToRefresh

/**
 *
 * @author 李双祥 on 2018/7/8.
 */
abstract class MovieAbstractObserver<T :BaseModel>(context: Context, pullToRefresh: PullToRefresh?) : AbstractObserver<T>(context,pullToRefresh) {

    /**
     * top250接口返回数据格式不是{"status":1}形式，直接交给调用者处理
     */
    override fun onNext(t:T) {
        onCall(t)
    }

}