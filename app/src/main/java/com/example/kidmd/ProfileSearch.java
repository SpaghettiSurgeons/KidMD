package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//This file and its XML counterpart is an attempt at changing some aspects of the Profile Search
// functionality, which we were ultimately unable to finish in time, and so went with the original,
// which we renamed Profile Search 2.
public class ProfileSearch extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;


    public void checkKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private AppCompatImageButton addFriend;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private String visit_user_id;

    private AppCompatEditText searchField;
    private AppCompatImageButton searchBtn;

    private String currentUserEmail;
    ListView profilesListView;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<User> userArrayList = new ArrayList<>();



        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView ageTextView = (TextView) findViewById(R.id.age);

//        final TextView fullNameTitleTextView = (TextView) findViewById(R.id.fullNameTitle);
//        final TextView emailTitleTextView = (TextView) findViewById(R.id.emailAddressTitle);
//        final TextView ageTitleTextView = (TextView) findViewById(R.id.ageTitle);
//
//        //Set title fields invisible until profile is found
//        fullNameTitleTextView.setVisibility(View.GONE);
//        emailTitleTextView.setVisibility(View.GONE);
//        ageTitleTextView.setVisibility(View.GONE);

        addFriend = (AppCompatImageButton) findViewById(R.id.addFriend);
        searchField = findViewById(R.id.search_field);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        searchBtn = (AppCompatImageButton) findViewById(R.id.search_btn);

        profilesListView = (ListView) findViewById(R.id.profilesTextView);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            userID = user.getUid();

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userProfile = dataSnapshot.getValue(User.class);
                    if (userProfile != null) {
                        currentUserEmail = userProfile.email;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ProfileSearch.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            currentUserEmail = "";
        }

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Iterate through user profiles in realtime database
                for (DataSnapshot ds : snapshot.getChildren()) {
                    arrayList.add(ds.child("fullName").getValue().toString());
                    User newUser = new User(ds.child("fullName").getValue().toString(), ds.child("age").getValue().toString(), ds.child("email").getValue().toString());
                    newUser.setUid(ds.getRef().getKey());
                    userArrayList.add(newUser);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList){
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
        profilesListView.setAdapter(arrayAdapter);

        //add listener
        profilesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkKeyboard();
                profilesListView.setVisibility(View.GONE);
                searchBtn.setEnabled(false);
                //Populate fields with found data
                for (User u: userArrayList) {
                    if (u.fullName.toLowerCase().equals(((String)adapterView.getItemAtPosition(i)).toLowerCase())) {
//                        fullNameTitleTextView.setVisibility(View.VISIBLE);
//                        emailTitleTextView.setVisibility(View.VISIBLE);
//                        ageTitleTextView.setVisibility(View.VISIBLE);
                        selectedUser = u;
                        fullNameTextView.setText((String)adapterView.getItemAtPosition(i));
                        emailTextView.setText(selectedUser.getEmail());
                        ageTextView.setText(selectedUser.getAge());
                        visit_user_id = selectedUser.getUid();
                        break;
                    }
                }
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                profilesListView.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!searchBtn.isEnabled()) {
                    searchBtn.setEnabled(true);
                }
                profilesListView.setVisibility(View.VISIBLE);
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKeyboard();
                profilesListView.setVisibility(View.GONE);
                String searchText = searchField.getText().toString().toLowerCase();

                reference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Iterate through user profiles in realtime database
                        for (DataSnapshot ds: snapshot.getChildren()) {
                            //Make sure the name matches and the match is not the same as the current user
                            if (ds.child("fullName").getValue().toString().toLowerCase().contains(searchText) && !currentUserEmail.equals(ds.child("email").getValue()) && !searchText.equals("")) {
                                //Make titles visible now that a profile has been found
//                                fullNameTitleTextView.setVisibility(View.VISIBLE);
//                                emailTitleTextView.setVisibility(View.VISIBLE);
//                                ageTitleTextView.setVisibility(View.VISIBLE);
                                //Populate fields with found data
                                //visit_user_id = ds.child("uid").getValue(String.class);
                                visit_user_id = ds.getRef().getKey();
                                fullNameTextView.setText(ds.child("fullName").getValue(String.class));
                                emailTextView.setText(ds.child("email").getValue(String.class));
                                ageTextView.setText(ds.child("age").getValue(String.class));
                                break;
                            }
                            else {
//                                fullNameTitleTextView.setVisibility(View.GONE);
//                                emailTitleTextView.setVisibility(View.GONE);
//                                ageTitleTextView.setVisibility(View.GONE);
                                fullNameTextView.setText("");
                                emailTextView.setText("");
                                ageTextView.setText("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null && visit_user_id != null) {
                    Intent friendIntent = new Intent(ProfileSearch.this, friendRequest.class);
                    friendIntent.putExtra("visit_user_id", visit_user_id);
                    startActivity(friendIntent);
                }
                else if (visit_user_id == null) {
                    Toast.makeText(ProfileSearch.this, "You must select a profile to friend first!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProfileSearch.this, "You must be logged in to add a friend!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fullNameTitleTextView.setVisibility(View.GONE);
//                emailTitleTextView.setVisibility(View.GONE);
//                ageTitleTextView.setVisibility(View.GONE);
                fullNameTextView.setText("");
                emailTextView.setText("");
                ageTextView.setText("");
                searchField.setText("");
                profilesListView.setVisibility(View.GONE);
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
                startActivity(new Intent(this, ?.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ?.class));
                break;*/
        }
    }


}

