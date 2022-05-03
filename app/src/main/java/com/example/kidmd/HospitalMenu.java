package com.example.kidmd;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class HospitalMenu extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton photoButton, interactButton;
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    AppCompatTextView hospitalRoomTitle;
    AppCompatImageView hospitalRoomBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_menu);

        //lock orientation to portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (SpRetrieve("nightMode")){
            View menuview = findViewById(R.id.hospitalBackground);
            menuview.setBackgroundColor(Color.parseColor("#121212"));;
        }
        photoButton = (AppCompatButton) findViewById(R.id.roomPhotos);
        photoButton.setOnClickListener(this);
        interactButton = (AppCompatButton) findViewById(R.id.interactButton);
        interactButton.setOnClickListener(this);

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);

        // Visibility for top toolbar
        hospitalRoomTitle = (AppCompatTextView) findViewById(R.id.hospitalRoomTitle);
        hospitalRoomTitle.setVisibility(View.VISIBLE);
        hospitalRoomBack = (AppCompatImageView) findViewById(R.id.backArrow);
        hospitalRoomBack.setVisibility(View.VISIBLE);


        // Back Arrow
        hospitalRoomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HospitalMenu.this, MainMenu.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interactButton:
                startActivity(new Intent(HospitalMenu.this, HospitalInteract.class));
                break;

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
    private boolean SpRetrieve(String key){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Settings", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }
}