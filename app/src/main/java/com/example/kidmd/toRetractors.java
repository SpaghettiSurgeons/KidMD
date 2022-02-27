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

public class toRetractors extends AppCompatActivity {

    TextView retractorsDesc;
    ImageButton retractorsAudio;
    TextToSpeech textToSpeech;
    private ImageButton backArrow;
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_retractors);

        retractorsDesc = findViewById(R.id.retractorsDescView);
        retractorsAudio = findViewById(R.id.retractorsAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.retractorsTitleView)).setTypeface(baloo);
        (retractorsDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        retractorsAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(retractorsDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //---back arrow button---
        backArrow = (ImageButton) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toRetractors.this, ToolsList.class));
            }
        });

        //---home button---
        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toRetractors.this, MainMenu.class));
            }
        });

    }
}