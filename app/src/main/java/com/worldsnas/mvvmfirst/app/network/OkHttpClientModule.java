package com.worldsnas.mvvmfirst.app.network;

import com.worldsnas.mvvmfirst.app.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

import static com.worldsnas.mvvmfirst.BuildConfig.DEBUG;
import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

@Module
public class OkHttpClientModule {
    private static final String CACHE_CONTROL = "Cache-Control";

    @Provides
    @Named("cache_flag")
    static boolean isCacheEnabled() {
        return false;
//        return true;
    }

    @Provides
    @Named("cache_file")
    static File provideCacheFile(App app) {
        return new File(app.getCacheDir(), "http-cache");
    }

    @Provides
    @Named("cache")
    static Cache provideCache(@Named("cache_file") File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }

    @Provides
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));

        httpLoggingInterceptor.setLevel(DEBUG ? HEADERS : NONE);

        return httpLoggingInterceptor;
    }

    @Provides
    @Named("online_temp_cache")
    static Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            // re-write response header to force use of cache
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(2, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    @Provides
    @Singleton
    static OkHttpClient provideClient(@Named("cache_flag") boolean cacheEnabled,
                                      @Named("cache") Cache cache,
                                      OfflineCacheInterceptor offlineCacheInterceptor,
                                      @Named("online_temp_cache") Interceptor onlineTempCache,
                                      HttpLoggingInterceptor loggingInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)// Set connection timeout
                .readTimeout(10, TimeUnit.SECONDS)// Read timeout
                .writeTimeout(10, TimeUnit.SECONDS)// Write timeout
                .addInterceptor(loggingInterceptor);

        if (cacheEnabled)
            builder.addNetworkInterceptor(onlineTempCache)// Add a custom cache interceptor （ Explain later ）， Note that it needs to be used here. .addNetworkInterceptor
                    .addInterceptor(offlineCacheInterceptor)
                    .cache(cache);// Add cache

        return builder.build();
    }
}

