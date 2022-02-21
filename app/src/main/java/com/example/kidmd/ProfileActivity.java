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

public class ProfileActivity extends AppCompatActivity {

    private Button friendSearch;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private EditText searchField;

    private TextView emailAddressTitle;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView ageTextView = (TextView) findViewById(R.id.age);

        friendSearch = (Button) findViewById(R.id.friendSearch);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        friendSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileSearch.class));
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            //reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userProfile = dataSnapshot.getValue(User.class);
                    if (userProfile != null) {
                        String[] firstName = userProfile.fullName.split(" ");
                        String email = userProfile.email;
                        String age = userProfile.age;

                        greetingTextView.setText("Hi, " + firstName[0] + "!");
                        fullNameTextView.setText(userProfile.fullName);
                        emailTextView.setText(email);
                        ageTextView.setText(age);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ProfileActivity.this, "There was a problem!", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

}