package com.example.musicmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    public static final String FAVORITE_SONG = "FAVORITE_SONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        String songTitle = intent.getStringExtra(MainActivity.SONG_TITLE);

        Toast.makeText(this, songTitle, Toast.LENGTH_SHORT).show();
        CheckBox checkBox = findViewById(R.id.favoriteCheckBox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            Intent intent1 =  new Intent(this , MainActivity.class);
            intent1.putExtra(FAVORITE_SONG,  isChecked);
            setResult(RESULT_OK , intent1);
            finish();
        });
    }
}