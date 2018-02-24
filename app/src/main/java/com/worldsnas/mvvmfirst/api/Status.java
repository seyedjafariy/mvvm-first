package com.worldsnas.mvvmfirst.api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Seyed on 2/2/2018.
 * status of the current request
 */

public class Status {

    @Retention(SOURCE)
    @IntDef({
            SUCCESS,
            LOADING,
            FAILED
    })
    public @interface APIStatus {}

    public static final int SUCCESS = 1;
    public static final int LOADING = 2;
    public static final int FAILED = 3;

}
