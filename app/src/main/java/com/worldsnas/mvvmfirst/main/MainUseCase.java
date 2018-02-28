package com.worldsnas.mvvmfirst.main;


import com.worldsnas.mvvmfirst.app.pref.PrefManager;
import com.worldsnas.mvvmfirst.manager.rest.RestManager;

import javax.inject.Inject;

public class MainUseCase {

    @Inject
    RestManager mRestManager;

    @Inject
    PrefManager mPrefManager;

    @Inject
    public MainUseCase() {
    }
}
