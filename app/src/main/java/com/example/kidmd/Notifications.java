package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
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

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        createNotificationChannel();

        Button buttonShowNotification = findViewById(R.id.testNotification1);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifications.this, "My Notification");
        builder.setContentTitle("KidMD: You're halfway there!");
        builder.setContentText("You've learned about over half of the human body, log in now to learn more");
        builder.setSmallIcon(R.drawable.logo);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Notifications.this);

        buttonShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerCompat.notify(100, builder.build());

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


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notifchannel";
            String description = "Channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("My Notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
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





/****

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }




        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifications.this, "My Notification");
        builder.setContentTitle("KidMD: Halfway there!");
        builder.setContentText("Anatomy list Progress: ");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Notifications.this);
        managerCompat.notify(1, builder.build());





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
                            //Toast.makeText(ProgressPage.this, hrTrack, Toast.LENGTH_LONG).show();
                        }
                        //else Toast.makeText(ProgressPage.this, "track is null", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Notifications.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}


}

****/