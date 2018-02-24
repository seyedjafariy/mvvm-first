package com.worldsnas.mvvmfirst.main;

import com.worldsnas.mvvmfirst.repos.LoadMainReposUseCase;
import com.worldsnas.mvvmfirst.repos.MainReposRepository;
import com.worldsnas.mvvmfirst.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

/**
 * Define MainActivity-specific dependencies here.
 */
@Module
public class MainModule {

    @Provides
    MainReposRepository provideMainRepository() {
        return new MainReposRepository();
    }

    @Provides
    MainViewModelFactory provideLobbyViewModelFactory(LoadMainReposUseCase loadMainReposUseCase,
                                         SchedulersFacade schedulersFacade) {
        return new MainViewModelFactory(loadMainReposUseCase, schedulersFacade);
    }
}
