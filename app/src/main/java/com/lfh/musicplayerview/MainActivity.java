package com.lfh.musicplayerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private MediaPlayer mediaPlayer = null;

    private TextView name;

    private TextView singer;

    Button play;

    Button pause;

    Button stop;

//    private String path;
    Intent intent;

    private SeekBar seekBar;

    public int position = 0;

    public static int maxProgress = 0;

    final Timer timer = new Timer();

    private boolean isSeekBarChanging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        intent = getIntent();
//        path = intent.getStringExtra("path");
        maxProgress = intent.getIntExtra("duration", 0)/1000;
        Log.d("onCreate", "maxProgress111   " + Integer.toString(maxProgress));

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        name = findViewById(R.id.name);
        singer = findViewById(R.id.singer);

        name.setText(intent.getStringExtra("songName"));
        singer.setText(intent.getStringExtra("vocalist"));
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        getPermission();
        init();

    }

    public void init(){
        intent.setAction("com.lfh.action.PLAY");
        intent.setClass(this, MusicService.class);
        startService(intent);
    }

//    private void initMediaPlayer() {
//        Log.d("Path", path);
//        try {
//            mediaPlayer.setDataSource(path);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    initMediaPlayer();
                }else {
                    Toast.makeText(this, "Deny permissions will down you app",Toast.LENGTH_LONG).show();
                    finish();
                }
            default:
                break;
        }
    }

    public void getPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        seekBar = findViewById(R.id.seerbar);
        seekBar.setOnSeekBarChangeListener(this);
//        maxProgress = MusicService.maxProgress/1000;
        seekBar.setMax(maxProgress);
        Log.d("seekBar", "maxProgress  " + Integer.toString(maxProgress));
        if(MusicService.mediaPlayer.isPlaying()){
            position = MusicService.mediaPlayer.getCurrentPosition()/1000;
            seekBar.setProgress(position);
            Log.d("seekBar", "position" + Integer.toString(position));
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("seekBar", "position" + Integer.toString(position));
                if(!isSeekBarChanging){
                    seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition()/1000);
                }
            }
        },1000, 1000);
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mediaPlayer != null) mediaPlayer.release();

        Log.d("MainActivity", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                intent.setAction("com.lfh.action.PLAY");
                intent.setClass(this, MusicService.class);
                startService(intent);
                break;
            case R.id.pause:
                mediaPlayer = MusicService.mediaPlayer;
                mediaPlayer.pause();
                break;
            case R.id.stop:
                mediaPlayer = MusicService.mediaPlayer;
                mediaPlayer.stop();
                MusicService.isStop = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("SeSeekBar", "onProgressChanged   " + seekBar.getProgress());
        position = seekBar.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isSeekBarChanging = true;
        Log.d("SeSeekBar", "onStartTrackingTouch");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isSeekBarChanging = false;
        intent.setAction("com.lfh.action.CUT_SONG");
        intent.putExtra("position", position);
        intent.setClass(this, MusicService.class);
        startService(intent);
        Log.d("SeSeekBar", "onStartTrackingTouch");
    }

//    public void onSetMaxProgress(int mProgress){
//        seekBar.setMax(mProgress);
//    }
}