package com.worldsnas.mvvmfirst.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.worldsnas.mvvmfirst.app.api.Status;

import static com.worldsnas.mvvmfirst.app.api.Status.FAILED;
import static com.worldsnas.mvvmfirst.app.api.Status.LOADING;
import static com.worldsnas.mvvmfirst.app.api.Status.SUCCESS;

public class MainReposResponse {

    @Status.APIStatus
    public final int status;

    @Nullable
    public final String data;

    @Nullable
    public final Throwable error;

    private MainReposResponse(@Status.APIStatus int status, @Nullable String data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static MainReposResponse loading() {
        return new MainReposResponse(LOADING, null, null);
    }

    public static MainReposResponse success(@NonNull String data) {
        return new MainReposResponse(SUCCESS, data, null);
    }

    public static MainReposResponse error(@NonNull Throwable error) {
        return new MainReposResponse(FAILED, null, error);
    }
}
