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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

        final EditText name = findViewById(R.id.name);
        final EditText description = findViewById(R.id.editText3);
        final EditText link = findViewById(R.id.editText6);
        final Button create = findViewById(R.id.button2);


// search for project dropdown start :::::::::::::::::::::::::::::::::::
        final Spinner language = (Spinner) findViewById(R.id.language);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        language.setAdapter(adapter);


        final Spinner database = (Spinner) findViewById(R.id.database);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.database_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        database.setAdapter(adapter2);

        final Spinner environment = (Spinner) findViewById(R.id.environment);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.environment_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        environment.setAdapter(adapter3);

        final Spinner platform = (Spinner) findViewById(R.id.platform);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.platform_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platform.setAdapter(adapter4);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    runProjectsCreateMutation(name.getText().toString(),description.getText().toString(),language.getSelectedItem().toString(),database.getSelectedItem().toString(),
                            environment.getSelectedItem().toString(),platform.getSelectedItem().toString(),link.getText().toString());
                Intent i = new Intent(CreateProject.this,MainActivity.class);
                CreateProject.this.startActivity(i);
            }
        });



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
                                          String env, String platform, String link) {
        CreateProjectInput createProjectInput = CreateProjectInput.builder()
                .name(name)
                .description(description)
                .owner(AWSMobileClient.getInstance().getUsername())
                .language(language)
                .database(dataBase)
                .environment(env)
                .platform(platform)
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

