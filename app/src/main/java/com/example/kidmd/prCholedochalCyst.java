package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prCholedochalCyst extends AppCompatActivity {

    private ImageButton pageForward;

    private ImageButton xBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_choledochal_cyst);

        //---page forward button---
        pageForward = (ImageButton) findViewById(R.id.cholCystPage1Next);
        pageForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholedochalCyst.this, prCholedochalCyst2.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.cholCystPage1X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholedochalCyst.this, ProceduresList.class));
            }
        });
    }
}