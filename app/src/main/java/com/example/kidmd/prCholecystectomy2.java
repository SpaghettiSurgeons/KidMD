package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prCholecystectomy2 extends AppCompatActivity {

    private ImageButton pageBack;
    private ImageButton xBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_cholecystectomy2);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.cholPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholecystectomy2.this, prCholecystectomy.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.cholPage2X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholecystectomy2.this, ProceduresList.class));
            }
        });
    }
}