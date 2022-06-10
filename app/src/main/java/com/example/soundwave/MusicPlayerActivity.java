package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageButton btnPlayButton;
    private ImageButton btnPauseButton;
    private MediaPlayer mediaPlayer = null;
    private TextView songName, length;
    private ImageView picture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        String title = getIntent().getStringExtra("title");
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        Song song = songDao.getSong(title);

        btnPlayButton = findViewById(R.id.playButton1);
        btnPauseButton = findViewById(R.id.nextButton);

        songName=findViewById(R.id.music_player_title);
        length=findViewById(R.id.music_player_length);
        picture=findViewById(R.id.music_player_picture);

        songName.setText(song.getArtist() + " - " + song.getSongName());
        length.setText(song.getLength());
        picture.setImageResource(song.getPictureID());


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

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String title = getIntent().getStringExtra("title");
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        Song song = songDao.getSong(title);
        AssetFileDescriptor afd;

        try {
            afd = getApplicationContext().getAssets().openFd(song.getTitle());
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
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