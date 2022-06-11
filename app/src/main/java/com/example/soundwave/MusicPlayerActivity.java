package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
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
    private TextView songName, length,songArtist;
    private ImageView picture;
    private AssetFileDescriptor afd;
    private int currentlength;
    private long id;
    private Song firstSong = null;
    private int SongIndex;
    List<Song> songs;
    private NotificationManager notificationManager;

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
        songArtist=findViewById(R.id.music_player_artist);
        length=findViewById(R.id.music_player_length);
        picture=findViewById(R.id.music_player_picture);

        songName.setText(song.getSongName());
        songArtist.setText(song.getArtist());
        length.setText(song.getLength());
        picture.setImageResource(song.getPictureID());

        songs = songDao.getAll();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }


        for (Song s : songs) {
            if (s.getSongName().equals(song.getSongName())) {
                Song firstSong = s;
                SongIndex = songs.indexOf(firstSong);
                break;
            }
        }



        playSong(SongIndex);
        btnPlayButton.setVisibility(View.INVISIBLE);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
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

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID, "SoundWave", NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
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
            CreateNotification.createNotification(MusicPlayerActivity.this, songs.get(SongIndex), R.drawable.ic_baseline_pause_24,1, songs.size()-1);

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

            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            Song song = songs.get(SongIndex);
            songName.setText(song.getSongName());
            songArtist.setText(song.getArtist());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            currentlength = 0;
            playSong(SongIndex);
            btnPlayButton.setVisibility(View.INVISIBLE);



    }

    public void previousSong() throws IOException {

            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            Song song = songs.get(SongIndex);
            songName.setText(song.getSongName());
            songArtist.setText(song.getArtist());
            length.setText(song.getLength());
            picture.setImageResource(song.getPictureID());
            currentlength = 0;
            playSong(SongIndex);
            btnPlayButton.setVisibility(View.INVISIBLE);

    }

    public void stopSong()
    {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        currentlength=0;
    }

    protected void onDestroy() {
        super.onDestroy();
        stopSong();
    }



}