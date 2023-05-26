package com.example.musicmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    public static final String KEY_SONG = "KEY_SONG";
    private boolean Bound = false;
    private Messenger mServiceMessager;
    private Messenger mActivityMessager  = new Messenger(new ActivityHandler(this));
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Bound =  true;
            mServiceMessager = new Messenger(binder);
            Message message = Message.obtain();
            message.arg1 = 2;
            message.arg2 = 1;
            message.replyTo = mActivityMessager;
            try {
                mServiceMessager.send(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
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
                Intent intent = new Intent(MainActivity.this , PlayerService.class);
                startService(intent);
                Message message = Message.obtain();
                message.arg1 = 2;
                message.replyTo = mActivityMessager;
                try {
                    mServiceMessager.send(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public void changePlayText(String text){
        playButton.setText(text);
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