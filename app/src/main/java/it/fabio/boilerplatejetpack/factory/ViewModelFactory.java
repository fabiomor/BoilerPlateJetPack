package it.fabio.boilerplatejetpack.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import it.fabio.boilerplatejetpack.modules.main.MainViewModel;
import it.fabio.boilerplatejetpack.repository.local.LocalDataRepository;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    LocalDataRepository localDataRepository;

    @Inject
    public ViewModelFactory(LocalDataRepository localDataRepository) {
        this.localDataRepository = localDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(localDataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
