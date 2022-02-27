package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prCholedochalCyst2 extends AppCompatActivity {

    private ImageButton pageBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_choledochal_cyst2);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.cholCystPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prCholedochalCyst2.this, prCholedochalCyst.class));
            }
        });
    }
}