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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProceduresList extends AppCompatActivity implements View.OnClickListener{

    // Search
    ListView proceduresListView;
    EditText procedureSearchEdit;
    private Integer search_visible;
    DatabaseReference reference;
    String prTrack;

    AppCompatTextView procedureTitle;
    AppCompatImageView procedureBack, procedureSearch;
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedures_list);

        //retrieve progress data
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userID = user.getUid();
            reference = FirebaseDatabase.getInstance().getReference("Progress").child(userID);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        ProgressTrack track = dataSnapshot.getValue(ProgressTrack.class);
                        if (track != null){
                            prTrack = track.pr;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ProceduresList.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}

        // Search
        proceduresListView = (ListView) findViewById(R.id.prview);
        procedureSearchEdit = findViewById(R.id.prSearch);

        // Visibility for toolbar
        procedureTitle = (AppCompatTextView) findViewById(R.id.procedureTitle);
        procedureTitle.setVisibility(View.VISIBLE);
        procedureBack = (AppCompatImageView) findViewById(R.id.backArrow);
        procedureBack.setVisibility(View.VISIBLE);
        procedureSearch = (AppCompatImageView) findViewById(R.id.search_button);
        procedureSearch.setVisibility(View.VISIBLE);
        search_visible = 0;

        // Set up array for ListView
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Appendectomy");
        arrayList.add("Cholecystectomy");
        arrayList.add("Choledochal Cyst");
        arrayList.add("Decortication");
        arrayList.add("Esophagomyotomy");
        arrayList.add("Gastroschisis");
        arrayList.add("Herniorraphy");
        arrayList.add("Hyperinsulinism");
        arrayList.add("Proctocolectomy");


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
                if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
                    if (!prTrack.contains(arrayList.get(i))) {
                        prTrack = prTrack + "." + arrayList.get(i);
                        reference.child("pr").setValue(prTrack);
                    }
                }
                //open Appendectomy page
                if (arrayList.get(i) == "Appendectomy") startActivity(new Intent(ProceduresList.this, prAppendectomy.class));
                if (arrayList.get(i) == "Cholecystectomy") startActivity(new Intent(ProceduresList.this, prCholecystectomy.class));
                if (arrayList.get(i) == "Choledochal Cyst") startActivity(new Intent(ProceduresList.this, prCholedochalCyst.class));
                if (arrayList.get(i) == "Decortication") startActivity(new Intent(ProceduresList.this, prDecortication.class));
                if (arrayList.get(i) == "Esophagomyotomy") startActivity(new Intent(ProceduresList.this, prEsophagomyotomy.class));
                if (arrayList.get(i) == "Herniorraphy") startActivity(new Intent(ProceduresList.this, prHerniorraphy.class));
                if (arrayList.get(i) == "Hyperinsulinism") startActivity(new Intent(ProceduresList.this, prHyperinsulinism.class));
                if (arrayList.get(i) == "Proctocolectomy") startActivity(new Intent(ProceduresList.this, prProctocolectomy.class));



            }
        });

        // Back Arrow
        procedureBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProceduresList.this, MainMenu.class));
            }
        });
        // Search Button
        procedureSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(search_visible);
                switch (search_visible) {
                    case 0:
                        procedureSearchEdit.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        procedureSearchEdit.setVisibility(View.GONE);
                        break;
                }
                if (search_visible == 0) {search_visible = 1;}
                else {search_visible = 0;}
                System.out.println(search_visible);
            }
        });

        procedureSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) { arrayAdapter.getFilter().filter(s); }
            @Override
            public void afterTextChanged(Editable editable) { }
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

