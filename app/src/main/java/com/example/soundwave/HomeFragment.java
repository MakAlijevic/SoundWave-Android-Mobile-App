package com.example.soundwave;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView welcomeUsername;
    private ImageButton next,prev,playButton1, playButton2,playButton3;
    private ListView listView;
    private SongDao songDao=SoundWaveDatabase.getInstance(getContext()).songDao();

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
                Song song=songDao.getSong("Harry Styles - As It Was.mp3");
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("id",song.getId());
                intent.putExtra("title", song.getTitle());
                startActivity(intent);
            }
        });
        playButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song=songDao.getSong("Dua Lipa - Levitating.mp3");
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("id",song.getId());
                intent.putExtra("title", song.getTitle());
                startActivity(intent);
            }
        });
        playButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song=songDao.getSong("Ed Sheeran - Shivers.mp3");
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("id",song.getId());
                intent.putExtra("title", song.getTitle());
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
                viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
                viewFlipper.showNext();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setInAnimation(getActivity(), R.anim.slide_in_right);
                viewFlipper.setOutAnimation(getActivity(), R.anim.slide_out_left);
                viewFlipper.showPrevious();
            }
        });


        return v;
    }

}
