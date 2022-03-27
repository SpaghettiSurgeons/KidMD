package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Locale;

public class ProgressPage extends AppCompatActivity {

    ProgressBar bp;
    ProgressBar pr;
    ProgressBar to;
    ProgressBar hr;
    ProgressBar overall;
    public String bpTrack,prTrack, hrTrack, toTrack;
    public int bpNum, prNum, hrNum, toNum, all;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_page);


        bp = findViewById(R.id.anatomyBar);
        pr = findViewById(R.id.procedureBar);
        to = findViewById(R.id.toolsBar);
        hr = findViewById(R.id.hospitalBar);
        overall = findViewById(R.id.overallBar);

        bp.setMax(10);
        pr.setMax(5);
        to.setMax(10);
        hr.setMax(7);
        overall.setMax(32);


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
                            bpNum = bpTrack.length() - bpTrack.replaceAll("\\.","").length();
                            prNum = prTrack.length() - prTrack.replaceAll("\\.","").length();
                            toNum = toTrack.length() - toTrack.replaceAll("\\.","").length();
                            hrNum = hrTrack.length() - hrTrack.replaceAll("\\.","").length();
                            all = bpNum + prNum + toNum + hrNum;
                            bp.setProgress(bpNum);
                            pr.setProgress(prNum);
                            to.setProgress(toNum);
                            hr.setProgress(hrNum);
                            overall.setProgress(all);
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

    }
}