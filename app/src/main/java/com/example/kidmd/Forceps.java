package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;

public class Forceps extends AppCompatActivity {

    TextView forcepsDesc;
    ImageButton forcepsAudio;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forceps);

        forcepsDesc = findViewById(R.id.forcepsDescView);
        forcepsAudio = findViewById(R.id.forcepsAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.forcepsTitleView)).setTypeface(baloo);
        (forcepsDesc).setTypeface(baloo);

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
                textToSpeech.speak(forcepsDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}