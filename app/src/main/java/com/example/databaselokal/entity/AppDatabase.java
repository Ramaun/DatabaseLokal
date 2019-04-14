package com.example.databaselokal.entity;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {DataSekolah.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract DataSekolahDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                    "dbSekolah").allowMainThreadQueries().build();
        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }
}
