package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prDecortication2 extends AppCompatActivity {


    private ImageButton pageBack;
    private ImageButton xBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_decortication2);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.decorPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prDecortication2.this, prDecortication.class));
            }
        });

        //---page X button---
        xBack = (ImageButton) findViewById(R.id.decorPage2X);
        xBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prDecortication2.this, ProceduresList.class));
            }
        });
    }

}