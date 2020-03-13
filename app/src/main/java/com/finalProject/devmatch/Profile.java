package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView profileTitle=findViewById((R.id.profiletitle));
        final TextView profileName=findViewById((R.id.profilename));
        final TextView profileEmail=findViewById((R.id.profileemail));
        final TextView profileGithub=findViewById((R.id.usergithub));
        final TextView profileJobType=findViewById((R.id.profiletype));
        final Button createProfile = findViewById(R.id.createProfile);
        final Button editProfile = findViewById(R.id.editProfile);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, CreateProfile.class);
                Profile.this.startActivity(i);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                Profile.this.startActivity(i);
            }
        });
    }
}
