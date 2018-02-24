package com.worldsnas.mvvmfirst.repos;

import io.reactivex.Single;

public class MainReposRepository {

    Single<String> getRepos(){

        // TODO: 2/2/2018 to be replaced with a real request
        return Single.just("test repo");
    }
}
