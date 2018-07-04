package it.fabio.boilerplatejetpack.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import it.fabio.boilerplatejetpack.BuildConfig;
import it.fabio.boilerplatejetpack.R;
import it.fabio.boilerplatejetpack.di.AppInjector;
import it.fabio.boilerplatejetpack.error.CrashLibrary;
import it.fabio.boilerplatejetpack.utils.Constants;
import timber.log.Timber;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initApplicationComponent();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private void initApplicationComponent() {
        AppInjector.init(this);
//        applicationComponent = DaggerApplicationComponent
//                .builder()
//                .applicationModule(new ApplicationModule(this.getApplicationContext()))
//                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            CrashLibrary.log(priority, tag, message);

            if(t != null){
                if(priority == Log.ERROR){
                    CrashLibrary.logError(t);
                }
                else if(priority == Log.WARN){
                    CrashLibrary.logWarning(t);
                }
            }

        }
    }
}
