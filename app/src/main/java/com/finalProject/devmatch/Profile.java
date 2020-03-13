package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        getDev();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
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
                    List<ListDevelopersQuery.Item> items = response.data().listDevelopers().items();
                    Log.i(TAG,"response");
                    for(int i = 0; i < items.size(); i++){
                        System.out.println("STG " + items.get(i));
                        if(items.get(i).username() == AWSMobileClient.getInstance().getUsername()){
                            username.setText(items.get(i).username());
                            name.setText(items.get(i).name());
                            github.setText(items.get(i).github());
                            email.setText(items.get(i).email());
                            type.setText(items.get(i).type());
                        }
                    }
                    if(username.getText() != AWSMobileClient.getInstance().getUsername()){
                        Log.i(TAG,username.getText().toString());
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
