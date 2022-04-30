package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Sounds extends AppCompatActivity implements View.OnClickListener {

    ImageButton naturesounds, wavesounds, rainsounds, stormsounds;
    MediaPlayer mediaPlayer;
    // Bottom Toolbar
    AppCompatImageView home_button, explore_button, notifications_button, profile_button;
    // Top Toolbar
    AppCompatTextView soundsPageTitle;
    AppCompatImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nature);

        // play buttons for each sound
        naturesounds = findViewById(R.id.naturesounds);
        naturesounds.setOnClickListener(this);
        wavesounds = findViewById(R.id.wavesounds);
        wavesounds.setOnClickListener(this);
        rainsounds = findViewById(R.id.rainsounds);
        rainsounds.setOnClickListener(this);
        stormsounds = findViewById(R.id.stormsounds);
        stormsounds.setOnClickListener(this);


        // Visibility for toolbar
        soundsPageTitle = (AppCompatTextView) findViewById(R.id.soundsPageTitle);
        soundsPageTitle.setVisibility(View.VISIBLE);
        backButton = (AppCompatImageView) findViewById(R.id.backArrow);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(this);


        // Bottom toolbar
        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);
    }

    // To stop the media player
    @Override
    protected void onStop() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.naturesounds:
                mediaPlayer.release(); // so sounds don't play over each other
                mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.nature);
                mediaPlayer.start();
                break;
            case R.id.wavesounds:
                mediaPlayer.release();
                mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.waves);
                mediaPlayer.start();
                break;
            case R.id.rainsounds:
                mediaPlayer.release();
                mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.rain);
                mediaPlayer.start();
                break;
            case R.id.stormsounds:
                mediaPlayer.release();
                mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.storm);
                mediaPlayer.start();
                break;



            // Toolbar
            case R.id.backArrow:
                startActivity(new Intent(Sounds.this, MainMenu.class));

            // Bottom Toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            case R.id.notifications_button:
                startActivity(new Intent(this, Notifications.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;

        }
    }
}