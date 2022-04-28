package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class resourcesPage extends AppCompatActivity implements View.OnClickListener{

    // for top toolbar
    AppCompatTextView resourceTitle;
    AppCompatImageView resourceBack;

    // for bottom toolbar
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page);

        // Visibility for toolbar
        resourceTitle = (AppCompatTextView) findViewById(R.id.resourcesTitle);
        resourceTitle.setVisibility(View.VISIBLE);
        resourceBack = (AppCompatImageView) findViewById(R.id.backArrow);
        resourceBack.setVisibility(View.VISIBLE);

        // Back Arrow
        resourceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(resourcesPage.this, MainMenu.class));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            /*case R.id.notifications_button:
                startActivity(new Intent(this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ?.class));
                break;*/
        }
    }
}