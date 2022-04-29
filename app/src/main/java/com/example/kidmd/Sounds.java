package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sounds extends AppCompatActivity {

    Button playnaturesound;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);


        // play buttons
        playnaturesound = findViewById(R.id.playnaturesound);

        mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.nature_sounds);

        // to play the nature sound when 'Play' button clicked
        playnaturesound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }

    // To stop the media player
    @Override
    protected void onStop() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onStop();
    }
}