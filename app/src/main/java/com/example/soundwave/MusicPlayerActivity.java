package com.example.soundwave;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity{

    private ImageButton btnPlayButton,btnPauseButton,btnNextButton,btnPreviousButton;
    private MediaPlayer mediaPlayer = null;
    private TextView songName, length,songArtist, currentTime;
    private SeekBar seekBar;
    private ImageView picture;
    private AssetFileDescriptor afd;
    private int currentlength;
    private long id;
    private Song firstSong = null;
    private int SongIndex;
    private List<Song> songs;
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
        currentTime = findViewById(R.id.music_player_current_time);
        seekBar = findViewById(R.id.seekBar);

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
        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null && currentlength < mediaPlayer.getDuration()){
                    try {
                        sleep(100);
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        currentTime.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));
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
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null && b){
                    mediaPlayer.seekTo(i);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

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
        mediaPlayer.reset();

            try {
            afd = getApplicationContext().getAssets().openFd(title);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.seekTo(currentlength);
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
            CreateNotification.createNotification(MusicPlayerActivity.this, songs.get(SongIndex), R.drawable.ic_baseline_pause_24,1, songs.size()-1);

            } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void pauseSong(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            currentlength=mediaPlayer.getCurrentPosition();
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

    @SuppressLint("DefaultLocale")
    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()) {
            pauseSong();
        }
        currentThread().interrupt();
    }
}