package com.xurxodev.moviesandroidkata.thread;

import android.os.Handler;
import android.os.Looper;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;

public class MainThreadImpl implements MainThread {

    private Handler mainThread;

    public MainThreadImpl() {
        this.mainThread = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
            mainThread.post(runnable);
    }
}
