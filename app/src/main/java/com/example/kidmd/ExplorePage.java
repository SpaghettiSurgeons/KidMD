package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExplorePage extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    AppCompatTextView explorePageTitle;
    AppCompatImageView exploreBack, exploreSearch;
    private Integer search_visible;
    EditText exploreSearchEdit;
    AppCompatButton populartool1, populartool2, populartool3, populartool4;
    AppCompatButton popularanatomy1, popularanatomy2, popularanatomy3, popularanatomy4;
    AppCompatButton popularprocedure1, popularprocedure2, popularprocedure3, popularprocedure4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        // popular tools buttons
        populartool1 = findViewById(R.id.populartool1);
        populartool1.setOnClickListener(this);
        populartool2 = findViewById(R.id.populartool2);
        populartool2.setOnClickListener(this);
        populartool3 = findViewById(R.id.populartool3);
        populartool3.setOnClickListener(this);
        populartool4 = findViewById(R.id.populartool4);
        populartool4.setOnClickListener(this);

        // popular anatomy buttons
        popularanatomy1 = findViewById(R.id.popularanatomy1);
        popularanatomy1.setOnClickListener(this);
        popularanatomy2 = findViewById(R.id.popularanatomy2);
        popularanatomy2.setOnClickListener(this);
        popularanatomy3 = findViewById(R.id.popularanatomy3);
        popularanatomy3.setOnClickListener(this);
        popularanatomy4 = findViewById(R.id.popularanatomy4);
        popularanatomy4.setOnClickListener(this);

        // popular procedures buttons
        popularprocedure1 = findViewById(R.id.popularprocedure1);
        popularprocedure1.setOnClickListener(this);
        popularprocedure2 = findViewById(R.id.popularprocedure2);
        popularprocedure2.setOnClickListener(this);
        popularprocedure3 = findViewById(R.id.popularprocedure3);
        popularprocedure3.setOnClickListener(this);
        popularprocedure4 = findViewById(R.id.popularprocedure4);
        popularprocedure4.setOnClickListener(this);


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

        //Changes for dark mode
        if (SpRetrieve("nightMode")){
            View menuview = findViewById(R.id.exploreBackground);
            TextView anatomy = findViewById(R.id.popAnatomy);
            TextView tools = findViewById(R.id.popTools);
            TextView procedures = findViewById(R.id.popProcedures);
            menuview.setBackgroundColor(Color.parseColor("#121212"));
            anatomy.setTextColor(Color.parseColor("#121212"));
            tools.setTextColor(Color.parseColor("#121212"));
            procedures.setTextColor(Color.parseColor("#121212"));
        }

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
            // popular tools buttons
            case R.id.populartool1:
                startActivity(new Intent(this, toAnesthesia.class));
                break;
            case R.id.populartool2:
                startActivity(new Intent(this, toForceps.class));
                break;
            case R.id.populartool3:
                startActivity(new Intent(this, toMri.class));
                break;
            case R.id.populartool4:
                startActivity(new Intent(this, toIv.class));
                break;

            // popular anatomy buttons
            case R.id.popularanatomy1:
                startActivity(new Intent(this, bpBrain.class));
                break;
            case R.id.popularanatomy2:
                startActivity(new Intent(this, bpHeart.class));
                break;
            case R.id.popularanatomy3:
                startActivity(new Intent(this, bpKidney.class));
                break;
            case R.id.popularanatomy4:
                startActivity(new Intent(this, bpLiver.class));
                break;

            // popular procedures buttons
            case R.id.popularprocedure1:
                startActivity(new Intent(this, prAppendectomy.class));
                break;
            case R.id.popularprocedure2:
                startActivity(new Intent(this, prCholecystectomy.class));
                break;
            case R.id.popularprocedure3:
                startActivity(new Intent(this, prNissen.class));
                break;
            case R.id.popularprocedure4:
                startActivity(new Intent(this, prDecortication.class));
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