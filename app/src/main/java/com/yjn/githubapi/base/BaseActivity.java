package com.yjn.githubapi.base;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.http.listener.OnHttpListener;

public class BaseActivity extends AppCompatActivity implements OnHttpListener<Object> {
    @Override
    public void onSucceed(Object result) {

    }

    @Override
    public void onFail(Exception e) {

    }
}
