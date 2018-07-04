package it.fabio.boilerplatejetpack.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import it.fabio.boilerplatejetpack.modules.main.MainFragment;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeProjectFragment();
}