package com.lfh.musicplayerview;

/**
 * @author lfh
 * @project MusicPlayer
 * @package_name com.lfh.musicplayerview
 * @date 20-12-5
 * @time 下午11:20
 * @year 2020
 * @month 12
 * @month_short 十二月
 * @month_full 十二月
 * @day 05
 * @day_short 星期六
 * @day_full 星期六
 * @hour 23
 * @minute 20
 */
public class MusicInfo {

    private String songName;

    private String vocalist;

    private long id;

    private String path;

    private long size;

    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MusicInfo() {

    }
    public MusicInfo(String songName, String vocalist) {
        this.songName = songName;
        this.vocalist = vocalist;
    }

    public String getVocalist() {
        return vocalist;
    }

    public void setVocalist(String vocalist) {
        this.vocalist = vocalist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }



}
