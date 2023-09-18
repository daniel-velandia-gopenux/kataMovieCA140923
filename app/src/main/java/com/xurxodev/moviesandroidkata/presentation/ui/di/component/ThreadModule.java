package com.xurxodev.moviesandroidkata.presentation.ui.di.component;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.thread.BackgroundExecutorImpl;
import com.xurxodev.moviesandroidkata.thread.MainThreadImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ThreadModule {

    @Provides
    @Singleton
    MainThread providesMainThread() {
        return new MainThreadImpl();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        return new BackgroundExecutorImpl();
    }
}
