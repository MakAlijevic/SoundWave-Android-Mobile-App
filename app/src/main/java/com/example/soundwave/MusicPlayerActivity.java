package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageButton btnPlayButton,btnPauseButton,btnNextButton,btnPreviousButton;
    private MediaPlayer mediaPlayer = null;
    private TextView songName, length;
    private ImageView picture;
    private AssetFileDescriptor afd;
    private int currentlength;
    private long id;
    private long startingid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        id = getIntent().getLongExtra("id",0);
        startingid=id;
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        Song song = songDao.getID(id);
        btnPlayButton = findViewById(R.id.playButton1);
        btnPauseButton= findViewById(R.id.pauseButton1);
        btnNextButton=findViewById(R.id.nextButton);
        btnPreviousButton=findViewById(R.id.previousButton);

        songName=findViewById(R.id.music_player_title);
        length=findViewById(R.id.music_player_length);
        picture=findViewById(R.id.music_player_picture);

        songName.setText(song.getArtist() + " - " + song.getSongName());
        length.setText(song.getLength());
        picture.setImageResource(song.getPictureID());

        List<Song> songs = songDao.getAll();

        playSong(id);
        btnPlayButton.setVisibility(View.INVISIBLE);



        btnPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong(id);
                btnPlayButton.setVisibility(View.INVISIBLE);
            }
        });

        btnPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseSong();
                btnPlayButton.setVisibility(View.VISIBLE);


            }
        });
        btnNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id==startingid+songs.size()-1)
                    {
                        id=id-songs.size();
                    }
                    else{
                        id=id+1;

                    }
                    nextSong();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id=id-1;
                    previousSong();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void playSong(long id) {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
        Song song=songDao.getID(id);

            try {
            afd = getApplicationContext().getAssets().openFd(song.getTitle());
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.seekTo(currentlength);
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void pauseSong(){

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            currentlength=mediaPlayer.getCurrentPosition();

        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }
    public void nextSong() throws IOException {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
            Song song=songDao.getID(id);
            songName.setText(song.getSongName());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            playSong(id);

        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }

    public void previousSong() throws IOException {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            SongDao songDao = SoundWaveDatabase.getInstance(this).songDao();
            Song song=songDao.getID(id);
            songName.setText(song.getSongName());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            playSong(id);

        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }

}