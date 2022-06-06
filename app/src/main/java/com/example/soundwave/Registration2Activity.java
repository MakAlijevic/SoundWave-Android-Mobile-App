package com.example.soundwave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Registration2Activity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
   private static Uri imageUri;
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

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            profilePicture.setImageURI(imageUri);

        }
    }

   public void RegisterButton(View view)
   {
       User user=new User(username,email,password);
       SoundWaveDatabase.getInstance(this).userDao().addUser(user);
       Intent intent=new Intent(this,LoginActivity.class);
      // intent.putExtra("profilePhoto",imageUri.toString());
       startActivity(intent);
   }
}