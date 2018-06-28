package it.fabio.boilerplatejetpack.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigator;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule<T extends AppCompatActivity> {

    @Provides
    public Activity provideActivity(T activity) { return activity; }

    @Provides
    public FragmentManager provideFragmentManager(T activity) { return activity.getSupportFragmentManager(); }

    @Provides
    public Navigator provideNavigator(T activity) { return new ActivityNavigator(activity); }

    @Provides
    public LifecycleOwner provideLifeCycleOwner(T activity) {
        return activity;
    }
}
