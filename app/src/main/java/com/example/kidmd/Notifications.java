package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Notifications extends AppCompatActivity implements View.OnClickListener{

    Button anatomy50, anatomy100, procedures50, procedures100, tools50, tools100, signInNotif;
    // Bottom Toolbar
    AppCompatImageView home_button, explore_button, profile_button, notifications_button;
    // Top Toolbar
    AppCompatTextView notificationsTitle;
    AppCompatImageView backButton;
    // User progress related
    ProgressBar bp;
    ProgressBar pr;
    ProgressBar to;
    ProgressBar hr;
    ProgressBar overall;
    public int bpMax, prMax, toMax, hrMax, overallMax;
    public String bpTrack,prTrack, hrTrack, toTrack;
    public int bpNum, prNum, hrNum, toNum, all;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        // maximum number of pages for each
        bpMax = 13;
        prMax = 9;
        toMax = 15;
        hrMax = 7;
        overallMax = bpMax + prMax + toMax + hrMax;

        //retrieve progress data for notifications
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

                            /** Anatomy list notifs**/
                            // display notification if 100% or over 50% of anatomy list viewed
                            if (bpNum > bpMax/2){
                                // check if list is 100% finished
                                if(bpNum == bpMax){
                                    // Visibility for 100% notification
                                    anatomy100 = findViewById(R.id.anatomy100);
                                    anatomy100.setVisibility(View.VISIBLE);
                                    anatomy100.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(Notifications.this, ProgressPage.class));
                                        }
                                    });
                                }
                                else {
                                    // Visibility for 50% notification
                                    anatomy50 = findViewById(R.id.anatomy50);
                                    anatomy50.setVisibility(View.VISIBLE);
                                    anatomy50.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(Notifications.this, BodyPartList.class));
                                        }
                                    });
                                }
                            }
                            /** Procedures list notifs**/
                            // display notification if 100% or over 50% of tools list viewed
                            if (prNum > prMax/2){
                                // check if list is 100% finished
                                if(prNum == prMax){
                                    // Visibility for 100% notification
                                    procedures100 = findViewById(R.id.procedures100);
                                    procedures100.setVisibility(View.VISIBLE);
                                    procedures100.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(Notifications.this, ProgressPage.class));
                                        }
                                    });
                                }
                                // Visibility for 50% notification
                                procedures50 = findViewById(R.id.procedures50);
                                procedures50.setVisibility(View.VISIBLE);
                                procedures50.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Notifications.this, ProceduresList.class));
                                    }
                                });
                            }
                            /** Tools list notifs**/
                            // display notification if 100% or over 50% of tools list viewed
                            if (toNum > toMax/2){
                                // check if list is 100% finished
                                if(toNum == toMax){
                                    // Visibility for 100% notification
                                    tools100 = findViewById(R.id.tools100);
                                    tools100.setVisibility(View.VISIBLE);
                                    tools100.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(Notifications.this, ProgressPage.class));
                                        }
                                    });
                                }
                                // Visibility for 50% notification
                                tools50 = findViewById(R.id.tools50);
                                tools50.setVisibility(View.VISIBLE);
                                tools50.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Notifications.this, ToolsList.class));
                                    }
                                });
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Notifications.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}

        // if user is not found, prompt them to sign in or create an account
        else {
            // Visibility for notification
            signInNotif = findViewById(R.id.signInNotif);
            signInNotif.setVisibility(View.VISIBLE);
            signInNotif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Notifications.this, MainActivity.class));
                }
            });



        }


        // Visibility for toolbar
        notificationsTitle = (AppCompatTextView) findViewById(R.id.notificationsTitle);
        notificationsTitle.setVisibility(View.VISIBLE);
        backButton = (AppCompatImageView) findViewById(R.id.backArrow);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(this);

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
            // Toolbar
            case R.id.backArrow:
                startActivity(new Intent(this, MainMenu.class));

            // Bottom Toolbar
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
