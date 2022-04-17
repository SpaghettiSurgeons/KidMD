package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import java.util.Locale;

public class toSpirometer extends AppCompatActivity implements View.OnClickListener {

    TextView spirometerDesc;
    ImageButton spirometerAudio;
    TextToSpeech textToSpeech;
    private ImageButton secondPage;
    // Bottom Toolbar
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_spirometer);

        spirometerDesc = findViewById(R.id.spirometerDescView);
        spirometerAudio = findViewById(R.id.spirometerAudio);

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.spirometerTitleView)).setTypeface(baloo);
        (spirometerDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        spirometerAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(spirometerDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        secondPage = (ImageButton) findViewById(R.id.secondPage);
        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(toSpirometer.this, toSpirometer2.class));
            }
        });
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