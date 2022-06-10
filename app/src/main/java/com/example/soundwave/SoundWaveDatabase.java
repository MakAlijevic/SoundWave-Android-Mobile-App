package com.example.soundwave;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {User.class,Song.class},version = 5, exportSchema = false)
public abstract class SoundWaveDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract SongDao songDao();
    public static SoundWaveDatabase INSTANCE = null;


    public static SoundWaveDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, SoundWaveDatabase.class, "soundwave-database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }


}
