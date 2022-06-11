package com.example.soundwave;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM Song")
    List<Song> getAll();

    @Query("SELECT * FROM Song ORDER BY RANDOM() LIMIT 5;")
    List<Song> getFiveSongs();

    @Insert
    void addSong(Song song);

    @Query("SELECT * FROM Song WHERE title=(:title)")
    Song getSong(String title);

    @Query("SELECT * FROM Song WHERE id=(:id)")
    Song getID(long id);

    @Query("DELETE FROM Song")
    void deleteAll();

}
