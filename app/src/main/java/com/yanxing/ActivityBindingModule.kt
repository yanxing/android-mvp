package com.yanxing

import com.yanxing.presenter.TopMovieModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * @author 李双祥 on 2018/7/11.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TopMovieModule::class])
    abstract fun mainActivity(): MainActivity
}