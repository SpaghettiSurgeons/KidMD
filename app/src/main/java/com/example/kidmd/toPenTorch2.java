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

public class toPenTorch2 extends AppCompatActivity {

    TextView penTorchUse;
    ImageButton penTorchAudio;
    TextToSpeech textToSpeech;
    private ImageButton backArrow;
    private ImageButton home;
    private ImageButton firstPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pen_torch2);

        penTorchUse = findViewById(R.id.penTorchUseView);
        penTorchAudio = findViewById(R.id.penTorchAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.penTorchTitleView)).setTypeface(baloo);
        (penTorchUse).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        penTorchAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(penTorchUse.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        firstPage = (ImageButton) findViewById(R.id.backPage);
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toPenTorch2.this, toPenTorch.class));
            }
        });

        //---home button---
        home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toPenTorch2.this, MainMenu.class));
            }
        });

    }
}