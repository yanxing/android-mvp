package com.yanxing

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *
 * @author 李双祥 on 2018/7/11.
 */
@Singleton
@Component(modules = [ActivityBindingModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): AppComponent.Builder

        fun build(): AppComponent
    }
}