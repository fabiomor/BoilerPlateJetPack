package it.fabio.boilerplatejetpack.modules.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;

import it.fabio.boilerplatejetpack.repository.data.User;
import it.fabio.boilerplatejetpack.repository.local.LocalDataRepository;

public class MainViewModel extends ViewModel {

    private LocalDataRepository localDataRepository;

    public MainViewModel(LocalDataRepository localDataRepository) {
        this.localDataRepository = localDataRepository;
    }

    public LiveData<List<User>> getUsers(){
        return localDataRepository.getUsers();
    }
}
