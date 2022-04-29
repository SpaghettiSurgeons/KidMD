package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Locale;

public class ProgressPage extends AppCompatActivity  implements View.OnClickListener{

    ProgressBar bp;
    ProgressBar pr;
    ProgressBar to;
    ProgressBar hr;
    ProgressBar overall;
    TextView bpHeader, prHeader, toHeader, hrHeader, overallHeader;
    public int bpMax, prMax, toMax, hrMax, overallMax;
    public String bpTrack,prTrack, hrTrack, toTrack;
    public int bpNum, prNum, hrNum, toNum, all;
    DatabaseReference reference;
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_page);

        bpMax = 13;
        prMax = 9;
        toMax = 15;
        hrMax = 7;
        overallMax = bpMax + prMax + toMax + hrMax;

        bpHeader = findViewById(R.id.bpHeader);
        prHeader = findViewById(R.id.prHeader);
        toHeader = findViewById(R.id.toHeader);
        hrHeader = findViewById(R.id.hrHeader);
        overallHeader = findViewById(R.id.overallHeader);

        bp = findViewById(R.id.anatomyBar);
        pr = findViewById(R.id.procedureBar);
        to = findViewById(R.id.toolsBar);
        hr = findViewById(R.id.hospitalBar);
        overall = findViewById(R.id.overallBar);

        //Set amount of activities per section
        bp.setMax(bpMax);
        pr.setMax(prMax);
        to.setMax(toMax);
        hr.setMax(hrMax);
        overall.setMax(overallMax);

        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");

        //retrieve progress data
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userID = user.getUid();
            reference = FirebaseDatabase.getInstance().getReference("Progress").child(userID);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        ProgressTrack track = dataSnapshot.getValue(ProgressTrack.class);
                        if (track != null){
                            bpTrack = track.bp;
                            prTrack = track.pr;
                            toTrack = track.to;
                            hrTrack = track.hr;

                            //count up how many unique pages were viewed
                            bpNum = bpTrack.length() - bpTrack.replaceAll("\\.","").length();
                            prNum = prTrack.length() - prTrack.replaceAll("\\.","").length();
                            toNum = toTrack.length() - toTrack.replaceAll("\\.","").length();
                            hrNum = hrTrack.length() - hrTrack.replaceAll("\\.","").length();

                            //get total for overall progress
                            all = bpNum + prNum + toNum + hrNum;
                            //set progress to progress bars
                            bp.setProgress(bpNum);
                            pr.setProgress(prNum);
                            to.setProgress(toNum);
                            hr.setProgress(hrNum);
                            overall.setProgress(all);

                            //change text percentages in header
                            bpHeader.setText("Anatomy: " + String.format("%.0f", (Double.valueOf(bpNum)/Double.valueOf(bpMax))*100) + "%");
                            prHeader.setText("Procedures: " + String.format("%.0f", (Double.valueOf(prNum)/Double.valueOf(prMax))*100) + "%");
                            toHeader.setText("Tools: " + String.format("%.0f", (Double.valueOf(toNum)/Double.valueOf(toMax))*100) + "%");
                            hrHeader.setText("Hospital: " + String.format("%.0f", (Double.valueOf(hrNum)/Double.valueOf(hrMax))*100) + "%");
                            overallHeader.setText("Overall Progress: " + String.format("%.0f", (Double.valueOf(all)/Double.valueOf(overallMax))*100) + "%");
                            //Toast.makeText(ProgressPage.this, hrTrack, Toast.LENGTH_LONG).show();
                        }
                        //else Toast.makeText(ProgressPage.this, "track is null", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ProgressPage.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}
        (bpHeader).setTypeface(baloo);
        (prHeader).setTypeface(baloo);
        (toHeader).setTypeface(baloo);
        (hrHeader).setTypeface(baloo);
        (overallHeader).setTypeface(baloo);

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
            case R.id.notifications_button:
                startActivity(new Intent(this, Notifications.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }
}