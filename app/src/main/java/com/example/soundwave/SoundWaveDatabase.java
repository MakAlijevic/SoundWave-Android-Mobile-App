package com.example.soundwave;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1, exportSchema = false)
public abstract class SoundWaveDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public static SoundWaveDatabase INSTANCE = null;

    public static SoundWaveDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, SoundWaveDatabase.class, "soundwave-database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


}
