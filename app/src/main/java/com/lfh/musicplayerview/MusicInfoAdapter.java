package com.lfh.musicplayerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author lfh
 * @project MusicPlayer
 * @package_name com.lfh.musicplayerview
 * @date 20-12-6
 * @time 下午1:42
 * @year 2020
 * @month 12
 * @month_short 十二月
 * @month_full 十二月
 * @day 06
 * @day_short 星期日
 * @day_full 星期日
 * @hour 13
 * @minute 42
 */
public class MusicInfoAdapter extends ArrayAdapter<MusicInfo> {

    private int resourceId;

    public MusicInfoAdapter(@NonNull Context context, int resource, @NonNull List<MusicInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        MusicInfo musicInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);
        TextView songName = view.findViewById(R.id.songName);
        TextView vocalist = view.findViewById(R.id.vocalist);
        TextView duration = view.findViewById(R.id.duration);

        songName.setText(musicInfo.getSongName());
        vocalist.setText("vocalist:  " + musicInfo.getVocalist());
        duration.setText("duration:  " + musicInfo.getDuration() / 1000 / 60  + ":" + musicInfo.getDuration() % 60);
        return view;
    }
}
