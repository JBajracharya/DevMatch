package com.finalProject.devmatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.amazonaws.amplify.generated.graphql.CreateDeveloperMutation;
import com.amazonaws.amplify.generated.graphql.CreateProjectMutation;
import com.amazonaws.amplify.generated.graphql.ListDevelopersQuery;
import com.amazonaws.amplify.generated.graphql.ListProjectsQuery;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.Projects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;
import type.CreateProjectInput;

public class CreateProject extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    final String TAG = "STG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_projects);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

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
    }
  
    public void runProjectsCreateMutation(String name, String description, String language,String dataBase,
                                          String env, String platform, String date, String link) {
        CreateProjectInput createProjectInput = CreateProjectInput.builder()
                .name(name)
                .description(description)
                .owner(AWSMobileClient.getInstance().getUsername())
                .language(language)
                .database(dataBase)
                .environment(env)
                .platform(platform)
                .date(date)
                .link(link)
                .build();

        mAWSAppSyncClient.mutate(CreateProjectMutation.builder().input(createProjectInput).build())
                .enqueue(mutationCallbacky);
    }

    private GraphQLCall.Callback<CreateProjectMutation.Data> mutationCallbacky = new GraphQLCall.Callback<CreateProjectMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateProjectMutation.Data> response) {
            Log.i(TAG, response.data().toString());
            Log.i(TAG,"SUCCESS");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void getProjects(){

        mAWSAppSyncClient.query(ListProjectsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(projsCallback);
    }
    private GraphQLCall.Callback<ListProjectsQuery.Data> projsCallback = new
            GraphQLCall.Callback<ListProjectsQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListProjectsQuery.Data> response) {
                    List<ListProjectsQuery.Item> items = response.data().listProjects().items();
                    // items is a list of projects from DynamoDB
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };

    public void saveProject(View view) {

    }
}

