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
    private Song firstSong = null;
    private int SongIndex;
    List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        id = getIntent().getLongExtra("id",0);
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

        songs = songDao.getAll();

        for (Song s : songs) {
            if (s.getSongName().equals(song.getSongName())) {
                Song firstSong = s;
                SongIndex = songs.indexOf(firstSong);
                break;
            }
        }


        playSong(SongIndex);
        btnPlayButton.setVisibility(View.INVISIBLE);



        btnPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong(SongIndex);
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
                    if(SongIndex==songs.size()-1){
                        SongIndex=0;
                    }
                    else{
                        SongIndex++;

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
                    if(SongIndex==0){
                        SongIndex=songs.size()-1;
                    }
                    else{
                        SongIndex--;

                    }
                    previousSong();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void playSong(int SongIndex) {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Song song = songs.get(SongIndex);
        String title = song.getTitle();

            try {
            afd = getApplicationContext().getAssets().openFd(title);
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
            Song song = songs.get(SongIndex);
            songName.setText(song.getSongName());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            playSong(SongIndex);

        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }

    public void previousSong() throws IOException {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            Song song = songs.get(SongIndex);
            songName.setText(song.getSongName());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            playSong(SongIndex);

        }else{
            Toast.makeText(this, "Audio not played yet", Toast.LENGTH_SHORT).show();
        }

    }

}