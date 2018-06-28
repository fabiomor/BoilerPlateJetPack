package it.fabio.boilerplatejetpack.repository.local;

import android.arch.lifecycle.LiveData;

import java.util.List;

import it.fabio.boilerplatejetpack.repository.data.User;

public class RoomDbManager implements PersistenceRepository {

    private RoomDb roomDb;

    public RoomDbManager(RoomDb roomDb) {
        this.roomDb = roomDb;
    }

    @Override
    public void insertUsers(List<User> user) {
        roomDb.localDao().insertAll(user);
    }

    @Override
    public LiveData<List<User>> getUsers() {
        return roomDb.localDao().getAll();
    }
}
