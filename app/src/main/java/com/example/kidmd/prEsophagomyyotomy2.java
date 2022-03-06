package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prEsophagomyyotomy2 extends AppCompatActivity {

    private ImageButton pageBack;
    private ImageButton xBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_esophagomyyotomy2);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.esophPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prEsophagomyyotomy2.this, prEsophagomyotomy.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.esophPage2X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prEsophagomyyotomy2.this, ProceduresList.class));
            }
        });
    }
}