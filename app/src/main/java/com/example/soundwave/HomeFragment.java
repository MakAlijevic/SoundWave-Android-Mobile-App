package com.example.soundwave;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;


public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private TextView welcomeUsername,songName1,songName2,songName3;
    private ImageButton next,prev,playButton1, playButton2,playButton3;
    private ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home, container,false);
        viewFlipper =v.findViewById(R.id.simpleViewFlipper);
        welcomeUsername=v.findViewById(R.id.welcomeUsername);
        MainActivity activity= (MainActivity) getActivity();
        welcomeUsername.setText(activity.getUsername());
        next=v.findViewById(R.id.next);
        prev=v.findViewById(R.id.prev);
        playButton1=v.findViewById(R.id.playButton1);
        playButton2=v.findViewById(R.id.playButton2);
        playButton3=v.findViewById(R.id.playSong);
        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        songName1 = v.findViewById(R.id.title1);
        songName2 = v.findViewById(R.id.title2);
        songName3 = v.findViewById(R.id.SongName);
        listView=v.findViewById(R.id.listview);
        List<Song> songs = SoundWaveDatabase.getInstance(getContext()).songDao().getFiveSongs();
        Adapter adapter = new Adapter(getContext(), songs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song=(Song) adapterView.getItemAtPosition(i);
                String title=song.getTitle();
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("id",song.getId());
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });


        playButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("title", "Dua Lipa - Levitating");
                startActivity(intent);
            }
        });
        playButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("title", "Charlie Puth - Light Switch");
                startActivity(intent);
            }
        });
        playButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("title", "Ed Sheeran - Shivers");
                startActivity(intent);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });


        return v;
    }

}
