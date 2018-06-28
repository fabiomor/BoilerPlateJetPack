package it.fabio.boilerplatejetpack.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager implements PreferenceRepository {

    protected SharedPreferences sharedPreferences;
    protected Context context;

    public SharedPreferencesManager(SharedPreferences sharedPreferences, Context context) {
        this.sharedPreferences = sharedPreferences;
        this.context = context;
    }
}
