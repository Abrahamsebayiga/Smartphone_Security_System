package com.example.pdsafe256;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
ImageView logout, backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logout = findViewById(R.id.logout_icon);
        backArrow = findViewById(R.id.backArrowID);
        logout.setOnClickListener(view ->
                FirebaseAuth.getInstance().signOut());

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backintent = new Intent(Settings.this,Home.class);
                startActivity(backintent);
                finish();
            }
        });
    }
}