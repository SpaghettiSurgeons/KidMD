package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton procedurebutton, hospitalbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Change text font
        //Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        //((TextView) findViewById(R.id.menudesc)).setTypeface(baloo);


        //initialize procedures button
        procedurebutton = (AppCompatButton) findViewById(R.id.procedurebutton);
        procedurebutton.setOnClickListener(this);
        hospitalbutton = (AppCompatButton) findViewById(R.id.hospitalbutton);
        hospitalbutton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.procedurebutton:
                startActivity(new Intent(MainMenu.this, ProceduresList.class));
                break;
            case R.id.hospitalbutton:
                startActivity(new Intent(this, LocateHospital.class));
                break;
        }
    }
}
