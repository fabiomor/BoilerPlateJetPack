package it.fabio.boilerplatejetpack.modules.main;

import dagger.Component;
import it.fabio.boilerplatejetpack.application.ApplicationComponent;
import it.fabio.boilerplatejetpack.scope.PerActivity;

@PerActivity
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent { }