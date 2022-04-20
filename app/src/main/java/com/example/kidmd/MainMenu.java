package com.example.kidmd;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton procedurebutton, hospitalbutton, toolsbutton, bodypartbutton, logoutbutton, profileSearchButton, settingsButton;
    private AppCompatTextView title;
    // Bottom Toolbar
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //lock orientation to portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Change text font
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        //((TextView) findViewById(R.id.menudesc)).setTypeface(baloo);

        //Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");


        //initialize procedures button
        procedurebutton = (AppCompatButton) findViewById(R.id.procedurebutton);
        procedurebutton.setOnClickListener(this);
        hospitalbutton = (AppCompatButton) findViewById(R.id.hospitalbutton);
        hospitalbutton.setOnClickListener(this);
        toolsbutton = (AppCompatButton) findViewById(R.id.toolsbutton);
        toolsbutton.setOnClickListener(this);
        bodypartbutton= (AppCompatButton) findViewById(R.id.bodypartbutton);
        bodypartbutton.setOnClickListener(this);
        logoutbutton= (AppCompatButton) findViewById(R.id.logout);
        logoutbutton.setOnClickListener(this);
        profileSearchButton = (AppCompatButton) findViewById(R.id.profilesearchbutton);
        profileSearchButton.setOnClickListener(this);
        settingsButton = (AppCompatButton) findViewById(R.id.settingsbutton);
        settingsButton.setOnClickListener(this);

        // Visibility for toolbar
        title = (AppCompatTextView) findViewById(R.id.appTitle_toolbar);
        title.setVisibility(View.VISIBLE);



        //Only show logout button and profile if user is logged in
        //if (FirebaseAuth.getInstance().getCurrentUser() == null) {
        //    logoutbutton.setVisibility(View.GONE);
        //    profile.setVisibility(View.VISIBLE);
        //}

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.procedurebutton:
                startActivity(new Intent(MainMenu.this, ProceduresList.class));
                break;
            case R.id.hospitalbutton:
                startActivity(new Intent(MainMenu.this, HospitalMenu.class));
                break;
            case R.id.toolsbutton:
                startActivity(new Intent(MainMenu.this, ToolsList.class));
                break;
            case R.id.bodypartbutton:
                startActivity(new Intent(MainMenu.this, BodyPartList.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu.this, MainActivity.class));
                break;
            case R.id.profilesearchbutton:
                startActivity(new Intent(MainMenu.this, ProfileSearch.class));
                break;
            case R.id.settingsbutton:
                startActivity(new Intent(MainMenu.this, Settings.class));
                break;


            // Bottom Toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            case R.id.notifications_button:
                //startActivity(new Intent(this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;

        }
    }

}
