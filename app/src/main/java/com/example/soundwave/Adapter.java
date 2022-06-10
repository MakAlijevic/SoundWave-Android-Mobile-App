package com.example.soundwave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<Song> songList;
    private Context context;

    public Adapter(Context context, List<Song> songList){
        this.context = context;
        this.songList = songList;
    }
    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int i) {
        return songList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return songList.indexOf(songList.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.listview_layout, viewGroup, false);
        Song song = (Song) getItem(i);
        TextView songArtist = view.findViewById(R.id.SongArtist);
        TextView songName=view.findViewById(R.id.SongName);
        TextView length = view.findViewById(R.id.length);

        songArtist.setText(song.getArtist());
        length.setText(song.getLength());
        songName.setText(song.getSongName());
        return view;
    }
}
