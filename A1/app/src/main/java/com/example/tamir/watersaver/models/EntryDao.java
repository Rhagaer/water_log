package com.example.tamir.watersaver.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM entry")
    List<Entry> getAll();


    @Query("SELECT * from entry where uid=:uid")
    Entry findByUid(int uid);

    @Insert()
    void insertEntry(Entry entry);

}
