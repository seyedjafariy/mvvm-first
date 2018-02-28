package com.worldsnas.mvvmfirst.app.di;

import com.worldsnas.mvvmfirst.app.network.NetworkModule;
import com.worldsnas.mvvmfirst.app.pref.AppPrefManager;
import com.worldsnas.mvvmfirst.app.pref.PrefManager;
import com.worldsnas.mvvmfirst.manager.rest.AppRestManager;
import com.worldsnas.mvvmfirst.manager.rest.RestManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module(includes = {NetworkModule.class, SharedPrefModule.class})
abstract class AppModule {

    @Binds
    abstract RestManager bindRestManager(AppRestManager appRestManager);

    @Binds
    abstract PrefManager bindPrefManager(AppPrefManager appRestManager);

    @Provides
    static CompositeDisposable provideDisposables(){
        return new CompositeDisposable();
    }
}
