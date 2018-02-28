package com.worldsnas.mvvmfirst.app.di;

import com.worldsnas.mvvmfirst.main.MainActivity;
import com.worldsnas.mvvmfirst.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    // Add bindings for other sub-components here
}
