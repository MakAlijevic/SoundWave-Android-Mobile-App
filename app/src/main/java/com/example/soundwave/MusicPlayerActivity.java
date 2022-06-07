package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageButton btnPlayButton;
    private ImageButton btnPauseButton;
    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btnPlayButton = findViewById(R.id.playButton);
        btnPauseButton = findViewById(R.id.nextButton);

        btnPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });

        btnPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseSong();
            }
        });
    }
    public void playSong() {
        final String audioURL="https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(audioURL);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(this, "You currently playing emk", Toast.LENGTH_LONG).show();
    }
    public void pauseSong(){

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }
}