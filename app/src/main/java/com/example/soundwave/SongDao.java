package com.example.soundwave;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDao {


    @Query("SELECT * FROM Song")
    List<Song> getAll();

    @Insert
    void addSong(Song song);

    @Query("SELECT * FROM Song WHERE title=(:title)")
    Song getSong(String title);

    @Query("DELETE FROM Song")
    void deleteAll();

}
