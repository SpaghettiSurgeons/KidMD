package com.example.kidmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class prDecortication extends AppCompatActivity {

    private ImageButton pageForward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_decortication);


        //---page forward button---
        pageForward = (ImageButton) findViewById(R.id.decPage1Next);
        pageForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(prDecortication.this, prDecortication2.class));
            }
        });




    }
}