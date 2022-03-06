package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prCholecystectomy extends AppCompatActivity {

    private ImageButton pageForward;
    private ImageButton xBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_cholecystectomy);

        //---page forward button---
        pageForward = (ImageButton) findViewById(R.id.cholPage1Next);
        pageForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholecystectomy.this, prCholecystectomy2.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.cholPage1X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholecystectomy.this, ProceduresList.class));
            }
        });
    }
}