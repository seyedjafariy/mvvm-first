package com.worldsnas.mvvmfirst.app.pref;

import io.reactivex.Observable;

public interface PrefManager {

    Observable<String> getString(String key);

    Observable<Boolean> getBool(String key);

    Observable<Integer> getInt(String key);

    void setString(String key, String value);

    void setBool(String key, boolean value);

    void setInt(String key, int value);
}
