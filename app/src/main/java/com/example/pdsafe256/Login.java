package com.example.pdsafe256;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button ButtonForgotpass, registerButton, loginButton;
    TextInputEditText email, pass;
    TextView textView;
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.mail);
        pass = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
        ButtonForgotpass = findViewById(R.id.ButtonForgotpass);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        currentUser = mAuth.getCurrentUser();

        textView = findViewById(R.id.loginText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(registerIntent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email, pwd;
                Email = String.valueOf(email.getText());
                pwd = String.valueOf(pass.getText());

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Login.this, "Please enter email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //When both email and password are available log in to the account
                //Show the progress on Progress Dialog
                loadingBar.setTitle("Signing In");
                loadingBar.setMessage("Please wait ,Because Good things always take time");
                loadingBar.show();
                mAuth.signInWithEmailAndPassword(Email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())//If account login successful print message and send user to Home Activity
                                {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();
                                    Intent HomeIntent = new Intent(Login.this, Home.class);
                                    startActivity(HomeIntent);
                                    finish();
                                } else//Print the error message in case of failure
                                {
                                    String msg = task.getException().toString();
                                    Toast.makeText(Login.this, "Error: " + msg, Toast.LENGTH_SHORT).show();


                                }
                            }
                        });
            }
        });
    }}