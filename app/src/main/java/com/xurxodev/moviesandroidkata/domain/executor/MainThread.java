package com.xurxodev.moviesandroidkata.domain.executor;

public interface MainThread {

    void post(final Runnable runnable);
}
