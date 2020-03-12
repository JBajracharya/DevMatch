package com.finalProject.devmatch;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.amazonaws.amplify.generated.graphql.CreateDeveloperMutation;
import com.amazonaws.amplify.generated.graphql.CreateSkillsetMutation;
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
import com.finalProject.devmatch.models.SkillSet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;
import type.CreateSkillsetInput;

public class EditProfile extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";
    Developer dev;
    SkillSet skills;
    ArrayList<Projects> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getDev();
        skills = dev.getSkills();

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

        Log.i(TAG,"EditProfile Activity Created");

        final EditText name = findViewById(R.id.name);
        final EditText github = findViewById(R.id.github);
        final EditText email = findViewById(R.id.email);
        final RadioButton frontEnd = findViewById(R.id.frontEnd);
        final RadioButton backEnd = findViewById(R.id.backEnd);
        final RadioButton fullStack = findViewById(R.id.fullStack);
        final CheckBox java = findViewById(R.id.java);
        final CheckBox python = findViewById(R.id.python);
        final CheckBox cSharp = findViewById(R.id.cSharp);
        final CheckBox cPlusPlus = findViewById(R.id.cPlusPlus);
        final CheckBox ruby = findViewById(R.id.ruby);
        final CheckBox dotNet = findViewById(R.id.dotNet);
        final CheckBox javascript = findViewById(R.id.javascript);
        final CheckBox sql = findViewById(R.id.sql);
        final CheckBox html = findViewById(R.id.html);
        final CheckBox css = findViewById(R.id.css);
        final CheckBox postgresql = findViewById(R.id.postgresql);
        final CheckBox mysql = findViewById(R.id.mysql);
        final CheckBox mongoDB = findViewById(R.id.mongoDB);
        final CheckBox dynamoDB = findViewById(R.id.dynamoDB);
        final CheckBox AWS = findViewById(R.id.AWS);
        final CheckBox heroku = findViewById(R.id.heroku);
        final CheckBox firebase = findViewById(R.id.firebase);
        final CheckBox azure = findViewById(R.id.azure);
        final CheckBox iOS = findViewById(R.id.iOS);
        final CheckBox android = findViewById(R.id.android);
        final CheckBox linux = findViewById(R.id.linux);
        final CheckBox web = findViewById(R.id.web);
        final CheckBox react = findViewById(R.id.react);
        final Button update = findViewById(R.id.update);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG,"Clicked");

                dev.setName(name.getText().toString());
                dev.setGithub(github.getText().toString());
                dev.setEmail(email.getText().toString());

                if(frontEnd.isChecked()) {
                    dev.setType("Front End");
                } else if(backEnd.isChecked()) {
                    dev.setType("Back End");
                } else if(fullStack.isChecked()) {
                    dev.setType("Full Stack");
                }

                if(java.isChecked()) {
                    skills.setJava(true);
                } else {
                    skills.setJava(false);
                }
                if(python.isChecked()) {
                    skills.setPython(true);
                } else {
                    skills.setPython(false);
                }
                if(cSharp.isChecked()) {
                    skills.setcSharp(true);
                } else {
                    skills.setcSharp(false);
                }
                if (cPlusPlus.isChecked()) {
                    skills.setCplusplus(true);
                } else {
                    skills.setCplusplus(false);
                }
                if (ruby.isChecked()) {
                    skills.setRuby(true);
                } else {
                    skills.setRuby(false);
                }
                if (dotNet.isChecked()) {
                    skills.setDotNet(true);
                } else {
                    skills.setDotNet(false);
                }
                if (javascript.isChecked()) {
                    skills.setJavascript(true);
                } else {
                    skills.setJavascript(false);
                }
                if (sql.isChecked()) {
                    skills.setSql(true);
                } else {
                    skills.setSql(false);
                }
                if (html.isChecked()) {
                    skills.setHtml(true);
                } else {
                    skills.setHtml(false);
                }
                if (css.isChecked()) {
                    skills.setCss(true);
                } else {
                    skills.setCss(false);
                }
                if (postgresql.isChecked()) {
                    skills.setPostgresql(true);
                } else {
                    skills.setPostgresql(false);
                }
                if (mysql.isChecked()) {
                    skills.setMysql(true);
                } else {
                    skills.setMysql(false);
                }
                if (mongoDB.isChecked()) {
                    skills.setMongoDB(true);
                } else {
                    skills.setMongoDB(false);
                }
                if (dynamoDB.isChecked()) {
                    skills.setDynamoDB(true);
                } else {
                    skills.setDynamoDB(false);
                }

                if (AWS.isChecked()) {
                    skills.setAWS(true);
                } else {
                    skills.setAWS(false);
                }
                if (heroku.isChecked()) {
                    skills.setHeroku(true);
                } else {
                    skills.setHeroku(false);
                }
                if (firebase.isChecked()) {
                    skills.setFirebase(true);
                } else {
                    skills.setFirebase(false);
                }
                if (azure.isChecked()) {
                    skills.setAzure(true);
                } else {
                    skills.setAzure(false);
                }

                if (iOS.isChecked()) {
                    skills.setiOS(true);
                } else {
                    skills.setiOS(false);
                }
                if (android.isChecked()) {
                    skills.setAndroid(true);
                } else {
                    skills.setAndroid(false);
                }
                if (linux.isChecked()) {
                    skills.setLinux(true);
                } else {
                    skills.setLinux(false);
                }
                if (web.isChecked()) {
                    skills.setWeb(true);
                } else {
                    skills.setWeb(false);
                }
                if (react.isChecked()) {
                    skills.setReact(true);
                } else {
                    skills.setReact(false);
                }

                dev.setSkills(skills);

                runSkillsetCreateMutation(skills);
            }
        });
    }
    public void runSkillsetCreateMutation(SkillSet skills) {
        CreateSkillsetInput createSkillsetInput = CreateSkillsetInput.builder().
                java(skills.isJava()).
                python(skills.isPython()).
                cSharp(skills.iscSharp()).
                cplusplus(skills.isCplusplus()).
                ruby(skills.isRuby()).
                dotNet(skills.isDotNet()).
                javascript(skills.isJavascript()).
                sql(skills.isSql()).
                html(skills.isHtml()).
                css(skills.isCss()).
                postgresql(skills.isPostgresql()).
                mysql(skills.isMysql()).
                mongoDB(skills.isMongoDB()).
                dynamoDB(skills.isDynamoDB()).
                aWS(skills.isAWS()).
                heroku(skills.isHeroku()).
                firebase(skills.isFirebase()).
                azure(skills.isAzure()).
                iOS(skills.isiOS()).
                android(skills.isAndroid()).
                linux(skills.isLinux()).
                web(skills.isWeb()).
                react(skills.isReact()).
                build();

        mAWSAppSyncClient.mutate(CreateSkillsetMutation.builder().input(createSkillsetInput).build())
                .enqueue(mutationCallback);
    }

    private GraphQLCall.Callback<CreateSkillsetMutation.Data> mutationCallback = new GraphQLCall.Callback<CreateSkillsetMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateSkillsetMutation.Data> response) {
            Log.i(TAG,"Success");
            runDeveloperCreateMutation(dev.getName(),dev.getGithub(),dev.getEmail(),response.data().createSkillset().id(),"Front End");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void runDeveloperCreateMutation(String name, String github, String email, String id, String type) {
        CreateDeveloperInput createDeveloperInput = CreateDeveloperInput.builder().
                username(AWSMobileClient.getInstance().getUsername()).
                name(name).
                github(github).
                email(email).
                developerSkillSetId(id).
                type(type).
                build();

        mAWSAppSyncClient.mutate(CreateDeveloperMutation.builder().input(createDeveloperInput).build())
                .enqueue(mutationCallbacky);
    }

    private GraphQLCall.Callback<CreateDeveloperMutation.Data> mutationCallbacky = new GraphQLCall.Callback<CreateDeveloperMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateDeveloperMutation.Data> response) {
            Log.i(TAG, response.data().toString());
            Log.i(TAG,"SUCCESS");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void getDev(){

        mAWSAppSyncClient.query(ListDevelopersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(devsCallback);
    }
    private GraphQLCall.Callback<ListDevelopersQuery.Data> devsCallback = new
            GraphQLCall.Callback<ListDevelopersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListDevelopersQuery.Data> response) {
                    List<ListDevelopersQuery.Item> items = response.data().listDevelopers().items();
                    for(int i = 0; i < items.size(); i++){
                        if(items.get(i).username() == AWSMobileClient.getInstance().getUsername()){
                            ListDevelopersQuery.SkillSet skillSet = items.get(i).skillSet();
                            SkillSet skills = new SkillSet(skillSet.id(),skillSet.java(),skillSet.python(),
                                    skillSet.cSharp(),skillSet.cplusplus(),skillSet.ruby(),skillSet.dotNet(),
                                    skillSet.javascript(),skillSet.sql(),skillSet.html(),skillSet.css(),
                                    skillSet.postgresql(),skillSet.mysql(),skillSet.mongoDB(),skillSet.dynamoDB(),
                                    skillSet.AWS(),skillSet.heroku(),skillSet.firebase(),skillSet.azure(),skillSet.iOS(),
                                    skillSet.android(),skillSet.linux(),skillSet.web(),skillSet.react());
                            ListDevelopersQuery.Item item = items.get(i);
                            dev = new Developer(item.id(),item.username(),item.username(),item.github(),item.email());
                            dev.setSkills(skills);
                            getProjects();
                            dev.setCurrentProjects(projects);

                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
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
                    ArrayList<String> developers;
                    List<ListProjectsQuery.Item> items = response.data().listProjects().items();
                    for(int i = 0; i < items.size(); i++){
                        if(items.get(i).developers().toString().contains(AWSMobileClient.getInstance().getUsername())){
                            ListProjectsQuery.Item item = items.get(i);
                            Projects project = new Projects();
                            project.setId(item.id());
                            project.setName(item.name());
                            project.setDescription(item.description());
                            project.setLanguage(item.language());
                            project.setDatabase(item.database());
                            project.setEnvironment(item.environment());
                            project.setPlatform(item.platform());
                            project.setDate(item.date());
                            project.setLink(item.link());
                            projects.add(project);
                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };
}