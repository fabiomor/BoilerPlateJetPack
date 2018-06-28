package it.fabio.boilerplatejetpack.repository.local;

import android.arch.lifecycle.LiveData;

import java.util.List;

import it.fabio.boilerplatejetpack.repository.DataRepository;
import it.fabio.boilerplatejetpack.repository.data.User;

public interface PersistenceRepository extends DataRepository {

    void insertUsers(List<User> user);

    LiveData<List<User>> getUsers();
}
