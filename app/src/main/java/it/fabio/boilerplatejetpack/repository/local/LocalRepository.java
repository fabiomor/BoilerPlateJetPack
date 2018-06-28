package it.fabio.boilerplatejetpack.repository.local;

import android.arch.lifecycle.LiveData;

import java.util.List;

import it.fabio.boilerplatejetpack.repository.data.User;

public class LocalRepository implements LocalDataRepository {

    protected PersistenceRepository persistenceRepository;
    protected PreferenceRepository preferenceRepository;

    public LocalRepository(PersistenceRepository persistenceRepository, PreferenceRepository preferenceRepository) {
        this.persistenceRepository = persistenceRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public void insertUsers(List<User> user) {
        persistenceRepository.insertUsers(user);
    }

    @Override
    public LiveData<List<User>> getUsers() {
        return persistenceRepository.getUsers();
    }
}
