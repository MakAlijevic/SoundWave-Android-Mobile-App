package com.example.soundwave;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class SearchFragment extends Fragment {

    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_search, container,false);
        listView=v.findViewById(R.id.search_list_view);
        List<Song> songs = SoundWaveDatabase.getInstance(getContext()).songDao().getAll();
        Adapter adapter = new Adapter(getContext(), songs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song=(Song) adapterView.getItemAtPosition(i);
                String title=song.getTitle();
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        return v;
    }
}
