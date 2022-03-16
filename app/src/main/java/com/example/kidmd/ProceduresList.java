package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ProceduresList extends AppCompatActivity implements View.OnClickListener{

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    ListView proceduresListView;
    AppCompatImageView procedureBack;


    EditText procedureSearch;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedures_list);

        procedureBack = (AppCompatImageView) findViewById(R.id.backArrow);
        procedureBack.setVisibility(View.VISIBLE);

        proceduresListView = (ListView) findViewById(R.id.prview);

        procedureSearch = findViewById(R.id.prSearch);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Appendectomy");
        arrayList.add("Cholecystectomy");
        arrayList.add("Choledochal Cyst");
        arrayList.add("Decortication");
        arrayList.add("Esophagomyotomy");
        arrayList.add("Gastroschisis");
        arrayList.add("Herniorraphy");
        arrayList.add("Hyperinsulinism");
        arrayList.add("Proctolectomy");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //Get the current item for ListView
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    //set background colors
                    view.setBackgroundColor(Color.rgb(173, 216, 230));
                    view.getBackground().setAlpha(220);
                } else {
                    view.setBackgroundColor(Color.rgb(33, 155, 163));
                    view.getBackground().setAlpha(100);

                }
                return view;
            }
        };
        proceduresListView.setAdapter(arrayAdapter);

        //add listener
        proceduresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //toast to display what item user clicked on
                //Toast.makeText(ProceduresList.this, "clicked: " + arrayList.get(i), Toast.LENGTH_SHORT).show();

                //open Appendectomy page
                if (arrayList.get(i) == "Appendectomy") startActivity(new Intent(ProceduresList.this, prAppendectomy.class));
                if (arrayList.get(i) == "Cholecystectomy") startActivity(new Intent(ProceduresList.this, prCholecystectomy.class));
                if (arrayList.get(i) == "Choledochal Cyst") startActivity(new Intent(ProceduresList.this, prCholedochalCyst.class));
                if (arrayList.get(i) == "Decortication") startActivity(new Intent(ProceduresList.this, prDecortication.class));
                if (arrayList.get(i) == "Esophagomyotomy") startActivity(new Intent(ProceduresList.this, prEsophagomyotomy.class));

            }
        });


        procedureSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // Back Arrow
        procedureBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProceduresList.this, MainMenu.class));
            }
        });


        // Bottom toolbar
        home_button = (AppCompatImageView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        explore_button = (AppCompatImageView) findViewById(R.id.explore_button);
        explore_button.setOnClickListener(this);
        notifications_button = (AppCompatImageView) findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(this);
        profile_button = (AppCompatImageView) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            /*case R.id.notifications_button:
                startActivity(new Intent(this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ?.class));
                break;*/
        }
    }
}

