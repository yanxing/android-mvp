package com.yanxing.base

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * MVP基础Activity
 * @author 李双祥 on 2018/7/5.
 */
abstract class MVPBaseActivity<V : BaseView, P : BasePresenter<V>> : BaseActivity(), HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        getPresenter()?.attachView(this as V)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
        return frameworkFragmentInjector
    }

    /**
     * 得到子类中的Presenter
     */
    abstract fun getPresenter(): P?

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.detachView()
    }
}