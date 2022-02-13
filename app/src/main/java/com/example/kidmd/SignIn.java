package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private TextView cancel, guest;
    private EditText editTextEmail, editTextPassword;
    private Button login;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        cancel = (TextView) findViewById(R.id.signInCancel);
        cancel.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.signIn_email);
        editTextPassword = (EditText) findViewById(R.id.signIn_password);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        guest = (TextView) findViewById(R.id.guest);
        guest.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInCancel:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.guest:
                startActivity(new Intent(this, MainMenu.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email address is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email address is invalid. Please enter a valid email address!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("You must enter a password!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters long!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    //The code that is commented out is for email verification, if we want to use it.
                    //if (user.isEmailVerified()) {
                    //Redirect to user profile
                    startActivity(new Intent(SignIn.this, MainMenu.class));
                    progressBar.setVisibility(View.GONE);
                    //}
                    //else {
                    //user.sendEmailVerification();
                    //Toast.makeText(MainActivity.this, "Please check your email to verify your account!", Toast.LENGTH_LONG).show();
                    //}

                }
                else {
                    Toast.makeText(SignIn.this, "Failed to login!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}