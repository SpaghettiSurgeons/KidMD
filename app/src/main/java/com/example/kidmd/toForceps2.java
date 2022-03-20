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

public class toForceps2 extends AppCompatActivity {

    TextView forcepsUse;
    ImageButton forcepsAudio;
    TextToSpeech textToSpeech;
    private ImageButton firstPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_forceps2);

        forcepsUse = findViewById(R.id.forcepsUseView);
        forcepsAudio = findViewById(R.id.forcepsAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.forcepsTitleView)).setTypeface(baloo);
        (forcepsUse).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        forcepsAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(forcepsUse.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        firstPage = (ImageButton) findViewById(R.id.backPage);
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toForceps2.this, toForceps.class));
            }
        });

    }
}