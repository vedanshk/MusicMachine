package com.example.musicmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String KEY_SONG = "KEY_SONG";
    Button mDownloadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDownloadButton = findViewById(R.id.btnDownload);

        mDownloadButton.setOnClickListener( v -> {

            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();

            for (String song:
                 Playlist.songs) {

                Intent intent = new Intent(MainActivity.this , DownloadIntentService.class);
                intent.putExtra(KEY_SONG, song);
                startService(intent);

            }


        });
    }


}