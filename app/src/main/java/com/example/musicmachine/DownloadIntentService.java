package com.example.musicmachine;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloadIntentService extends IntentService {
    private static final String TAG = "DownloadIntentService";

    public DownloadIntentService() {
        super("DownloadIntent Service");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        try {
            downLoadSong(song);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void downLoadSong(String songName) throws InterruptedException {

        long endTime = System.currentTimeMillis() + 10 * 10000;
        while(System.currentTimeMillis() <= endTime) {

            Thread.sleep(1000);
        }
        Log.d(TAG, "downLoadSong: " +  songName);
    }
}
