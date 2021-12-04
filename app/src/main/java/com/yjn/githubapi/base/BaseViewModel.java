package com.yjn.githubapi.base;

import android.annotation.SuppressLint;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel implements LifecycleOwner {

    @SuppressLint("StaticFieldLeak")
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    public BaseViewModel() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }
}
