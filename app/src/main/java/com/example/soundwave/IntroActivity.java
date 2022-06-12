package com.example.soundwave;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        Song song1 = new Song("Charlie Puth - Light Switch.mp3", "Charlie Puth", "03:40", "Light Switch",R.drawable.lightswitch);
        songDao.addSong(song1);

        Song song2 = new Song("Coldplay X BTS - My Universe.mp3", "Coldplay", "03:48", "My Universe",R.drawable.myuniverse);
        songDao.addSong(song2);

        Song song3 = new Song("Charlie Puth - We Dont Talk Anymore.mp3", "Charlie Puth", "03:50", "We Dont Talk Anymore",R.drawable.wedonttalkanymore);
        songDao.addSong(song3);

        Song song4 = new Song("Dove Cameron - Boyfriend.mp3", "Dove Cameron", "02:33", "Boyfriend",R.drawable.boyfriend);
        songDao.addSong(song4);

        Song song5 = new Song("Dua Lipa - Levitating.mp3", "Dua Lipa", "04:27", "Levitating",R.drawable.levitating);
        songDao.addSong(song5);

        Song song6 = new Song("Dua Lipa - New Rules.mp3", "Dua Lipa", "03:44", "New Rules",R.drawable.newrules);
        songDao.addSong(song6);

        Song song7 = new Song("Ed Sheeran - Bad Habits.mp3", "Ed Sheeran", "04:00", "Bad Habits",R.drawable.badhabits);
        songDao.addSong(song7);

        Song song8 = new Song("Ed Sheeran - Shivers.mp3", "Ed Sheeran", "03:57", "Shivers",R.drawable.shivers);
        songDao.addSong(song8);

        Song song9 = new Song("Elley Duhé - Middle of the Night.mp3", "Elley Duhé", "03:02", "Middle of the Night",R.drawable.middleofthenight);
        songDao.addSong(song9);

        Song song10 = new Song("Elton John, Dua Lipa - Cold Heart.mp3", "Elton John, Dua Lipa", "04:10", "Cold Heart",R.drawable.coldheart);
        songDao.addSong(song10);

        Song song11 = new Song("Harry Styles - As It Was.mp3", "Harry Styles", "02:45", "As It Was",R.drawable.asitwas);
        songDao.addSong(song11);

        Song song12 = new Song("Imagine Dragons - Bones.mp3", "Imagine Dragons", "02:45", "Bones",R.drawable.bones);
        songDao.addSong(song12);

        Song song13 = new Song("Justin Bieber - Ghost.mp3", "Justin Bieber", "03:13", "Ghost",R.drawable.ghost);
        songDao.addSong(song13);

        Song song14 = new Song("Imagine Dragons x JID - Enemy.mp3", "Imagine Dragons", "02:50", "Enemy",R.drawable.enemy);
        songDao.addSong(song14);

        Song song15 = new Song("Harry Styles - Late Night Talking.mp3", "Harry Styles", "02:57","Late Night Talking",R.drawable.latenighttalking);
        songDao.addSong(song15);

        Song song16 = new Song("Labrinth - Mount Everest.mp3", "Labirinth", "02:37","Mount Everest",R.drawable.mounteverest);
        songDao.addSong(song16);

        Song song17 = new Song("Lil Nas X - THATS WHAT I WANT.mp3", "Lil Nas X", "02:24","Thats What I Want",R.drawable.thatswhatiwant);
        songDao.addSong(song17);

        Song song18 = new Song("Måneskin - ZITTI E BUONI.mp3", "Maneskin", "03:18","ZITTI E BUONI",R.drawable.zittiebuoni);
        songDao.addSong(song18);

        Song song19 = new Song("Megan Thee Stallion & Dua Lipa - Sweetest Pie.mp3", "Megan Thee Stallion", "03:36","Sweetest Pie",R.drawable.duamegan);
        songDao.addSong(song19);

        Song song20 = new Song("Olivia Rodrigo - deja vu.mp3", "Olivia Rodrigo", "03:51","Deja Vu",R.drawable.dejavu);
        songDao.addSong(song20);

        Song song21 = new Song("Olivia Rodrigo – drivers license.mp3", "Olivia Rodrigo", "02:57","Drivers Licence",R.drawable.driverslicence);
        songDao.addSong(song21);

        Song song22 = new Song("Sean Paul - No Lie.mp3", "Sean Paul", "03:48","No Lie",R.drawable.nolie);
        songDao.addSong(song22);

        Song song23 = new Song("Shawn Mendes - It ll Be Okay.mp3", "Shawn Mendes", "03:52","It ll Be Okay",R.drawable.itllbeokay);
        songDao.addSong(song23);

        Song song24 = new Song("Olivia Rodrigo - good 4 u.mp3", "Olivia Rodrigo", "03:18","Good 4 U",R.drawable.good4u);
        songDao.addSong(song24);

        Song song25 = new Song("Stromae - L enfer.mp3", "Stromae", "03:14","L efner",R.drawable.stromae);
        songDao.addSong(song25);

        Song song26 = new Song("Swedish House Mafia, The Weeknd - Moth To A Flame.mp3", "Swedish House Mafia", "03:54","Moth To A Flame",R.drawable.mothtoaflame);
        songDao.addSong(song26);

        Song song27 = new Song("The Kid LAROI - STAY.mp3", "The Kid LAROI", "02:21","STAY",R.drawable.stay);
        songDao.addSong(song27);

        Song song28 = new Song("The Weeknd - Die For You.mp3", "The Weeknd", "04:20","Die For You",R.drawable.dieforyou);
        songDao.addSong(song28);

        Song song29 = new Song("Olivia Rodrigo - traitor.mp3", "Olivia Rodrigo", "03:50","Traitor",R.drawable.traitor);
        songDao.addSong(song29);

        Song song30 = new Song("The Weeknd - Take My Breath.mp3", "The Weeknd", "03:44","Take My Breath",R.drawable.takemybreath);
        songDao.addSong(song30);

    }

}