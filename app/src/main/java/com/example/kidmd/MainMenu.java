package com.example.kidmd;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton procedurebutton, hospitalbutton, toolsbutton, bodypartbutton, logoutbutton;
    private AppCompatTextView title;
    private AppCompatImageView profile;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Change text font
        Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");
        ((TextView) findViewById(R.id.menudesc)).setTypeface(baloo);

        //Typeface baloo = Typeface.createFromAsset(getAssets(), "fonts/Baloo-Regular.ttf");


        //initialize procedures button
        procedurebutton = (AppCompatButton) findViewById(R.id.procedurebutton);
        procedurebutton.setOnClickListener(this);
        hospitalbutton = (AppCompatButton) findViewById(R.id.hospitalbutton);
        hospitalbutton.setOnClickListener(this);
        toolsbutton = (AppCompatButton) findViewById(R.id.toolsbutton);
        toolsbutton.setOnClickListener(this);
        bodypartbutton= (AppCompatButton) findViewById(R.id.bodypartbutton);
        bodypartbutton.setOnClickListener(this);
        logoutbutton= (AppCompatButton) findViewById(R.id.logout);
        logoutbutton.setOnClickListener(this);

        // Visibility for toolbar
        title = (AppCompatTextView) findViewById(R.id.appTitle_toolbar);
        title.setVisibility(View.VISIBLE);

        profile = (AppCompatImageView) findViewById(R.id.profile_button);
        profile.setOnClickListener(this);


        //Only show logout button and profile if user is logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            logoutbutton.setVisibility(View.GONE);
            profile.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.procedurebutton:
                startActivity(new Intent(MainMenu.this, ProceduresList.class));
                break;
            case R.id.hospitalbutton:
                startActivity(new Intent(this, HospitalMenu.class));
                break;
            case R.id.toolsbutton:
                startActivity(new Intent(MainMenu.this, ToolsList.class));
                break;
            case R.id.bodypartbutton:
                startActivity(new Intent(MainMenu.this, BodyPartList.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu.this, MainActivity.class));
                break;
        }
    }
}
