package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class friendRequest extends AppCompatActivity {

    private TextView fullName, email, age;
    private Button SendReq, DeclineReq;

    private DatabaseReference FriendRequestRef, UsersRef;
    private FirebaseAuth mAuth;
    private String senderUserID, receiverUserID, CURRENT_STATE;

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

    }

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

        CURRENT_STATE = "not_friends";
    }

}