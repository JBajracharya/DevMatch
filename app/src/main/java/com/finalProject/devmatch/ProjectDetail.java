package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListProjectsQuery;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Projects;

import org.w3c.dom.Text;

import java.util.List;

import javax.annotation.Nonnull;

public class ProjectDetail extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";
    Projects project;
    String id;

    TextView name;
    TextView description;
    TextView link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        mAWSAppSyncClient.query(ListProjectsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(projsCallback);

        name = findViewById(R.id.textView4);
        description = findViewById(R.id.textView8);
        link = findViewById(R.id.textView10);

        id = getIntent().getStringExtra("mID");
        getProjects();
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
                    for(int i = 0; i < items.size(); i++){
                        ListProjectsQuery.Item p = items.get(i);
                        if(p.id() == id){
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
                            projectStuff();
                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };
    public void projectStuff(){
        name.setText(project.getName());
        description.setText(project.getDescription());
        link.setText(project.getLink());
    }
}
