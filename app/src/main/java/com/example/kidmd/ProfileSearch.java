package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class ProfileSearch extends AppCompatActivity {

    private Button backToMain;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private EditText searchField;
    private ImageButton searchBtn;

    private RecyclerView resultList;

    private TextView emailAddressTitle;
    private List<User> users;
    private RecyclerView recyclerView;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView ageTextView = (TextView) findViewById(R.id.age);

        final TextView fullNameTitleTextView = (TextView) findViewById(R.id.fullNameTitle);
        final TextView emailTitleTextView = (TextView) findViewById(R.id.emailAddressTitle);
        final TextView ageTitleTextView = (TextView) findViewById(R.id.ageTitle);

        //Set title fields invisible until profile is found
        fullNameTitleTextView.setVisibility(View.GONE);
        emailTitleTextView.setVisibility(View.GONE);
        ageTitleTextView.setVisibility(View.GONE);

        backToMain = (Button) findViewById(R.id.backToMain);
        searchField = findViewById(R.id.search_field);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        searchBtn = (ImageButton) findViewById(R.id.search_btn);

        resultList = (RecyclerView) findViewById(R.id.result_list);
        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            //reference = FirebaseDatabase.getInstance().getReference("Users");
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

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = searchField.getText().toString().toLowerCase();

                reference.addValueEventListener(new ValueEventListener() {



                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Iterate through user profiles in realtime database
                        for (DataSnapshot ds: snapshot.getChildren()) {
                            //Make sure the name matches and the match is not the same as the current user
                            if (ds.child("fullName").getValue().toString().toLowerCase().contains(searchText) && !currentUserEmail.equals(ds.child("email").getValue()) && !searchText.equals("")) {
                                //Make titles visible now that a profile has been found
                                fullNameTitleTextView.setVisibility(View.VISIBLE);
                                emailTitleTextView.setVisibility(View.VISIBLE);
                                ageTitleTextView.setVisibility(View.VISIBLE);
                                //Populate fields with found data
                                fullNameTextView.setText(ds.child("fullName").getValue(String.class));
                                emailTextView.setText(ds.child("email").getValue(String.class));
                                ageTextView.setText(ds.child("age").getValue(String.class));
                                break;
                            }
                            else {
                                fullNameTitleTextView.setVisibility(View.GONE);
                                emailTitleTextView.setVisibility(View.GONE);
                                ageTitleTextView.setVisibility(View.GONE);
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


                firebaseUserSearch();

            }
        });


        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileSearch.this, MainMenu.class));
            }
        });

        searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //emailAddressTitle = findViewById(R.id.emailAddressTitle);
                //emailAddressTitle.setVisibility(View.GONE);
            }
        });

    }

    Query query = FirebaseDatabase.getInstance().getReference().child("Users").limitToLast(50);

    FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(query, User.class).build();

    private void firebaseUserSearch() {
        FirebaseRecyclerAdapter<User, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull User model) {

            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

    }

}