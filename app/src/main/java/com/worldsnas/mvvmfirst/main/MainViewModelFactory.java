package com.worldsnas.mvvmfirst.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.worldsnas.mvvmfirst.repos.LoadMainReposUseCase;
import com.worldsnas.mvvmfirst.rx.SchedulersFacade;


class MainViewModelFactory implements ViewModelProvider.Factory {

    private final LoadMainReposUseCase mLoadMainReposUseCase;

    private final SchedulersFacade schedulersFacade;

    MainViewModelFactory(LoadMainReposUseCase loadMainReposUseCase,
                         SchedulersFacade schedulersFacade) {

        this.mLoadMainReposUseCase = loadMainReposUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mLoadMainReposUseCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
