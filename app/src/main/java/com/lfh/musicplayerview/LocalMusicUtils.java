package com.lfh.musicplayerview;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lfh
 * @project MusicPlayer
 * @package_name com.lfh.musicplayerview
 * @date 20-12-6
 * @time 上午11:48
 * @year 2020
 * @month 12
 * @month_short 十二月
 * @month_full 十二月
 * @day 06
 * @day_short 星期日
 * @day_full 星期日
 * @hour 11
 * @minute 48
 */
public class LocalMusicUtils {

    private static MusicInfo musicInfo;

    private static String songName;

    private static String vocalist;

    private static long id;

    private static long size;

    private static String path;

    private static int duration;

    public static List<MusicInfo> list;

    public static List<MusicInfo> getMusicInfo(Context context){

        list = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        Log.d("getMusicInfo", "Get cursor");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                musicInfo = new MusicInfo();
                songName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                vocalist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                Log.d("getMusicInfo", "Start Setting");
                Log.d("getMusicInfo", "path:" + path);
                Log.d("getMusicInfo", "id:" + Long.toString(id));
                Log.d("getMusicInfo", "vocalist:" + vocalist);
                Log.d("getMusicInfo", "songName:" + songName);
                Log.d("getMusicInfo", "duration:" + duration);

                musicInfo.setVocalist(vocalist);
                musicInfo.setSongName(songName);
                musicInfo.setPath(path);
                musicInfo.setSize(size);
                musicInfo.setId(id);
                musicInfo.setDuration(duration);

                list.add(musicInfo);
                }
            }else {
            Log.d("LocalMusicUtils", "cursor == null");
        }

        cursor.close();
        return list;

    }


}
