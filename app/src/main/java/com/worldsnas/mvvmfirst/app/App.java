package com.worldsnas.mvvmfirst.app;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.worldsnas.mvvmfirst.app.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

import static com.worldsnas.mvvmfirst.BuildConfig.DEBUG;


public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().application(this).build().inject(this);

        initLeakCanary();

        initAnalytics();

        Fresco.initialize(this);

        initTimber();
    }

    private void initTimber() {

        if (DEBUG)
            Timber.plant(new Timber.DebugTree());
//        else
//            Timber.plant(new CrashlyticsLogExceptionTree());
    }

    private void initAnalytics(){

    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
