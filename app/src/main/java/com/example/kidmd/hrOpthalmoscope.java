package com.example.kidmd;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class hrOpthalmoscope extends Activity {

    TextView opthalmoscopeDesc;
    ImageButton opthalmoscopeAudio;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_opthalmoscope);

        opthalmoscopeDesc = findViewById(R.id.opthalmoscopeDesc);
        opthalmoscopeAudio = findViewById(R.id.opthalmoscopeAudio);

        //Set font to Baloo
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.opthalmoscopeTitleView)).setTypeface(baloo);
        (opthalmoscopeDesc).setTypeface(baloo);

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
        opthalmoscopeAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(opthalmoscopeDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}