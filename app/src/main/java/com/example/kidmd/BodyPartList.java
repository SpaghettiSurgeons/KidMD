package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;

public class BodyPartList extends AppCompatActivity implements View.OnClickListener {

    // Search
    ListView anatomyListView;
    EditText anatomySearchEdit;
    String clicked;
    String bpTrack;
    DatabaseReference reference;
    private Integer search_visible;

    AppCompatTextView anatomyTitle;
    AppCompatImageView anatomyBack, anatomySearch;
    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Progress");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodypart_list);

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
                            bpTrack = track.bp;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(BodyPartList.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });}

        // Search
        anatomyListView = (ListView) findViewById(R.id.bprview);
        anatomySearchEdit = findViewById(R.id.bpSearch);

        // Visibility for toolbar
        anatomyTitle = (AppCompatTextView) findViewById(R.id.anatomyTitle);
        anatomyTitle.setVisibility(View.VISIBLE);
        anatomyBack = (AppCompatImageView) findViewById(R.id.backArrow);
        anatomyBack.setVisibility(View.VISIBLE);
        anatomySearch = (AppCompatImageView) findViewById(R.id.search_button);
        anatomySearch.setVisibility(View.VISIBLE);
        search_visible = 0;

        // Set up array for ListView
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Appendix");
        arrayList.add("Brain");
        arrayList.add("Clavicle");
        arrayList.add("Distal Radius");
        arrayList.add("Gallbladder");
        arrayList.add("Heart");
        arrayList.add("Kidney");
        arrayList.add("Large Intestine");
        arrayList.add("Liver");
        arrayList.add("Lungs");
        arrayList.add("Pancreas");
        arrayList.add("Small Intestine");
        arrayList.add("Spine");
        arrayList.add("Spleen");
        arrayList.add("Stomach");
        arrayList.add("Tonsils");

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
        anatomyListView.setAdapter(arrayAdapter);

        //add listener
        anatomyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(BodyPartList.this, "clicked: " + arrayList.get(i), Toast.LENGTH_SHORT).show();

                //see if page has been visited before and log it if it hasn't
                if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
                    if (!bpTrack.contains(arrayList.get(i))) {
                        bpTrack = bpTrack + "." + arrayList.get(i);
                        reference.child("bp").setValue(bpTrack);
                    }
                }

                //open body part activity
                switch ((String)adapterView.getItemAtPosition(i)) {
                    case "Appendix":
                        startActivity(new Intent(BodyPartList.this, bpAppendeix.class));
                        break;
                    case "Brain":
                        startActivity(new Intent(BodyPartList.this, bpBrain.class));
                        break;
                    case "Clavicle":
                        startActivity(new Intent(BodyPartList.this, bpClavicle.class));
                        break;
                    case "Gallbladder":
                        startActivity(new Intent(BodyPartList.this, bpGallbladder.class));
                        break;
                    case "Heart":
                        startActivity(new Intent(BodyPartList.this, bpHeart.class));
                        break;
                    case "Kidney":
                        startActivity(new Intent(BodyPartList.this, bpKidney.class));
                        break;
                    case "Large Intestine":
                        startActivity(new Intent(BodyPartList.this, bpLargeIntestine.class));
                        break;
                    case "Liver":
                        startActivity(new Intent(BodyPartList.this, bpLiver.class));
                        break;
                    case "Pancreas":
                        startActivity(new Intent(BodyPartList.this, bpPancreas.class));
                        break;
                    case "Tonsils":
                        startActivity(new Intent(BodyPartList.this, bpTonsils.class));
                        break;
                    case "Lungs":
                        startActivity(new Intent(BodyPartList.this, bpLungs.class));
                        break;
                    case "Spine":
                        startActivity(new Intent(BodyPartList.this, bpSpine.class));
                        break;
                    case "Small Intestine":
                        startActivity(new Intent(BodyPartList.this, bpSmallIntestine.class));
                        break;
                    case "Spleen":
                        startActivity(new Intent(BodyPartList.this, bpSpleen.class));
                        break;
                    case "Stomach":
                        startActivity(new Intent(BodyPartList.this, bpStomach.class));
                        break;
                    case "Distal Radius":
                        startActivity(new Intent(BodyPartList.this, bpDistalRadius.class));
                        break;
                }

            }
        });

        // Back Arrow
        anatomyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BodyPartList.this, MainMenu.class));
            }
        });
        // Search Button
        anatomySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(search_visible);
                switch (search_visible) {
                    case 0:
                        anatomySearchEdit.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        anatomySearchEdit.setVisibility(View.GONE);
                        break;
                }
                if (search_visible == 0) {search_visible = 1;}
                else {search_visible = 0;}
                System.out.println(search_visible);
            }
        });

        anatomySearchEdit.addTextChangedListener(new TextWatcher() {
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
