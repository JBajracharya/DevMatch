package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.SkillSet;

import javax.annotation.Nonnull;

import type.CreateDeveloperInput;

public class EditProfile extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private String TAG = "STG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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
//        removed Data Structures from create profile
//        final CheckBox arrays = findViewById(R.id.arrays);
//        final CheckBox linkedLists = findViewById(R.id.linkedLists);
//        final CheckBox stacks = findViewById(R.id.stacks);
//        final CheckBox queues = findViewById(R.id.queues);
//        final CheckBox trees = findViewById(R.id.trees);
//        final CheckBox hashes = findViewById(R.id.hashes);
//        final CheckBox heaps = findViewById(R.id.heaps);
//        final CheckBox sets = findViewById(R.id.sets);
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

        // THIS WILL BE REPLACED WITH A QUERY TO GET CURRENT USER OBJ
        final Developer dev = new Developer();
        //////////
        final SkillSet skills = new SkillSet();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
//
//                if (arrays.isChecked()) {
//                    skills.setArrays(true);
//                }
//                if (linkedLists.isChecked()) {
//                    skills.setLinkedlists(true);
//                }
//                if (stacks.isChecked()) {
//                    skills.setStacks(true);
//                }
//                if (queues.isChecked()) {
//                    skills.setQueues(true);
//                }
//                if (trees.isChecked()) {
//                    skills.setTrees(true);
//                }
//                if (hashes.isChecked()) {
//                    skills.setHashes(true);
//                }
//                if (heaps.isChecked()) {
//                    skills.setHeaps(true);
//                }
//                if (sets.isChecked()) {
//                    skills.setSets(true);
//                }

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

                // Then save this into database
            }
        });
    }
//    public void runTaskCreateMutation(String name, String github, String email, SkillSet skills, String type) {
//        CreateDeveloperInput createDeveloperInput = CreateDeveloperInput.builder().
//                name(name).
//                github(github).
//                email(email).
////                skills(skills).
//                type(type).
//                build();
//
//        mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(createTaskInput).build())
//                .enqueue(mutationCallback);
//    }
//
//    private GraphQLCall.Callback<CreateTaskMutation.Data> mutationCallback = new GraphQLCall.Callback<CreateTaskMutation.Data>() {
//        @Override
//        public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
//            Log.i(TAG, response.data().toString());
//        }
//
//        @Override
//        public void onFailure(@Nonnull ApolloException e) {
//            Log.e("Error", e.toString());
//        }
//
//    };
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 543 && resultCode == RESULT_OK && null != data) {
//
//            setImage(data.getData());
//        }
//    }
}