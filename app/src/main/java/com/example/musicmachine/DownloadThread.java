package com.example.musicmachine;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread {
    private static final String TAG = "DownloadThread";
    DownloadHandler handler;
    @Override
    public void run() {
        Looper.prepare();
        handler = new DownloadHandler();
        Looper.loop();
    }

}
