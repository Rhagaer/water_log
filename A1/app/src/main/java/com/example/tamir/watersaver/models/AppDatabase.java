package com.example.tamir.watersaver.models;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Entry.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();

    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = buildDatabaseInstance(context);
        }

        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "entries").allowMainThreadQueries().build();
    }

    public void cleanUp() {
        appDatabase = null;
    }
}
