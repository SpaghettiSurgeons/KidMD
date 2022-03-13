package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class toSphygmomanometer extends AppCompatActivity {

    TextView sphygmomanometerDesc;
    ImageButton sphygmomanometerAudio;
    TextToSpeech textToSpeech;
    private ImageButton backArrow;
    private ImageButton home;
    private ImageButton secondPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_sphygmomanometer);

        sphygmomanometerDesc = findViewById(R.id.sphygmomanometerDescView);
        sphygmomanometerAudio = findViewById(R.id.sphygmomanometerAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.sphygmomanometerTitleView)).setTypeface(baloo);
        (sphygmomanometerDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        sphygmomanometerAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(sphygmomanometerDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        secondPage = (ImageButton) findViewById(R.id.secondPage);
        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toSphygmomanometer.this, toSphygmomanometer2.class));
            }
        });

        //---back arrow button---
        backArrow = (ImageButton) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toSphygmomanometer.this, ToolsList.class));
            }
        });

        //---home button---
        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toSphygmomanometer.this, MainMenu.class));
            }
        });

    }
}