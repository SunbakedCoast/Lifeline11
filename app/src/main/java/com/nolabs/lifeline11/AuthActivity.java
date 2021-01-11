package com.nolabs.lifeline11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        fauth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();


        if (fauth.getCurrentUser() !=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
