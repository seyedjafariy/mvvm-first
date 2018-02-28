package com.worldsnas.mvvmfirst.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.worldsnas.mvvmfirst.rx.SchedulersFacade;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


class MainViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    MainUseCase mMainReposUseCase;

    @Inject
    SchedulersFacade schedulersFacade;

    @Inject
    CompositeDisposable mDisposable;

    @Inject
    MainViewModelFactory() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mMainReposUseCase, schedulersFacade, mDisposable);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
