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

public class toSutures extends AppCompatActivity {

    TextView suturesDesc;
    ImageButton suturesAudio;
    TextToSpeech textToSpeech;
    private ImageButton backArrow;
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_sutures);

        suturesDesc = findViewById(R.id.suturesDescView);
        suturesAudio = findViewById(R.id.suturesAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.suturesTitleView)).setTypeface(baloo);
        (suturesDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        suturesAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(suturesDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //---back arrow button---
        backArrow = (ImageButton) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toSutures.this, ToolsList.class));
            }
        });

        //---home button---
        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toSutures.this, MainMenu.class));
            }
        });

    }
}