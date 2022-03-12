package com.example.kidmd;

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

public class HospitalInteract extends AppCompatActivity {

    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialog;
    private TextView bedDesc;
    private Button test;
    private ImageButton bedButton, bpmButton, curtainButton, sudButton, ecgButton, otoscopeButton, opthalmoscopeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_interact);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

    }


    public void onClick(View v) {
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