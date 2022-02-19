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

public class bpGallbladder extends AppCompatActivity{

    TextView gbDesc;
    ImageButton gbAudio;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_gallbladder);

        gbDesc = findViewById(R.id.gbDescView);
        gbAudio = findViewById(R.id.gbAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.gbTitleView)).setTypeface(baloo);
        (gbDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
                else {
                    Toast.makeText(bpGallbladder.this, "Audio Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gbAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(gbDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}