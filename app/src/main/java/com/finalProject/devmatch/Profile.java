package com.finalProject.devmatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListDevelopersQuery;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.SkillSet;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

public class Profile extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";

    TextView username;
    TextView name;
    TextView github;
    TextView email;
    TextView type;
    Developer dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlack));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlack));
//
        ActionBar bar = this.getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        bar.setBackgroundDrawable(colorDrawable);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        github = findViewById(R.id.github);
        email = findViewById(R.id.email);
        type = findViewById(R.id.type);
        Button editProfile = findViewById(R.id.editProfile);
        Button createProfile = findViewById(R.id.createProfile);

        getDev();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                Profile.this.startActivity(i);
            }
        });
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,CreateProfile.class);
                Profile.this.startActivity(i);
            }
        });
    }
    public void getDev(){

        mAWSAppSyncClient.query(ListDevelopersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(devsCallback);
        Log.i(TAG,"query");
    }
    private GraphQLCall.Callback<ListDevelopersQuery.Data> devsCallback = new
            GraphQLCall.Callback<ListDevelopersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListDevelopersQuery.Data> response) {
                    if(response.data() == null || response.data().listDevelopers() == null || response.data().listDevelopers().items() == null){
                        return;
                    }
                    List<ListDevelopersQuery.Item> items = response.data().listDevelopers().items();
                    Log.i(TAG,"response");
                    for(int i = 0; i < items.size(); i++){
                        System.out.println("STG " + items.get(i));
                        if(items.get(i).username().equals(AWSMobileClient.getInstance().getUsername())){
                            username.setText(items.get(i).username());
                            name.setText(items.get(i).name());
                            github.setText(items.get(i).github());
                            email.setText(items.get(i).email());
                            type.setText(items.get(i).type());
                        }
                    }
                    if(!AWSMobileClient.getInstance().getUsername().equals(username.getText())){
                        Intent i = new Intent(Profile.this,CreateProfile.class);
                        Profile.this.startActivity(i);
                    }

                        }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };
}
