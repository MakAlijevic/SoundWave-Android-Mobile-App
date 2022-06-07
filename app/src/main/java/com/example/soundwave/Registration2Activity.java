package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Registration2Activity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private String username,password,email,profilePhoto;
   // private ImageButton photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        //photo=findViewById(R.id.female1);
       // profilePhoto=(String) photo.getTag();
    }

   public void RegisterButton(View view)
   {
       User user=new User(username,email,password);
       SoundWaveDatabase.getInstance(this).userDao().addUser(user);
       Intent intent=new Intent(this,LoginActivity.class);
       //intent.putExtra("profilePhoto",profilePhoto);
       startActivity(intent);
   }
}