package com.yjn.githubapi.net.server;

import com.hjq.http.config.IRequestServer;

public class GithubServer implements IRequestServer {
    @Override
    public String getHost() {
        return "https://api.github.com/";
    }
}
