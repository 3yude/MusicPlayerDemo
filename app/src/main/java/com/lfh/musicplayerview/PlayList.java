package com.lfh.musicplayerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class PlayList extends AppCompatActivity {

    private List<MusicInfo> musicInfoList;

    private MusicInfoAdapter musicInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                    .permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            musicInfoList = LocalMusicUtils.getMusicInfo(this);
            musicInfoAdapter = new MusicInfoAdapter(this,
                    R.layout.music_info_item, musicInfoList);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(musicInfoAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MusicInfo musicInfo = musicInfoList.get(i);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("path", musicInfo.getPath());
                    intent.putExtra("songName", musicInfo.getSongName());
                    intent.putExtra("duration", musicInfo.getDuration());
                    Log.d("onItemClick", "vocalist:" + musicInfo.getVocalist());
                    intent.putExtra("vocalist", musicInfo.getVocalist());
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    musicInfoList = LocalMusicUtils.getMusicInfo(this);
                    MusicInfoAdapter musicInfoAdapter = new MusicInfoAdapter(this,
                            R.layout.music_info_item, musicInfoList);
                    ListView listView = findViewById(R.id.listView);
                    listView.setAdapter(musicInfoAdapter);
                } else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }
}