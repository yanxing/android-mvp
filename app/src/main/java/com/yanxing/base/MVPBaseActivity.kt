package com.yanxing.base

import android.content.Context
import android.os.Bundle
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent


/**
 * MVP基础Activity
 * @author 李双祥 on 2018/7/5.
 */
abstract class MVPBaseActivity<V : BaseView, P : BasePresenter<V>> : BaseActivity() {

    protected var mPresenter: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = createPresenter(this,applicationContext)
        mPresenter?.attachView(this as V)
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建Presenter,lifecycleProvider用于防止Rxjava内存泄露
     */
    abstract fun createPresenter(lifecycleProvider: LifecycleProvider<ActivityEvent>,context: Context): P

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
}