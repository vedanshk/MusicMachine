package com.example.musicmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String KEY_SONG = "KEY_SONG";
    private boolean Bound = false;
    private PlayerService mPlayerService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Bound =  true;
            PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) binder;

            mPlayerService =  localBinder.getServices();
            if(mPlayerService.isPlaying()){
                playButton.setText("Pause");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Bound =  false;
        }
    };
    Button playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        playButton =  findViewById(R.id.play);

        playButton.setOnClickListener(v->{
            if(Bound){

                if(mPlayerService.isPlaying()){
                    playButton.setText("Play");
                    mPlayerService.pause();
                }else{
                    Intent intent = new Intent(MainActivity.this , PlayerService.class);
                    startService(intent);
                    playButton.setText("Pause");
                    mPlayerService.play();
                }

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this , PlayerService.class);
        bindService(intent ,serviceConnection , Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Bound){
            unbindService(serviceConnection );
            Bound = false;
        }

    }
}