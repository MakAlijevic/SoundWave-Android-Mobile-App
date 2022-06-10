package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Song song = songDao.getSong("Charlie Puth - Light Switch.mp3");
        if (song != null) {
            songDao.deleteAll();
        }
        loadSongs();

    }

    public void SignUpButton(View V) {
        Intent registrationIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registrationIntent);
    }

    public void LoginButton(View V) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void loadSongs() {
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        Song song1 = new Song("Charlie Puth - Light Switch.mp3", "Charlie Puth", "03:40", "Light Switch", R.drawable.edsheeran);
        songDao.addSong(song1);

        Song song2 = new Song("Charlie Puth - We Dont Talk Anymore.mp3", "Charlie Puth", "03:03", "We Dont Talk Anymore", R.drawable.kendrick);
        songDao.addSong(song2);

        Song song3 = new Song("Coldplay X BTS - My Universe.mp3", "Coldplay", "03:03", "My Universe", R.drawable.asitwas);
        songDao.addSong(song3);

    }
}