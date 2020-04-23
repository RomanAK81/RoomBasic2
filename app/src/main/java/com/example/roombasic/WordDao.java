package com.example.roombasic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface WordDao{

    @Insert
    void add(Word... word);
    @Update
    void update(Word... word);
    @Delete
    void delete(Word... word);

    @Query("DELETE FROM Word")
    void deleteAll();

    @Query("SELECT * FROM Word ORDER BY ID DESC")
    LiveData<List<Word>> getAllWordsLiveData();



}
