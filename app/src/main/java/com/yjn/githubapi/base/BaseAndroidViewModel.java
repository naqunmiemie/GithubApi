package com.yjn.githubapi.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;
import com.yjn.githubapi.App;
import com.yjn.githubapi.db.UserDao;
import com.yjn.githubapi.model.User;
import com.yjn.githubapi.net.api.GetUserApi;

public class BaseAndroidViewModel extends AndroidViewModel implements LifecycleOwner {

    @SuppressLint("StaticFieldLeak")
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
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
