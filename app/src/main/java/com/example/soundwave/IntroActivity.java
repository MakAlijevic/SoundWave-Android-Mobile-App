package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }
    public void SignUpButton(View V)
    {
        Intent registrationIntent = new Intent(this,RegistrationActivity.class);
        startActivity(registrationIntent);
    }

    public void LoginButton(View V)
    {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }
}