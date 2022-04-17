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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToolsList extends AppCompatActivity implements View.OnClickListener {

    ListView toolsListView;
    EditText toolSearch;
    private ImageButton backArrow;
    DatabaseReference reference;
    String toTrack;
    EditText toolSearchEdit;
    private Integer search_visible;

    AppCompatTextView toolsTitle;
    AppCompatImageView toolsBack, toolsSearch;

    // Bottom Toolbar
    private AppCompatImageView home_button, explore_button, notifications_button, profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_list);

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
                            toTrack = track.to;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ToolsList.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}

        toolsListView = (ListView) findViewById(R.id.toolsTextView);
        toolSearchEdit = findViewById(R.id.toolSearch);

        // Visibility for toolbar
        toolsTitle = (AppCompatTextView) findViewById(R.id.toolsTitle);
        toolsTitle.setVisibility(View.VISIBLE);
        toolsBack = (AppCompatImageView) findViewById(R.id.backArrow);
        toolsBack.setVisibility(View.VISIBLE);
        toolsSearch = (AppCompatImageView) findViewById(R.id.search_button);
        toolsSearch.setVisibility(View.VISIBLE);
        search_visible = 0;

        // Set up array for ListView
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Forceps");
        arrayList.add("Suction Tube");
        arrayList.add("Diathermy");
        arrayList.add("Retractors");
        arrayList.add("Sutures");
        arrayList.add("Defibrillators");
        arrayList.add("Anesthesia");
        arrayList.add("Stethoscope");
        arrayList.add("Sphygmomanometer");
        arrayList.add("Pen Torch");
        arrayList.add("X-rays");
        arrayList.add("CT Scan");
        arrayList.add("MRI");
        arrayList.add("IV");
        arrayList.add("Spirometer");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //Get the current item for ListView
                View view = super.getView(position, convertView, parent);
                if(position %2 == 1){
                    //set background colors
                    view.setBackgroundColor(Color.rgb(173,216,230));
                    view.getBackground().setAlpha(220);
                }
                else{
                    view.setBackgroundColor(Color.rgb(33, 155, 163));
                    view.getBackground().setAlpha(100);

                }
                return view;
            }
        };
        toolsListView.setAdapter(arrayAdapter);

        //add listener
        toolsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ToolsList.this, "clicked: " + arrayList.get(i), Toast.LENGTH_SHORT).show();

                //see if page has been visited before and log it if it hasn't
                if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
                    if (!toTrack.contains(arrayList.get(i))) {
                        toTrack = toTrack + "." + arrayList.get(i);
                        reference.child("to").setValue(toTrack);
                    }
                }
                switch ((String)adapterView.getItemAtPosition(i)) {
                    case "Forceps":
                        startActivity(new Intent(ToolsList.this, toForceps.class));
                        break;
                    case "Retractors":
                        startActivity(new Intent(ToolsList.this, toRetractors.class));
                        break;
                    case "Suction Tube":
                        startActivity(new Intent(ToolsList.this, toSuctionTube.class));
                        break;
                    case "Diathermy":
                        startActivity(new Intent(ToolsList.this, toDiathermy.class));
                        break;
                    case "Sutures":
                        startActivity(new Intent(ToolsList.this, toSutures.class));
                        break;
                    case "Defibrillators":
                        startActivity(new Intent(ToolsList.this, toDefibrillators.class));
                        break;
                    case "Anesthesia":
                        startActivity(new Intent(ToolsList.this, toAnesthesia.class));
                        break;
                    case "Stethoscope":
                        startActivity(new Intent(ToolsList.this, toStethoscope.class));
                        break;
                    case "Sphygmomanometer":
                        startActivity(new Intent(ToolsList.this, toSphygmomanometer.class));
                        break;
                    case "Pen Torch":
                        startActivity(new Intent(ToolsList.this, toPenTorch.class));
                        break;
                    case "X-rays":
                        startActivity(new Intent(ToolsList.this, toXray.class));
                        break;
                    case "CT Scan":
                        startActivity(new Intent(ToolsList.this, toCtscan.class));
                        break;
                    case "MRI":
                        startActivity(new Intent(ToolsList.this, toMri.class));
                        break;
                    case "IV":
                        startActivity(new Intent(ToolsList.this, toIv.class));
                        break;
                    case "Spirometer":
                        startActivity(new Intent(ToolsList.this, toSpirometer.class));
                        break;
                }
            }
        });

        toolSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) { arrayAdapter.getFilter().filter(s); }
            @Override
            public void afterTextChanged(Editable editable) { }
        });

        // Back Arrow
        toolsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToolsList.this, MainMenu.class));
            }
        });
        // Search Button
        toolsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(search_visible);
                switch (search_visible) {
                    case 0:
                        toolSearchEdit.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        toolSearchEdit.setVisibility(View.GONE);
                        break;
                }
                if (search_visible == 0) {search_visible = 1;}
                else {search_visible = 0;}
                System.out.println(search_visible);
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
    public void onClick(View view) {
        switch (view.getId()) {
            // bottom toolbar
            case R.id.home_button:
                startActivity(new Intent(this, MainMenu.class));
                break;
            case R.id.explore_button:
                startActivity(new Intent(this, ExplorePage.class));
                break;
            /*case R.id.notifications_button:
                startActivity(new Intent(ProceduresList.this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(ProceduresList.this, ?.class));
                break;*/
        }
    }
}