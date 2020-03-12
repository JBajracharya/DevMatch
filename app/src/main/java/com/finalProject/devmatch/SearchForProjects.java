package com.finalProject.devmatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.core.content.ContextCompat;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;

import com.amazonaws.amplify.generated.graphql.CreateDeveloperMutation;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.dummy.DummyContent;
import com.finalProject.devmatch.models.Projects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;

public class SearchForProjects extends AppCompatActivity implements ProjectListFragment.OnListFragmentInteractionListener {
    static String TAG = "SearchForProject";

    List<Projects> listOfProjects;
    private AWSAppSyncClient mAWSAppSyncClient;

import android.view.Window;
import android.view.WindowManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_projects2);

        //        pulls in context from aws
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        this.listOfProjects = new ArrayList<Projects>();

        listOfProjects.add(new Projects("test", "test", "test", "test","test"));
        listOfProjects.add(new Projects("match", "match", "test", "test","test"));
//        showing list of projects in the recycler view
        RecyclerView recyclerView = findViewById(R.id.fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyProjectListRecyclerViewAdapter(this.listOfProjects, this));

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

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i(TAG, "clicked");
    }





}
