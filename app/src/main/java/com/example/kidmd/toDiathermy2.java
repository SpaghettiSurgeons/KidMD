package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageButton;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import java.util.Locale;

//Displays a usage page in the Tools List: image, explanation of its use in surgery,
// text-to-audio functionality, and backward button to go to the tool's physical description
public class toDiathermy2 extends AppCompatActivity implements View.OnClickListener {

    TextView diathermyUse;
    ImageButton diathermyAudio;
    TextToSpeech textToSpeech;
    private ImageButton firstPage;
    // Bottom Toolbar
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_diathermy2);

        diathermyUse = findViewById(R.id.diathermyUseView);
        diathermyAudio = findViewById(R.id.diathermyAudio);

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.diathermyTitleView)).setTypeface(baloo);
        (diathermyUse).setTypeface(baloo);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!= TextToSpeech.ERROR){
                    //set language to US
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        diathermyAudio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textToSpeech.speak(diathermyUse.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        firstPage = (ImageButton) findViewById(R.id.backPage);
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(toDiathermy2.this, toDiathermy.class));
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // bottom toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            case R.id.notifications_button:
                startActivity(new Intent(this, Notifications.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }
}