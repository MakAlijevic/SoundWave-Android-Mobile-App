package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

    }
    public void LogIn(View v)
    {
        UserDao userDao=SoundWaveDatabase.getInstance(this).userDao();
        User user= userDao.login(username.getText().toString(),password.getText().toString());

        if (user==null)
         {
            Toast toast= Toast.makeText(this,"User doesnt exist!",Toast.LENGTH_LONG);
            toast.show();
         }
        else
         {
            String username= user.getUsername();
            String email=user.getEmail();
            String password=user.getPassword();
            String about=user.getAboutMe();
            Intent intent=new Intent(this, MainActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            intent.putExtra("about",about);
            startActivity(intent);
         }

    }

}