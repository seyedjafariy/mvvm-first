package com.worldsnas.mvvmfirst.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.worldsnas.mvvmfirst.model.MainReposResponse;
import com.worldsnas.mvvmfirst.repos.LoadMainUseCase;
import com.worldsnas.mvvmfirst.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

class MainViewModel extends ViewModel {

    private final MainUseCase mMainReposUseCase;

    private final SchedulersFacade schedulersFacade;

    private final CompositeDisposable mDisposable;

    private final MutableLiveData<MainReposResponse> response = new MutableLiveData<>();

    MainViewModel(MainUseCase mainUseCase,
                  SchedulersFacade schedulersFacade,
                  CompositeDisposable disposable) {
        this.mMainReposUseCase = mainUseCase;
        this.schedulersFacade = schedulersFacade;
        mDisposable = disposable;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    MutableLiveData<MainReposResponse> response() {
        return response;
    }

    private void loadRepos(LoadMainUseCase loadMainUseCase) {
        mDisposable.add(loadMainUseCase.execute()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(MainReposResponse.loading()))
                .subscribe(
                        greeting -> response.setValue(MainReposResponse.success(greeting)),
                        throwable -> response.setValue(MainReposResponse.error(throwable))
                )
        );
    }
}
