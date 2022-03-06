package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class prAppendectomy extends AppCompatActivity {

    private ImageButton pageForward;
    private ImageButton xBack;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_appendectomy);

        //---page forward button---
        pageForward = (ImageButton) findViewById(R.id.appPage1Next);
        pageForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prAppendectomy.this, prAppendectomy2.class));
            }
        });


        //---page X button---
        xBack = (ImageButton) findViewById(R.id.appPage1X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prAppendectomy.this, ProceduresList.class));
            }
        });
    }
}