package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.amplify.generated.graphql.ListDevelopersQuery;
import com.amazonaws.amplify.generated.graphql.ListProjectsQuery;
import com.amazonaws.amplify.generated.graphql.UpdateDeveloperMutation;
import com.amazonaws.amplify.generated.graphql.UpdateProjectMutation;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.Projects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.UpdateDeveloperInput;
import type.UpdateProjectInput;

public class ProjectDetail extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";
    Projects project;
    String id;
    String requsterID;

    TextView name;
    TextView description;
    TextView link;
    TextView owner;
    TextView env;
    TextView platform;
    TextView date;
    Button apply;

    TextView requester;
    Button approve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();



        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        link = findViewById(R.id.link);
        env = findViewById(R.id.env);
        owner = findViewById(R.id.owner);
        platform = findViewById(R.id.platform);
        date = findViewById(R.id.date);
        apply = findViewById(R.id.apply);
        requester = findViewById(R.id.Owner);
        approve = findViewById(R.id.approve);

        requester.setVisibility(View.INVISIBLE);
        approve.setVisibility(View.INVISIBLE);
        apply.setVisibility(View.INVISIBLE);

        id = getIntent().getStringExtra("mName");

        getProjects();

        getProjects();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runProjectsUpdateMutation();
                Toast.makeText(getApplicationContext(), "You have requested to be part of this project", Toast.LENGTH_SHORT).show();
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runProjectsUpdateMutationForApproval();
                runDeveloperUpdateMutationForApproval(project.getId());
                Toast.makeText(getApplicationContext(), "You have approved " + requester.getText() + " to join" + name.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProjects() {
        mAWSAppSyncClient.query(ListProjectsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(projsCallback);
    }



    private GraphQLCall.Callback<ListProjectsQuery.Data> projsCallback = new
            GraphQLCall.Callback<ListProjectsQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListProjectsQuery.Data> response) {
                    if(response.data() == null || response.data().listProjects() == null ||
                            response.data().listProjects().items() == null){
                        return;
                    }
                    List<ListProjectsQuery.Item> items = response.data().listProjects().items();
                    Log.i(TAG,"queyr");
                    for (int i = 0; i < items.size(); i++) {
                        ListProjectsQuery.Item p = items.get(i);
                        Log.i(TAG,id);
                        Log.i(TAG,items.get(i).name());
                        if (p.name().equals(id)) {
                            Log.i(TAG,items.get(i).toString());
                            project = new Projects();
                            project.setId(id);
                            project.setName(p.name());
                            project.setDescription(p.description());
                            project.setOwner(p.owner());
                            project.setLanguage(p.language());
                            project.setDatabase(p.database());
                            project.setEnvironment(p.environment());
                            project.setPlatform(p.platform());
                            project.setDate(p.date());
                            project.setLink(p.link());
                            project.setDevRequests(parseDevReqs(p.devRequests()));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    projectStuff();
                                    if (AWSMobileClient.getInstance().getUsername() != null && AWSMobileClient.getInstance().getUsername().equals(project.getOwner()) && !project.getDevRequests().isEmpty()) {
                                        requester.setText(project.getDevRequests().get(0));
                                        requester.setVisibility(View.VISIBLE);
                                        approve.setVisibility(View.VISIBLE);
                                        getDev();

                                    }
                                }
                            });

                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG, "Failure");
                }
            };

    public void runProjectsUpdateMutation() {
        UpdateProjectInput updateProjectInput = UpdateProjectInput.builder()
                .id(id)
                .devRequests(AWSMobileClient.getInstance().getUsername())
                .build();

        mAWSAppSyncClient.mutate(UpdateProjectMutation.builder().input(updateProjectInput).build())
                .enqueue(mutationCallbacky);
    }

    public void runProjectsUpdateMutationForApproval() {
        UpdateProjectInput updateProjectInput = UpdateProjectInput.builder()
                .id(id)
                .devRequests(null)
                .developers(project.getDevRequests().get(0))
                .build();

        mAWSAppSyncClient.mutate(UpdateProjectMutation.builder().input(updateProjectInput).build())
                .enqueue(mutationCallbacky);
    }

    private GraphQLCall.Callback<UpdateProjectMutation.Data> mutationCallbacky = new GraphQLCall.Callback<UpdateProjectMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<UpdateProjectMutation.Data> response) {
            Log.i(TAG, response.data().toString());
            Log.i(TAG, "SUCCESS");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void runDeveloperUpdateMutationForApproval(String projectID) {
        UpdateDeveloperInput updateDeveloperInput = UpdateDeveloperInput.builder()
                .id(requsterID)
                .projects(projectID)
                .build();

        mAWSAppSyncClient.mutate(UpdateDeveloperMutation.builder().input(updateDeveloperInput).build())
                .enqueue(mutationCallback);
    }

    private GraphQLCall.Callback<UpdateDeveloperMutation.Data> mutationCallback = new GraphQLCall.Callback<UpdateDeveloperMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<UpdateDeveloperMutation.Data> response) {
            Log.i(TAG, response.data().toString());
            Log.i(TAG, "SUCCESS");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void getDev() {

        mAWSAppSyncClient.query(ListDevelopersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(devsCallback);
    }

    private GraphQLCall.Callback<ListDevelopersQuery.Data> devsCallback = new
            GraphQLCall.Callback<ListDevelopersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListDevelopersQuery.Data> response) {
                    if(response.data() == null || response.data().listDevelopers() == null ||
                    response.data().listDevelopers().items() == null){
                        return;
                    }
                    List<ListDevelopersQuery.Item> items = response.data().listDevelopers().items();
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).username() == requester.getText()) {
                            requsterID = items.get(i).id();
                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG, "Failure");
                }

            };
    public void projectStuff() {
        name.setText(project.getName());
        description.setText(project.getDescription());
        link.setText(project.getLink());
        owner.setText(project.getOwner());
        platform.setText(project.getPlatform());
        env.setText(project.getEnvironment());
        date.setText(project.getDate());

    }
    public ArrayList<String> parseDevReqs(String devs){
        int beg = 0;
        ArrayList<String> result = new ArrayList<>();
        if(devs == null){
            return result;
        }
        for(int i = 0; i < devs.length(); i++){
            if(devs.charAt(i) == ','){
                result.add(devs.substring(beg,i));
                beg = i + 1;
            }
        }
        result.add(devs.substring(beg,devs.length() - 1));
        return result;
    }

}
