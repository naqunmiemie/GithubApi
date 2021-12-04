package com.yjn.githubapi;

import android.app.Application;
import android.view.Gravity;

import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.request.HttpRequest;
import com.hjq.toast.ToastUtils;
import com.tencent.mmkv.MMKV;
import com.yjn.githubapi.db.UserDatabase;
import com.yjn.githubapi.net.model.RequestHandler;
import com.yjn.githubapi.net.server.GithubServer;
import com.yjn.githubapi.util.L;

import okhttp3.OkHttpClient;

public class App extends Application {
    private static UserDatabase userDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        userDatabase = UserDatabase.getInstance(this);

        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.BOTTOM,0,150);

//        ToastUtils.init(this);
        MMKV.initialize(this);

        // 网络请求框架初始化
        IRequestServer server = new GithubServer();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                //.setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void interceptArguments(HttpRequest<?> httpRequest, HttpParams params, HttpHeaders headers) {
                        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    }
                })
                // 设置请求重试次数
                .setRetryCount(1)
                // 设置请求重试时间
                .setRetryTime(2000)
                // 添加全局请求参数
//                .addParam("token", "yjn")
                .into();
    }

    public static UserDatabase getUserDatabase(){
        return userDatabase;
    }
}
