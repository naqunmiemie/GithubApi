package com.yjn.githubapi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.yjn.githubapi.App;
import com.yjn.githubapi.base.BaseAndroidViewModel;
import com.yjn.githubapi.db.UserDao;
import com.yjn.githubapi.model.User;
import com.yjn.githubapi.repository.UserRepository;
import com.yjn.githubapi.util.L;

public class UserAVM extends BaseAndroidViewModel {

    private LiveData<User> user;
    private UserRepository userRepository;
    private static String userName = "getActivity";

    public UserAVM(@NonNull Application application) {
        super(application);
        UserDao userDao = App.getUserDatabase().userDao();
        userRepository = new UserRepository(userDao,this);
        user = userRepository.getUser(userName);
    }


    public LiveData<User>  getUser(){
        return user;
    }

    public void refresh(){
        userRepository.refresh(userName);
    }

    public void refresh(String userName){
        UserAVM.userName = userName;
        userRepository.refresh(userName);
    }
}
