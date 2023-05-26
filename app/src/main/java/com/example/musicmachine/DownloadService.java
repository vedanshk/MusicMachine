package com.example.musicmachine;


import static com.example.musicmachine.MainActivity.KEY_SONG;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import androidx.annotation.Nullable;

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";
    private DownloadHandler handler;
    @Override
    public void onCreate() {
        DownloadThread thread = new DownloadThread() ;
        thread.setName("Download Name");
        thread.start();
        while (thread.handler == null){};
        handler =  thread.handler;
        handler.setService(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(KEY_SONG);
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        handler.sendMessage(message);
        return Service.START_REDELIVER_INTENT;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
