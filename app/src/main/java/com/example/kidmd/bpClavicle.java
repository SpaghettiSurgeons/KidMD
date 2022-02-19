package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class bpClavicle extends AppCompatActivity{

    TextView clavicleDesc;
    ImageButton clavicleAudio;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_clavicle);

        clavicleDesc = findViewById(R.id.clavicleDescView);
        clavicleAudio = findViewById(R.id.clavicleAudio);

        //Set font to Baloo
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.clavicleTitleView)).setTypeface(baloo);
        (clavicleDesc).setTypeface(baloo);

        //Set up Text to Speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        //TTS button listener
        clavicleAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(clavicleDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}