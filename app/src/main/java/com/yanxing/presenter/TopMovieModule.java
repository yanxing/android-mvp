package com.yanxing.presenter;

import android.content.Context;

import com.yanxing.ActivityScoped;
import com.yanxing.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 开始用kotlin写，非抽象方法需要静态的，用companion object后报错提示静态类需要注解@Module,添加注解后，依然报错，
 * 改为java正常。若使用kotlin可参考https://www.jianshu.com/p/8a9cc465bced
 * @author 李双祥 on 2018/7/11.
 */
@Module
public abstract class TopMovieModule {


    @ActivityScoped
    @Binds
    abstract TopMoviePresenter topMoviePresenter(TopMoviePresenterImpl topMoviePresenter);

    @Provides
    @ActivityScoped
    static Context provideContext(MainActivity mainActivity){
        return mainActivity;
    }
}
