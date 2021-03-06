package com.finalProject.devmatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.SkillSet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;
import type.CreateSkillsetInput;

public class CreateProfile extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";

    final Developer dev = new Developer();
    final SkillSet skills = new SkillSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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

        Log.i(TAG,"Create Profile Activity Created");

//        final EditText name = findViewById(R.id.name);
        final EditText github = findViewById(R.id.github);
//        final EditText email = findViewById(R.id.email);
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

//                dev.setName(name.getText().toString());
                dev.setGithub(github.getText().toString());
//                dev.setEmail(email.getText().toString());

                if(frontEnd.isChecked()) {
                    dev.setType("Front End");
                } else if(backEnd.isChecked()) {
                    dev.setType("Back End");
                } else if(fullStack.isChecked()) {
                    dev.setType("Full Stack");
                }

                if(java.isChecked()) {
                    skills.setJava(true);
                }
                if(python.isChecked()) {
                    skills.setPython(true);
                }
                if(cSharp.isChecked()) {
                    skills.setcSharp(true);
                }
                if (cPlusPlus.isChecked()) {
                    skills.setCplusplus(true);
                }
                if (ruby.isChecked()) {
                    skills.setRuby(true);
                }
                if (dotNet.isChecked()) {
                    skills.setDotNet(true);
                }
                if (javascript.isChecked()) {
                    skills.setJavascript(true);
                }
                if (sql.isChecked()) {
                    skills.setSql(true);
                }
                if (html.isChecked()) {
                    skills.setHtml(true);
                }
                if (css.isChecked()) {
                    skills.setCss(true);
                }
                if (postgresql.isChecked()) {
                    skills.setPostgresql(true);
                }
                if (mysql.isChecked()) {
                    skills.setMysql(true);
                }
                if (mongoDB.isChecked()) {
                    skills.setMongoDB(true);
                }
                if (dynamoDB.isChecked()) {
                    skills.setDynamoDB(true);
                }

                if (AWS.isChecked()) {
                    skills.setAWS(true);
                }
                if (heroku.isChecked()) {
                    skills.setHeroku(true);
                }
                if (firebase.isChecked()) {
                    skills.setFirebase(true);
                }
                if (azure.isChecked()) {
                    skills.setAzure(true);
                }

                if (iOS.isChecked()) {
                    skills.setiOS(true);
                }
                if (android.isChecked()) {
                    skills.setAndroid(true);
                }
                if (linux.isChecked()) {
                    skills.setLinux(true);
                }
                if (web.isChecked()) {
                    skills.setWeb(true);
                }
                if (react.isChecked()) {
                    skills.setReact(true);
                }

                dev.setSkills(skills);

                runSkillsetCreateMutation(skills);
                Intent i = new Intent(CreateProfile.this,Profile.class);
                CreateProfile.this.startActivity(i);
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
            getDevs();
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

    };

    public void getDevs(){

        mAWSAppSyncClient.query(ListDevelopersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(devsCallback);
    }
    private GraphQLCall.Callback<ListDevelopersQuery.Data> devsCallback = new
            GraphQLCall.Callback<ListDevelopersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull final Response<ListDevelopersQuery.Data> response) {
                    List<ListDevelopersQuery.Item> items = response.data().listDevelopers().items();
                    // items is a list of developers from DynamoDB
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.i(TAG,"Failure");
                }
            };



}