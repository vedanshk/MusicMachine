package com.example.musicmachine;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {

    private DownloadService service;

    private static final String TAG = "DownloadHandler";
    @Override
    public void handleMessage(@NonNull Message msg) {
        try {
            downLoadSong(msg.obj.toString());
            service.stopSelf(msg.arg1);

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

    public void setService(DownloadService downloadService) {
        service =  downloadService;
    }
}
