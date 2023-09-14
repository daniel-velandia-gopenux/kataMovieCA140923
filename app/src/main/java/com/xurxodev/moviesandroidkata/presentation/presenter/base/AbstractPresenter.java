package com.xurxodev.moviesandroidkata.presentation.presenter.base;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;

public abstract class AbstractPresenter implements Presenter {

    protected Executor threadExecutor;
    protected MainThread mainThread;

    public AbstractPresenter(Executor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }
}
