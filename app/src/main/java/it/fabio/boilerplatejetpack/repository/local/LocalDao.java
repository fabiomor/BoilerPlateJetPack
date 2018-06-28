package it.fabio.boilerplatejetpack.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import it.fabio.boilerplatejetpack.repository.data.User;
import it.fabio.boilerplatejetpack.utils.DateConverter;

@Dao
@TypeConverters(DateConverter.class)
public interface LocalDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Insert
    void insertAll(List<User> users);

    @Delete
    void delete(User user);
}
