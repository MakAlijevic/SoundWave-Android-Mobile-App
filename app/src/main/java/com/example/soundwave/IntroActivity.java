package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SongDao songDao=SoundWaveDatabase.getInstance(this).songDao();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        loadSongs();


    }
    public void SignUpButton(View V)
    {
        Intent registrationIntent = new Intent(this,RegistrationActivity.class);
        startActivity(registrationIntent);
    }

    public void LoginButton(View V)
    {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }

    public void loadSongs(){
        SongDao songDao=SoundWaveDatabase.getInstance(this).songDao();
        Song song1=new Song("Charlie Puth - Light Switch.mp3","03:03");
        songDao.addSong(song1);

        Song song2=new Song("Charlie Puth - We Dont Talk Anymore.mp3","03:03");
        songDao.addSong(song2);

        Song song3=new Song("Coldplay X BTS - My Universe.mp3","03:03");
        songDao.addSong(song3);

        Song song4=new Song("Dove Cameron - Boyfriend.mp3","03:03");
        songDao.addSong(song4);

        Song song5=new Song("Dua Lipa - Levitating.mp3","03:03");
        songDao.addSong(song5);

        Song song6=new Song("Dua Lipa - New Rules.mp3","03:03");
        songDao.addSong(song6);

        Song song7=new Song("Ed Sheeran - Bad Habits.mp3","03:03");
        songDao.addSong(song7);

        Song song8=new Song("Ed Sheeran - Shivers.mp3","03:03");
        songDao.addSong(song8);
    }
}