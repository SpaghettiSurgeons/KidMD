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

public class bpKidney extends AppCompatActivity{

    TextView kidneyDesc;
    ImageButton kidneyAudio;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_kidney);

        kidneyDesc = findViewById(R.id.kidneyDescView);
        kidneyAudio = findViewById(R.id.kidneyAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.kidneyTitleView)).setTypeface(baloo);
        (kidneyDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
                else {
                    Toast.makeText(bpKidney.this, "Audio Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        kidneyAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(kidneyDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}