package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    SwitchCompat nightSwitch;
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nightSwitch = (SwitchCompat) findViewById(R.id.nightSwitch);

        //nightSwitch.setTextOff("Night Theme");
        //nightSwitch.setTextOn("Default Theme");

        //Set switch state based on shared preferences
        nightSwitch.setChecked(SpRetrieve("nightMode"));


        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //stop from triggering when switch state is changed programmatically
                if(!compoundButton.isPressed()) {
                    return;
                }
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SpSave("nightMode", true);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SpSave("nightMode", false);
                }
            }
        });

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Bottom Toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            /*case R.id.notifications_button:
                startActivity(new Intent(this, ?.class));
                break;*/
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }
    //Retrieves boolean value of preference
    private boolean SpRetrieve(String key){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Settings", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }
    //Saves key to shared preference
    private void SpSave(String key,boolean value){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Settings", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}