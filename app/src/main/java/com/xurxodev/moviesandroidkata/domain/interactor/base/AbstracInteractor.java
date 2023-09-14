package com.xurxodev.moviesandroidkata.domain.interactor.base;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;

public abstract class AbstracInteractor implements Interactor{

    protected Executor threadExecutor;
    protected MainThread mainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstracInteractor(Executor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isMIsRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsCanceled = false;
        mIsRunning = false;
    }

    public void execute() {

        mIsRunning = true;

        threadExecutor.execute(this);
    }
}
