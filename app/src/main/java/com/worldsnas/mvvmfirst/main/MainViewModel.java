package com.worldsnas.mvvmfirst.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.worldsnas.mvvmfirst.repos.LoadMainReposUseCase;
import com.worldsnas.mvvmfirst.repos.LoadMainUseCase;
import com.worldsnas.mvvmfirst.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

class MainViewModel extends ViewModel {


    private final LoadMainReposUseCase mLoadMainReposUseCase;

    private final SchedulersFacade schedulersFacade;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<MainReposResponse> response = new MutableLiveData<>();

    MainViewModel(LoadMainReposUseCase loadMainReposUseCase,
                  SchedulersFacade schedulersFacade) {
        this.mLoadMainReposUseCase = loadMainReposUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    void loadMainRepos() {
        loadRepos(mLoadMainReposUseCase);
    }

    MutableLiveData<MainReposResponse> response() {
        return response;
    }

    private void loadRepos(LoadMainUseCase loadMainUseCase) {
        disposables.add(loadMainUseCase.execute()
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
