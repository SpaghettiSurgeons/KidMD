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

public class toRetractors2 extends AppCompatActivity {

    TextView retractorsUse;
    ImageButton retractorsAudio;
    TextToSpeech textToSpeech;
    private ImageButton firstPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_retractors2);

        retractorsUse = findViewById(R.id.retractorsUseView);
        retractorsAudio = findViewById(R.id.retractorsAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.retractorsTitleView)).setTypeface(baloo);
        (retractorsUse).setTypeface(baloo);

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
                textToSpeech.speak(retractorsUse.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        firstPage = (ImageButton) findViewById(R.id.backPage);
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toRetractors2.this, toRetractors.class));
            }
        });

    }
}