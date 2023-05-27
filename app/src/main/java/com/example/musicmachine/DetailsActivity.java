package com.example.musicmachine;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicmachine.models.Song;


public class DetailsActivity extends AppCompatActivity {

    public static final String FAVORITE_SONG = "favorite_song" ;
    private Song mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView titleLabel = findViewById(R.id.songTitleLabel);
        final CheckBox favoriteCheckbox = findViewById(R.id.checkBox);

        Intent intent = getIntent();
//        if (intent.getStringExtra(MainActivity.EXTRA_TITLE) != null) {
//            String songTitle = intent.getStringExtra(MainActivity.EXTRA_TITLE);
//            titleLabel.setText(songTitle);
//        }
        if (intent.getParcelableExtra(MainActivity.EXTRA_SONG) != null) {
            Song song = intent.getParcelableExtra(MainActivity.EXTRA_SONG);
            titleLabel.setText(song.getTitle());
            favoriteCheckbox.setChecked(song.isFavorite());
        }

        final int listPosition = intent.getIntExtra(MainActivity.EXTRA_LIST_POSITION, 0);

        favoriteCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.EXTRA_FAVORITE, isChecked);
            resultIntent.putExtra(MainActivity.EXTRA_LIST_POSITION, listPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}