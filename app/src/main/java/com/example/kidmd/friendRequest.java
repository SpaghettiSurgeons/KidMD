package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Lets the User press a button and send a friend request to the database
public class friendRequest extends AppCompatActivity implements View.OnClickListener{

    private TextView fullName, email, age;
    private Button SendReq, DeclineReq, Message;

    private DatabaseReference FriendRequestRef, UsersRef;
    private FirebaseAuth mAuth;
    private String senderUserID, receiverUserID, CURRENT_STATE;

    private AppCompatImageView home_button, explore_button, profile_button, notifications_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        mAuth = FirebaseAuth.getInstance();
        senderUserID = mAuth.getCurrentUser().getUid();

        receiverUserID = getIntent().getExtras().get("visit_user_id").toString();

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        FriendRequestRef = FirebaseDatabase.getInstance().getReference().child("Friendrequests");


        InitializeFields();

        //Shows the user their own information
        UsersRef.child(receiverUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String myFullName = snapshot.child("fullName").getValue().toString();
                    String myEmail = snapshot.child("email").getValue().toString();
                    String myAge = snapshot.child("age").getValue().toString();

                    fullName.setText(myFullName);
                    email.setText(myEmail);
                    age.setText(myAge);

                    MaintenanceofButtons();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DeclineReq.setVisibility(View.GONE);
        DeclineReq.setEnabled(false);

        //Make sure the user isn't trying to friend themselves, and that a previous, unopened request hasn't been sent
        if (!senderUserID.equals(receiverUserID)) {
            SendReq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CURRENT_STATE.equals("not_friends")) {
                        SendFriendRequest();
                    }
                }
            });
        }
        else {
            DeclineReq.setVisibility(View.GONE);
            SendReq.setVisibility(View.GONE);
        }

        //Allow the user to chat with a friend by sending the selecting profile ID to the userMessaging page
        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friendIntent = new Intent(friendRequest.this, userMessaging.class);
                friendIntent.putExtra("chatWith", receiverUserID);
                startActivity(friendIntent);
                //startActivity(new Intent(friendRequest.this, UserMessaging.class));
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

    //If friend request has been sent, change button to "Cancel Friend Request"; else show "Add Friend" button
    private void MaintenanceofButtons() {
        FriendRequestRef.child(senderUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(receiverUserID)) {
                    String request_type = snapshot.child(receiverUserID).child("request_type").getValue().toString();

                    if (request_type.equals("sent")) {
                        CURRENT_STATE = "request_sent";
                        SendReq.setText("Cancel Friend Request");

                        DeclineReq.setVisibility(View.GONE);
                        DeclineReq.setEnabled(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Sends a nested friend request to the database. The request will have a Firebase-generated ID, then the sender's user ID, the receiver's user ID, sent/received state
    private void SendFriendRequest() {
        FriendRequestRef.child(senderUserID).child(receiverUserID).child("request_type").setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FriendRequestRef.child(receiverUserID).child(senderUserID).child("request_type").setValue("received").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                SendReq.setEnabled(true);
                                CURRENT_STATE = "request_sent";
                                SendReq.setText("Cancel Friend Request");

                                DeclineReq.setVisibility(View.GONE);
                                DeclineReq.setEnabled(false);
                            }
                        }
                    });
                }
            }
        });
    }

    private void InitializeFields() {
        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.emailAddress);
        age = (TextView) findViewById(R.id.age);

        SendReq = (Button) findViewById(R.id.sendReq);
        DeclineReq = (Button) findViewById(R.id.declineReq);
        Message = (Button) findViewById(R.id.message);

        CURRENT_STATE = "not_friends";
    }


    //Navigation
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
            case R.id.notifications_button:
                startActivity(new Intent(this, Notifications.class));
                break;
            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }

}