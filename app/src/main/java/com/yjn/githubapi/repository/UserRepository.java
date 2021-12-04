package com.yjn.githubapi.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;
import com.yjn.githubapi.db.UserDao;
import com.yjn.githubapi.model.User;
import com.yjn.githubapi.net.api.GetUserApi;
import com.yjn.githubapi.util.L;
import com.yjn.githubapi.viewmodel.UserAVM;

public class UserRepository {
    private String TAG = this.getClass().getName();
    private UserDao userDao;
    private UserAVM userAVM;

    public UserRepository(UserDao userDao,UserAVM userAVM){
        this.userDao = userDao;
        this.userAVM = userAVM;
    }

    public LiveData<User> getUser(final String login){
        L.d("getUser:"+login);
        refresh(login);
        return userDao.getUserByLogin();
    }

    public void refresh(String login) {
        EasyHttp.get(userAVM)
                .api(new GetUserApi().setUserId(login))
                .request(new OnHttpListener<User>() {
                    @Override
                    public void onSucceed(User result) {
                        insertUser(result);
                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                });
    }

    private void insertUser(final User user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteAll();
                userDao.insertUser(user);
            }
        });
    }
}
