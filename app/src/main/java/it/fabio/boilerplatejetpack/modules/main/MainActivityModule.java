package it.fabio.boilerplatejetpack.modules.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import it.fabio.boilerplatejetpack.base.BaseActivityModule;
import it.fabio.boilerplatejetpack.di.FragmentBuildersModule;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
