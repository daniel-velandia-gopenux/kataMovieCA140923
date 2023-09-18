package com.xurxodev.moviesandroidkata.thread;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;

public class BackgroundExecutorImpl implements Executor {

    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
