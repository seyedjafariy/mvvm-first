package com.worldsnas.mvvmfirst.app.network;


import android.support.annotation.NonNull;

import com.worldsnas.mvvmfirst.app.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineCacheInterceptor implements Interceptor {

    @Inject
    App mApp;

    @Inject
    public OfflineCacheInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtil.isNetworkAvailable(mApp)) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        return chain.proceed(request);
    }
}

