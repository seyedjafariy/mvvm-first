package com.worldsnas.mvvmfirst.di;

import android.content.Context;

import com.worldsnas.mvvmfirst.App;

import dagger.Binds;
import dagger.Module;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(App application) ;
}
