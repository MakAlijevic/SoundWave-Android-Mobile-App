package com.example.soundwave;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String length;

    public Song(String title, String length) {
        this.title = title;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
