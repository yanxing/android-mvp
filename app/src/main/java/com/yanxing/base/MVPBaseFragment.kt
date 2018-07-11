package com.yanxing.base

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * MVP基础Fragment
 * @author 李双祥 on 2018/7/9.
 */
abstract class MVPBaseFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment() , HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        getPresenter()?.attachView(this as V)
        super.onCreate(savedInstanceState)
    }

    /**
     * 得到子类中的Presenter
     */
    abstract fun getPresenter(): P?

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.detachView()
    }
}