package it.fabio.boilerplatejetpack.modules.main;

import dagger.Component;
import it.fabio.boilerplatejetpack.application.ApplicationComponent;
import it.fabio.boilerplatejetpack.scope.PerFragment;

@PerFragment
@Component(dependencies = ApplicationComponent.class)
public interface MainFragmentComponent {
//    void inject(MainFragment mainFragment);
}