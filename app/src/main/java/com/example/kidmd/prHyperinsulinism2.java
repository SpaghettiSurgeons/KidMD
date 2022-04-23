package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class prHyperinsulinism2 extends AppCompatActivity implements View.OnClickListener {

    private ImageButton pageBack;
    private ImageButton xBack;
    // Bottom Toolbar
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;

    //for audio
    ImageButton hyperAudio2;
    TextView hyperDesc2;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_hyperinsulinism2);

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.hyperPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prHyperinsulinism2.this, prHyperinsulinism.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.hyperPage2X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prHyperinsulinism2.this, ProceduresList.class));
            }
        });

        //audio
        hyperAudio2 = findViewById(R.id.hyperAudio2);
        hyperDesc2 = findViewById(R.id.prHyperDesc2);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US); }
            }
        });
        hyperAudio2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(hyperDesc2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //change font
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.Hyperinsulinism_2)).setTypeface(baloo);
        ((TextView) findViewById(R.id.Hyperinsulinism2_2)).setTypeface(baloo);
        ((TextView) findViewById(R.id.prHyperTitle2)).setTypeface(baloo);
        (hyperDesc2).setTypeface(baloo);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // Bottom Toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            /*case R.id.notifications_button:
                startActivity(new Intent(ProceduresList.this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(ProceduresList.this, ?.class));
                break;*/
        }
    }
}

