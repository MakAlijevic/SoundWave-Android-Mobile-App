package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Registration2Activity extends AppCompatActivity {
   private String username,password,email;
   private EditText aboutMe;
   private ImageView profilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");

        aboutMe = findViewById(R.id.aboutMe);
        profilePicture = findViewById(R.id.profilePicture);


    }
    public void RegisterButton(View view)
    {
        String aboutMeText=aboutMe.getText().toString();
        String photo=profilePicture.source
        User user=new User(username,password,email,abo)
    }
}