package com.example.musicmachine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.musicmachine.adapters.PlaylistAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    public static final String KEY_SONG = "KEY_SONG";
    public static final String SONG_TITLE = "SONG_TITLE";
    private RecyclerView recyclerView;
    private boolean Bound = false;
    private Messenger mServiceMessager;
    private final Messenger mActivityMessager  = new Messenger(new ActivityHandler(this));
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
    Button playButton , btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.recyclerView);

        playButton =  findViewById(R.id.play);

        PlaylistAdapter adapter = new PlaylistAdapter(Playlist.songs, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener( v->{
                testIntents("GRADLE GRADLE GRADLE");
        });
    }

    private void testIntents(String title) {

        Intent intent = new Intent(this , DetailsActivity.class);
        intent.putExtra(SONG_TITLE, title);
        startActivityForResult(intent , 101);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            assert data != null;
            Toast.makeText(this, data.getBooleanExtra(DetailsActivity.FAVORITE_SONG , false) + "", Toast.LENGTH_SHORT).show();
        }
    }
}