package com.lfh.musicplayerview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener {
    public MusicService() {
    }

    final static String CHANNEL_ID = "com.lfh.MusicNotification";

    private static final String ACTION_PLAY = "com.lfh.action.PLAY";

    private static final String ACTION_STOP = "com.lfh.action.STOP";

    private static final String ACTION_PAUSE = "com.lfh.action.PAUSE";

    private static final String ACTION_CUT_SONG = "com.lfh.action.CUT_SONG";

    public static MediaPlayer mediaPlayer = new MediaPlayer();

    private String path = "";

    public static boolean isStop = true;

    private int position = 0;

    public static int maxProgress = 0; //song time long

    private NotificationManager manager;

//    public ToggleListener toggle;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channelTest", importance);
        manager.createNotificationChannel(channel);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("MusicPlayer")
                .setContentText("Music is playing ...")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        manager.notify(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MusicService", "onStartCommand...");

        Log.d("MusicService", "intent.getAction():  " + intent.getAction());


        if (intent.getAction().equals(ACTION_PLAY)) {

            if (!path.equals(intent.getStringExtra("path"))) {

                path = intent.getStringExtra("path");

                Log.d("MusicService", "getStringExtra(path)...");

                Log.d("MusicService", path);

//            mediaPlayer = ... // initialize it here

                playMusic(path);

                isStop = false;

            } else {
                if (!mediaPlayer.isPlaying()) {
                    if (!isStop) {
                        Log.d("MusicService", "getCurrentPosition:  " +
                                mediaPlayer.getCurrentPosition());
                        mediaPlayer.start();
                    } else {
                        playMusic(path);
                        isStop = false;
                    }
                }
            }

        } else if (intent.getAction().equals(ACTION_PAUSE)) {

        } else if (intent.getAction().equals(ACTION_STOP)) {

        } else if (intent.getAction().equals(ACTION_CUT_SONG)) {
            position = intent.getIntExtra("position", 0);
            mediaPlayer.seekTo(position * 1000);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        maxProgress = mediaPlayer.getDuration();
//        MainActivity.maxProgress = maxProgress/1000;
        mediaPlayer.start();
    }

    public void playMusic(String Path) {
        mediaPlayer.stop();
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(path);
            Log.d("MusicService", "setDataSource...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync(); // prepare async to not block main thread
        Log.d("MusicService", "prepareAsync...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.cancel(1);
    }
}