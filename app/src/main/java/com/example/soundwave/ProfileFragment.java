package com.example.soundwave;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private EditText about,username,email;
    private Button save,cancel,photo;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, container,false);
        ImageView edit = (ImageView) v.findViewById(R.id.edit);
        about=(EditText) v.findViewById(R.id.aboutinput);
        username=(EditText) v.findViewById(R.id.usernameinput);
        email=(EditText) v.findViewById(R.id.emailinput);
        save=(Button) v.findViewById(R.id.buttonsave);
        cancel=(Button) v.findViewById(R.id.buttoncancel);
        photo=(Button) v.findViewById(R.id.buttonphoto);
        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                about.setEnabled(true);
                username.setEnabled(true);
                email.setEnabled(true);
                about.getText().clear();
                username.getText().clear();
                email.getText().clear();
                save.setVisibility(save.VISIBLE);
                cancel.setVisibility(cancel.VISIBLE);
                photo.setVisibility(photo.VISIBLE);
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
                photo.setVisibility(photo.INVISIBLE);
            }
        });
        return v;


    }

}
