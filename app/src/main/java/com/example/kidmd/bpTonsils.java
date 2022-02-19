package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class bpTonsils extends AppCompatActivity{

    TextView tonsilDesc;
    ImageButton tonsilAudio;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_tonsils);

        tonsilDesc = findViewById(R.id.tonsilDescView);
        tonsilAudio = findViewById(R.id.tonsilAudio);

        //Set font to Baloo
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.tonsilTitleView)).setTypeface(baloo);
        (tonsilDesc).setTypeface(baloo);

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
        tonsilAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(tonsilDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}