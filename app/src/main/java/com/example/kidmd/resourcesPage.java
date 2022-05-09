package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resourcesPage extends AppCompatActivity implements View.OnClickListener{

    // for top toolbar
    AppCompatTextView resourceTitle;
    AppCompatImageView resourceBack;

    // for bottom toolbar
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    TextView res1, res2, res3, res4;


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

        // Learn More
        res1 = findViewById(R.id.learnMore1);
        res2 = findViewById(R.id.learnMore2);
        res3 = findViewById(R.id.learnMore3);
        res4 = findViewById(R.id.learnMore4);

        res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.chop.edu/patients-and-visitors/guide-your-childs-surgery/preparing-your-child-surgery");
            }
        });
        res2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.aboutkidshealth.ca/Article?contentid=1166&language=English");
            }
        });
        res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.chop.edu/patients-and-visitors/guide-your-childs-surgery/your-childs-recovery-after-surgery");
            }
        });
        res4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.chop.edu/patients-and-visitors/guide-your-childs-surgery/coping-with-pain");
            }
        });

        //change font
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.resButton1)).setTypeface(baloo);
        ((TextView) findViewById(R.id.resButton2)).setTypeface(baloo);
        ((TextView) findViewById(R.id.resButton3)).setTypeface(baloo);
        ((TextView) findViewById(R.id.resButton4)).setTypeface(baloo);


    }

    // method to go to URL
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
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