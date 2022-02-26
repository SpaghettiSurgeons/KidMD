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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class HospitalInteract extends AppCompatActivity {

    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialog;
    private TextView bedDesc;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_interact);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        test = (Button) findViewById(R.id.testButton);
        test.setOnClickListener(this::onClick);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testButton:
                seeBed();
                break;
        }
    }

    public void seeBed(){
        dialogueBuilder = new AlertDialog.Builder(this);
        final View bedPopUpView = getLayoutInflater().inflate(R.layout.activity_hr_bed, null);
        dialogueBuilder.setView(bedPopUpView);
        dialog = dialogueBuilder.create();
        dialog.show();

    }
}