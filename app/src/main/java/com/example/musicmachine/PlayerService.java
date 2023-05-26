package com.example.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class PlayerService extends Service {

    private static final String TAG = "PlayerService";
    private MediaPlayer mPlayer;
    private final IBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: started");
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: started");
        super.onCreate();
        mPlayer = MediaPlayer.create(this , R.raw.jingle);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.setOnCompletionListener( v->{
            stopSelf();
        });
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: started");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: started");
        mPlayer.release();
    }

    public class LocalBinder extends Binder{
        public PlayerService getServices(){
            return PlayerService.this;
        }
    }

    public boolean isPlaying(){
        return mPlayer.isPlaying();
    }
    //
    public void play(){
        mPlayer.start();
    }

    public void pause(){
        mPlayer.pause();
    }
}
