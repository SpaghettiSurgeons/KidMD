package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalInteract extends AppCompatActivity {

    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialog;
    private TextView bedDesc;
    private Button test;
    DatabaseReference reference;
    String hrTrack;
    private ImageButton bedButton, bpmButton, curtainButton, sudButton, ecgButton, otoscopeButton, opthalmoscopeButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_interact);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
                            hrTrack = track.hr;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(HospitalInteract.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}

        bedButton = (ImageButton) findViewById(R.id.bedButton);
        bedButton.setOnClickListener(this::onClick);
        bpmButton = (ImageButton) findViewById(R.id.bpmButton);
        bpmButton.setOnClickListener(this::onClick);
        curtainButton = (ImageButton) findViewById(R.id.curtainButton);
        curtainButton.setOnClickListener(this::onClick);
        sudButton = (ImageButton) findViewById(R.id.sudButton);
        sudButton.setOnClickListener(this::onClick);
        ecgButton = (ImageButton) findViewById(R.id.ecgButton);
        ecgButton.setOnClickListener(this::onClick);
        otoscopeButton = (ImageButton) findViewById(R.id.otoscopeButton);
        otoscopeButton.setOnClickListener(this::onClick);
        opthalmoscopeButton = (ImageButton) findViewById(R.id.opthalmoscopeButton);
        opthalmoscopeButton.setOnClickListener(this::onClick);
        backButton = (ImageButton) findViewById(R.id.hospitalBack);
        backButton.setOnClickListener(this::onClick);

    }


    public void onClick(View v) {

        //log newly visited entries to database
        if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
            String fullName = getResources().getResourceName(v.getId());
            String name = fullName.substring(fullName.lastIndexOf("/") + 1);
            if (!hrTrack.contains(name)) {
                hrTrack = hrTrack + "." + name;
                reference.child("hr").setValue(hrTrack);
            }
        }
        switch (v.getId()) {
            case R.id.bedButton:
                startActivity(new Intent(HospitalInteract.this, hrBed.class));
                break;
            case R.id.bpmButton:
                startActivity(new Intent(HospitalInteract.this, hrBPM.class));
                break;
            case R.id.curtainButton:
                startActivity(new Intent(HospitalInteract.this, hrCurtain.class));
                break;
            case R.id.sudButton:
                startActivity(new Intent(HospitalInteract.this, hrSUD.class));
                break;
            case R.id.ecgButton:
                //seeBPM();
                startActivity(new Intent(HospitalInteract.this, hrECG.class));
                break;
            case R.id.otoscopeButton:
                //seeBPM();
                startActivity(new Intent(HospitalInteract.this, hrOtoscope.class));
                break;
            case R.id.opthalmoscopeButton:
                //seeBPM();
                startActivity(new Intent(HospitalInteract.this, hrOpthalmoscope.class));
            case R.id.hospitalBack:
                //seeBPM();
                startActivity(new Intent(HospitalInteract.this, HospitalMenu.class));
                break;
        }
    }

    public void seeBed(){
        //Create dialogue for bed
        dialogueBuilder = new AlertDialog.Builder(this);
        final View bedPopUpView = getLayoutInflater().inflate(R.layout.activity_hr_bed, null);
        dialogueBuilder.setView(bedPopUpView);
        dialog = dialogueBuilder.create();
        //Set window parameters to wrap content
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        //startActivity(new Intent(HospitalInteract.this, hrBed.class));;

    }
    public void seeBPM(){
        //Create dialogue for bed
        dialogueBuilder = new AlertDialog.Builder(this);
        final View bedPopUpView = getLayoutInflater().inflate(R.layout.activity_hr_bpm, null);
        dialogueBuilder.setView(bedPopUpView);
        dialog = dialogueBuilder.create();
        //Set window parameters to wrap content
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        //startActivity(new Intent(HospitalInteract.this, hrBed.class));;

    }
}