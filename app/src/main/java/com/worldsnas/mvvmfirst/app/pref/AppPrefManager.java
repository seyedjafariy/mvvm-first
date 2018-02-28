package com.worldsnas.mvvmfirst.app.pref;


import com.f2prateek.rx.preferences2.RxSharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppPrefManager implements PrefManager {

    @Inject
    RxSharedPreferences mRxPref;

    @Inject
    public AppPrefManager() {
    }

    @Override
    public Observable<String> getString(String key) {
        return mRxPref.getString(key).asObservable();
    }

    @Override
    public Observable<Boolean> getBool(String key) {
        return mRxPref.getBoolean(key).asObservable();
    }

    @Override
    public Observable<Integer> getInt(String key) {
        return mRxPref.getInteger(key).asObservable();
    }

    @Override
    public void setString(String key, String value) {
        mRxPref.getString(key).set(value);
    }

    @Override
    public void setBool(String key, boolean value) {
        mRxPref.getBoolean(key).set(value);
    }

    @Override
    public void setInt(String key, int value) {
        mRxPref.getInteger(key).set(value);
    }
}
