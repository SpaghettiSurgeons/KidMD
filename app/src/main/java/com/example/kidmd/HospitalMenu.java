package com.example.kidmd;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HospitalMenu extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton photoButton, interactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_menu);

        //lock orientation to portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        photoButton = (AppCompatButton) findViewById(R.id.roomPhotos);
        photoButton.setOnClickListener(this);
        interactButton = (AppCompatButton) findViewById(R.id.interactButton);
        interactButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interactButton:
                startActivity(new Intent(HospitalMenu.this, HospitalInteract.class));
                break;
        }
    }
}