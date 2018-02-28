package com.worldsnas.mvvmfirst.app.network;


import com.squareup.moshi.Moshi;
import com.worldsnas.mvvmfirst.app.api.Api;
import com.worldsnas.mvvmfirst.rx.SchedulersFacade;
import com.worldsnas.mvvmfirst.util.AutoValueMoshi_MAdapterFactory;
import com.worldsnas.mvvmfirst.util.EndPoint;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class NetworkModule {

    @Provides
    static Moshi provideMoshi() {
        return new Moshi.Builder().add(AutoValueMoshi_MAdapterFactory.create()).build();
    }

    @Provides
    static MoshiConverterFactory provideMoshiConverterFactory(Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }

    @Provides
    static Retrofit provideRestHelper(OkHttpClient client,
                                      Moshi moshi,
                                      SchedulersFacade schedulers) {
        return new Retrofit.Builder()
                .baseUrl(EndPoint.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    static Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

}
