package com.worldsnas.mvvmfirst.repos;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadMainReposUseCase implements LoadMainUseCase {

    private MainReposRepository mMainReposRepository;

    @Inject
    LoadMainReposUseCase(MainReposRepository mainReposRepository) {
        mMainReposRepository = mainReposRepository;
    }



    @Override
    public Single<String> execute() {
        return mMainReposRepository.getRepos();
    }
}
