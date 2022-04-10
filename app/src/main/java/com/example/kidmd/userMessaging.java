package com.example.kidmd;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class userMessaging extends AppCompatActivity {
    private DatabaseReference myDatabase;
    private DatabaseReference myDatabase2;

    private FirebaseAuth mAuth;
    private String senderUserID;
    private String messages = "";
    private FirebaseUser user;
    private String name;
    private String chatWith;
    private String chatWithName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messaging);
        mAuth = FirebaseAuth.getInstance();
        senderUserID = mAuth.getCurrentUser().getUid();

        myDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        myDatabase2 = FirebaseDatabase.getInstance().getReference("Users");

        chatWith = getIntent().getExtras().get("chatWith").toString();

        final TextView myText = findViewById(R.id.textbox);

        myDatabase2.child(senderUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("fullName").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myDatabase2.child(chatWith).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatWithName = snapshot.child("fullName").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myDatabase.child(senderUserID).child(chatWith).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myText.setText("");
                Iterator<DataSnapshot> iterable = dataSnapshot.getChildren().iterator();
                for (Iterator<DataSnapshot> it = iterable; it.hasNext();){
                    DataSnapshot dataSnapshot1 = it.next();
                    myText.append(dataSnapshot1.getValue().toString() + "\n");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myText.setText("CANCELLED");

            }
        });

    }

    public void sendMessage(View view){

        EditText myEditText = findViewById(R.id.editText);

        myDatabase.child(senderUserID).child(chatWith).push().setValue(name + ": " + myEditText.getText().toString());
        myDatabase.child(chatWith).child(senderUserID).push().setValue(name + ": " + myEditText.getText().toString());
        myEditText.setText("");
    }


}