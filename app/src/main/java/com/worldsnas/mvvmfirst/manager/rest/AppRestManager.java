package com.worldsnas.mvvmfirst.manager.rest;


import com.worldsnas.mvvmfirst.app.api.Api;

import javax.inject.Inject;

public class AppRestManager implements RestManager {

    @Inject
    Api mApi;

    @Inject

    public AppRestManager(Api api) {
        mApi = api;
    }
}
