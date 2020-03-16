package com.finalProject.devmatch;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.amazonaws.amplify.generated.graphql.CreateDeveloperMutation;
import com.amazonaws.amplify.generated.graphql.CreateSkillsetMutation;
import com.amazonaws.amplify.generated.graphql.ListDevelopersQuery;
import com.amazonaws.amplify.generated.graphql.ListProjectsQuery;
import com.amazonaws.amplify.generated.graphql.UpdateDeveloperMutation;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;
import type.CreateSkillsetInput;
import type.UpdateDeveloperInput;

import static android.view.View.VISIBLE;

public class EditProfile extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";
    Developer dev;
    SkillSet skills;
    ArrayList<Projects> projects;

    EditText name;
     EditText github;
    EditText email;
     RadioButton frontEnd;
     RadioButton backEnd;
     RadioButton fullStack;
     CheckBox java;
     CheckBox python;
     CheckBox cSharp;
     CheckBox cPlusPlus;
     CheckBox ruby;
     CheckBox dotNet;
     CheckBox javascript;
     CheckBox sql;
     CheckBox html;
     CheckBox css;
     CheckBox postgresql;
     CheckBox mysql;
     CheckBox mongoDB;
     CheckBox dynamoDB;
     CheckBox AWS;
     CheckBox heroku;
     CheckBox firebase;
     CheckBox azure;
     CheckBox iOS;
     CheckBox android;
     CheckBox linux;
     CheckBox web;
     CheckBox react;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();
        TextView profileUsername = (TextView) findViewById(R.id.name);
        profileUsername.setText(AWSMobileClient.getInstance().getUsername());
        profileUsername.setVisibility(VISIBLE);

