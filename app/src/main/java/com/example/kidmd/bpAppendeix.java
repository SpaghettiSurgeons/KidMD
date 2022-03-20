package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class bpAppendeix extends AppCompatActivity {

    TextView appendixDesc;
    ImageButton appendixAudio;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_appendix);

        appendixDesc = findViewById(R.id.appendixDescView);
        appendixAudio = findViewById(R.id.appendixAudio);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.appendixTitleView)).setTypeface(baloo);
        (appendixDesc).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        appendixAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(appendixDesc.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });


    }
}
