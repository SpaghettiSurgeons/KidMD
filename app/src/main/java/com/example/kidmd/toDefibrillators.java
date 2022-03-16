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

public class toDefibrillators extends AppCompatActivity {

    TextView defibrillatorsDesc;
    ImageButton defibrillatorsAudio;
    TextToSpeech textToSpeech;
    private ImageButton secondPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_defibrillators);

        defibrillatorsDesc = findViewById(R.id.defibrillatorsDescView);
        defibrillatorsAudio = findViewById(R.id.defibrillatorsAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.defibrillatorsTitleView)).setTypeface(baloo);
        (defibrillatorsDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        defibrillatorsAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(defibrillatorsDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        secondPage = (ImageButton) findViewById(R.id.secondPage);
        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toDefibrillators.this, toDefibrillators2.class));
            }
        });

    }
}