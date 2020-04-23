package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static volatile WordDatabase instance;

    static synchronized  WordDatabase getInstance(Context context) {
        if (instance==null){
            instance = Room.databaseBuilder(context, WordDatabase.class, "Word_Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


    public abstract WordDao getWordDao();
}
