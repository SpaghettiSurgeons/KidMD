package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prEsophagomyotomy extends AppCompatActivity {

    private ImageButton pageForward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_esophagomyotomy);

        //---page forward button---
        pageForward = (ImageButton) findViewById(R.id.esophPage1Next);
        pageForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prEsophagomyotomy.this, prEsophagomyyotomy2.class));
            }
        });
    }
}