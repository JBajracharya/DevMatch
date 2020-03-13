package com.finalProject.devmatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.amazonaws.amplify.generated.graphql.ListProjectsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.dummy.DummyContent;
import com.finalProject.devmatch.models.Projects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class SearchForProjects extends AppCompatActivity implements ProjectListFragment.OnListFragmentInteractionListener, AdapterView.OnItemSelectedListener {

    static String TAG = "SearchForProject";

    List<Projects> listOfProjects;
    private AWSAppSyncClient mAWSAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_projects2);

// search for project dropdown start :::::::::::::::::::::::::::::::::::
        Spinner spinner = (Spinner) findViewById(R.id.language);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.database);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.database_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.enviroment);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.environment_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.platform);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.platform_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        //        pulls in context from aws
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        this.listOfProjects = new ArrayList<Projects>();

        listOfProjects.add(new Projects("Test", "Test", "Test", "Test"));
        listOfProjects.add(new Projects("match", "match", "test", "test"));

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


        /// https://developer.android.com/guide/topics/ui/controls/spinner
//        Spinner spinner = (Spinner) findViewById(R.id.filter);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

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
                    Spinner language = (Spinner) findViewById(R.id.language);
                    Spinner database = (Spinner) findViewById(R.id.database);
                    Spinner environment = (Spinner) findViewById(R.id.enviroment);
                    Spinner platform = (Spinner) findViewById(R.id.platform);

                    String languageString = language.getSelectedItem().toString();
                    String databaseString = database.getSelectedItem().toString();
                    String environmentString = environment.getSelectedItem().toString();
                    String platformString = platform.getSelectedItem().toString();
                    listOfProjects.clear();

                    for( ListProjectsQuery.Item item : response.data().listProjects().items()) {
                        Log.i(TAG, item.name());
                        if(item.language() == languageString || item.database() == databaseString || item.environment() == environmentString
                            || item.platform() == platformString) {
                            listOfProjects.add(new Projects(item.name(),item.description(), item.date(), item.link()));
                        }


                    }



                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };

    public void saveProject(View view) {

    }
}
