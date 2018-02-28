package com.worldsnas.mvvmfirst.app.di;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.worldsnas.mvvmfirst.app.App;

import dagger.Module;
import dagger.Provides;

@Module
class SharedPrefModule {

    @Provides
    static SharedPreferences provideSharedPrefs(App context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    static RxSharedPreferences provideRXPreference(SharedPreferences preferences){
        return RxSharedPreferences.create(preferences);
    }
}

