package com.worldsnas.mvvmfirst.repos;

import io.reactivex.Single;


public interface LoadMainUseCase {

    Single<String> execute();
}
