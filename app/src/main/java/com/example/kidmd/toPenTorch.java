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

public class toPenTorch extends AppCompatActivity {

    TextView penTorchDesc;
    ImageButton penTorchAudio;
    TextToSpeech textToSpeech;
    private ImageButton secondPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pen_torch);

        penTorchDesc = findViewById(R.id.penTorchDescView);
        penTorchAudio = findViewById(R.id.penTorchAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.penTorchTitleView)).setTypeface(baloo);
        (penTorchDesc).setTypeface(baloo);

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
                textToSpeech.speak(penTorchDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        secondPage = (ImageButton) findViewById(R.id.secondPage);
        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toPenTorch.this, toPenTorch2.class));
            }
        });

    }
}