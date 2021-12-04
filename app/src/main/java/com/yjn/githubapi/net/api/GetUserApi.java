package com.yjn.githubapi.net.api;

import com.hjq.http.config.IRequestApi;

public final class GetUserApi implements IRequestApi {

    private String userId;
    @Override
    public String getApi() {
        return "users/"+userId;
    }

    public GetUserApi setUserId(String userId){
        this.userId = userId;
        return this;
    }
}
