package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prAppendectomy2 extends AppCompatActivity {

    private ImageButton pageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_appendectomy2);

        //---page back button---
        pageBack = (ImageButton) findViewById(R.id.appPage2Back);
        pageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prAppendectomy2.this, prAppendectomy.class));
            }
        });
    }
}