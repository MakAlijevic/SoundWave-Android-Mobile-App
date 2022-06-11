package com.example.soundwave;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private TextView usernamelarge;
    private EditText about,username,email;
    private Button save,cancel,logout;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, container,false);
        MainActivity activity= (MainActivity) getActivity();
        String getUsername = activity.getUsername();
        String getEmail = activity.getEmail();
        String getPassword=activity.getPassword();
        ImageView edit = (ImageView) v.findViewById(R.id.edit);
        about=(EditText) v.findViewById(R.id.aboutinput);
        username=(EditText) v.findViewById(R.id.usernameinput);
        usernamelarge=(TextView) v.findViewById(R.id.textView8);
        logout=v.findViewById(R.id.logout);
        username.setText(getUsername);
        usernamelarge.setText(getUsername);
        email=(EditText) v.findViewById(R.id.emailinput);
        email.setText(getEmail);
        save=(Button) v.findViewById(R.id.buttonsave);
        cancel=(Button) v.findViewById(R.id.buttoncancel);
        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                about.setEnabled(true);
                username.setEnabled(true);
                email.setEnabled(true);
                save.setVisibility(save.VISIBLE);
                cancel.setVisibility(cancel.VISIBLE);
                logout.setVisibility(logout.INVISIBLE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setEnabled(false);
                username.setEnabled(false);
                email.setEnabled(false);
                save.setVisibility(save.INVISIBLE);
                cancel.setVisibility(cancel.INVISIBLE);
                logout.setVisibility(logout.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao userDao=SoundWaveDatabase.getInstance(getActivity()).userDao();
                User user=userDao.login(getUsername,getPassword);
                user.setUsername(username.getText().toString());
                user.setAboutMe(about.getText().toString());
                user.setEmail(email.getText().toString());
                userDao.updateUser(user);
                username.setText(username.getText().toString());
                email.setText(email.getText().toString());
                about.setText(about.getText().toString());
                save.setVisibility(save.INVISIBLE);
                cancel.setVisibility(cancel.INVISIBLE);
                logout.setVisibility(logout.VISIBLE);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),IntroActivity.class);
                startActivity(intent);
            }
        });
        return v;


    }


}
