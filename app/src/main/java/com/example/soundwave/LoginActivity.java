package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
   // private String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
      //  photo= getIntent().getStringExtra("profilePhoto");

    }
    public void LogIn(View v)
    {
        UserDao userDao=SoundWaveDatabase.getInstance(this).userDao();
       User user= userDao.login(username.getText().toString(),password.getText().toString());
       String username= user.getUsername();
       String email=user.getEmail();
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        //intent.putExtra("profilePicture",photo);
        startActivity(intent);
    }

}