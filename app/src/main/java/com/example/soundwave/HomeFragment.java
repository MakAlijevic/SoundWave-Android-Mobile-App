package com.example.soundwave;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private TextView welcomeUsername,songName1,songName2,songName3;
    private ImageButton next,prev,playButton1, playButton2,playButton3;
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
        playButton3=v.findViewById(R.id.playButton3);
        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        songName1 = v.findViewById(R.id.title1);
        songName2 = v.findViewById(R.id.title2);
        songName3 = v.findViewById(R.id.title3);

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