//        Log.i(TAG, "User Details"+ AWSMobileClient.getInstance().getUserAttributes().toString());

        TextView profileEmail = (TextView) findViewById(R.id.email);
        try {
            profileEmail.setText(AWSMobileClient.getInstance().getUserAttributes().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        profileEmail.setVisibility(VISIBLE);


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

        name = findViewById(R.id.name);
        github = findViewById(R.id.github);
        email = findViewById(R.id.email);
        frontEnd = findViewById(R.id.frontEnd);
        backEnd = findViewById(R.id.backEnd);
        fullStack = findViewById(R.id.fullStack);
        java = findViewById(R.id.java);
        python = findViewById(R.id.python);
        cSharp = findViewById(R.id.cSharp);
        cPlusPlus = findViewById(R.id.cPlusPlus);
        ruby = findViewById(R.id.ruby);
        dotNet = findViewById(R.id.dotNet);
        javascript = findViewById(R.id.javascript);
        sql = findViewById(R.id.sql);
        html = findViewById(R.id.html);
        css = findViewById(R.id.css);
        postgresql = findViewById(R.id.postgresql);
        mysql = findViewById(R.id.mysql);
        mongoDB = findViewById(R.id.mongoDB);
        dynamoDB = findViewById(R.id.dynamoDB);
        AWS = findViewById(R.id.AWS);
        heroku = findViewById(R.id.heroku);
        firebase = findViewById(R.id.firebase);
        azure = findViewById(R.id.azure);
        iOS = findViewById(R.id.iOS);
        android = findViewById(R.id.android);
        linux = findViewById(R.id.linux);
        web = findViewById(R.id.web);
        react = findViewById(R.id.react);
        final Button update = findViewById(R.id.update);

//        getDev();



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView profileEmail = (TextView) findViewById(R.id.email);
                try {
                    profileEmail.setText(AWSMobileClient.getInstance().getUserAttributes().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                profileEmail.setVisibility(VISIBLE);


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
            runDeveloperUpdateMutation(dev.getId(),dev.getName(),dev.getGithub(),dev.getEmail(),response.data().createSkillset().id(),"Front End");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void runDeveloperUpdateMutation(String id, String name, String github, String email, String skillId, String type) {
        UpdateDeveloperInput updateDeveloperInput = UpdateDeveloperInput.builder().
                username(AWSMobileClient.getInstance().getUsername()).
                id(id).
                name(name).
                github(github).
                email(email).
                developerSkillSetId(skillId).
                type(type).
                build();

        mAWSAppSyncClient.mutate(UpdateDeveloperMutation.builder().input(updateDeveloperInput).build())
                .enqueue(mutationCallbacky);
    }

    private GraphQLCall.Callback<UpdateDeveloperMutation.Data> mutationCallbacky = new GraphQLCall.Callback<UpdateDeveloperMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<UpdateDeveloperMutation.Data> response) {
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
                    assert response.data() != null;
                    List<ListDevelopersQuery.Item> items = Objects.requireNonNull(response.data().listDevelopers()).items();
                    assert items != null;
                    for(int i = 0; i < items.size(); i++){
                        if(items.get(i) == null){
                            break;
                        }
                        if(Objects.equals(items.get(i).username(), AWSMobileClient.getInstance().getUsername())){
                            ListDevelopersQuery.SkillSet skillSet = items.get(i).skillSet();
                            if(skillSet != null){
                                Log.i(TAG,skillSet.toString());
                                skills = new SkillSet(skillSet.id(),skillSet.java(),skillSet.python(),
                                        skillSet.cSharp(),skillSet.cplusplus(),skillSet.ruby(),skillSet.dotNet(),
                                        skillSet.javascript(),skillSet.sql(),skillSet.html(),skillSet.css(),
                                        skillSet.postgresql(),skillSet.mysql(),skillSet.mongoDB(),skillSet.dynamoDB(),
                                        skillSet.AWS(),skillSet.heroku(),skillSet.firebase(),skillSet.azure(),skillSet.iOS(),
                                        skillSet.android(),skillSet.linux(),skillSet.web(),skillSet.react());
                            }
                            ListDevelopersQuery.Item item = items.get(i);
                            dev = new Developer(item.id(),item.username(),item.username(),item.github(),item.email());
                            dev.setSkills(skills);
                            getProjects();
                            dev.setCurrentProjects(projects);
                            Looper.prepare();
                            devStuff();

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
                    ArrayList<String> developers = new ArrayList<>();
                    assert response.data() != null;
                    List<ListProjectsQuery.Item> items = response.data().listProjects().items();
                    for(int i = 0; i < items.size(); i++){
                        if(items.get(i).developers() == null){
                            break;
                        }
                        if(items.get(i).developers().contains(AWSMobileClient.getInstance().getUsername())){
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
                            String devs = item.developers();
                            for(int j = 0; j < devs.length(); j++){
                                int beg = 0;
                                if(devs.charAt(j) == ','){
                                    developers.add(devs.substring(beg,j));
                                    beg = j + 1;
                                }
                            }
                            project.setDevelopers(developers);
                            projects.add(project);
                        }
                    }
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };
    public void devStuff(){
        name.setText(dev.getName());
        github.setText(dev.getGithub());
        email.setText(dev.getEmail());
        if(skills.isJava()){
            java.setChecked(true);
        }
        if(skills.isPython()){
            python.setChecked(true);
        }
        if(skills.iscSharp()){
            cSharp.setChecked(true);
        }
        if(skills.isCplusplus()){
            cPlusPlus.setChecked(true);
        }
        if(skills.isRuby()){
            ruby.setChecked(true);
        }
        if(skills.isDotNet()){
            dotNet.setChecked(true);
        }
        if(skills.isJavascript()){
            javascript.setChecked(true);
        }
        if(skills.isSql()){
            sql.setChecked(true);
        }
        if(skills.isHtml()){
            html.setChecked(true);
        }
        if(skills.isCss()){
            css.setChecked(true);
        }
        if(skills.isPostgresql()){
            postgresql.setChecked(true);
        }
        if(skills.isMysql()){
            mysql.setChecked(true);
        }
        if(skills.isMongoDB()){
            mongoDB.setChecked(true);
        }
        if(skills.isDynamoDB()){
            dynamoDB.setChecked(true);
        }
        if(skills.isAWS()){
            AWS.setChecked(true);
        }
        if(skills.isHeroku()){
            heroku.setChecked(true);
        }
        if(skills.isFirebase()){
            firebase.setChecked(true);
        }
        if(skills.isAzure()){
            azure.setChecked(true);
        }
        if(skills.isiOS()){
            iOS.setChecked(true);
        }
        if(skills.isAndroid()){
            android.setChecked(true);
        }
        if(skills.isLinux()){
            linux.setChecked(true);
        }
        if(skills.isWeb()){
            web.setChecked(true);
        }
        if(skills.isReact()){
            react.setChecked(true);
        }
    }
}