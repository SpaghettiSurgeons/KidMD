package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExplorePage extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    AppCompatTextView explorePageTitle;
    AppCompatImageView exploreBack, exploreSearch;
    private Integer search_visible;
    EditText exploreSearchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);

        // Visibility for toolbar
        explorePageTitle = (AppCompatTextView) findViewById(R.id.explorePageTitle);
        explorePageTitle.setVisibility(View.VISIBLE);
        exploreBack = (AppCompatImageView) findViewById(R.id.backArrow);
        exploreBack.setVisibility(View.VISIBLE);
        exploreSearch = (AppCompatImageView) findViewById(R.id.search_button);
        exploreSearch.setVisibility(View.VISIBLE);
        search_visible = 0;

        // Back Arrow
        exploreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExplorePage.this, MainMenu.class));
            }
        });
        // Search Button
        exploreSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(search_visible);
                switch (search_visible) {
                    case 0:
                        exploreSearchEdit.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        exploreSearchEdit.setVisibility(View.GONE);
                        break;
                }
                if (search_visible == 0) {search_visible = 1;}
                else {search_visible = 0;}
                System.out.println(search_visible);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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