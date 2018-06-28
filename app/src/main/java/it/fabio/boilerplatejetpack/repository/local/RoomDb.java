package it.fabio.boilerplatejetpack.repository.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import it.fabio.boilerplatejetpack.repository.data.User;
import it.fabio.boilerplatejetpack.utils.Constants;

@Database(entities = User.class, version = Constants.DATABASE.DB_VERSION, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    private static RoomDb INSTANCE;

    public static RoomDb getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, Constants.DATABASE.DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public abstract LocalDao localDao();
}
