package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.finalProject.devmatch.models.Developer;
import com.finalProject.devmatch.models.SkillSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditProfile extends AppCompatActivity {

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
        final CheckBox arrays = findViewById(R.id.arrays);
        final CheckBox linkedLists = findViewById(R.id.linkedLists);
        final CheckBox stacks = findViewById(R.id.stacks);
        final CheckBox queues = findViewById(R.id.queues);
        final CheckBox trees = findViewById(R.id.trees);
        final CheckBox hashes = findViewById(R.id.hashes);
        final CheckBox heaps = findViewById(R.id.heaps);
        final CheckBox sets = findViewById(R.id.sets);
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
                    skills.getLanguage().setJava(true);
                }
                if(python.isChecked()) {
                    skills.getLanguage().setPython(true);
                }
                if(cSharp.isChecked()) {
                    skills.getLanguage().setcSharp(true);
                }
                if (cPlusPlus.isChecked()) {
                    skills.getLanguage().setCplusplus(true);
                }
                if (ruby.isChecked()) {
                    skills.getLanguage().setRuby(true);
                }
                if (dotNet.isChecked()) {
                    skills.getLanguage().setDotNet(true);
                }
                if (javascript.isChecked()) {
                    skills.getLanguage().setJavascript(true);
                }
                if (sql.isChecked()) {
                    skills.getLanguage().setSql(true);
                }
                if (html.isChecked()) {
                    skills.getLanguage().setHtml(true);
                }
                if (css.isChecked()) {
                    skills.getLanguage().setCss(true);
                }

                if (arrays.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setArrays(true);
                }
                if (linkedLists.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setLinkedlists(true);
                }
                if (stacks.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setStacks(true);
                }
                if (queues.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setQueues(true);
                }
                if (trees.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setTrees(true);
                }
                if (hashes.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setHashes(true);
                }
                if (heaps.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setHeaps(true);
                }
                if (sets.isChecked()) {
                    skills.getDataStructuresAndAlgorithms().setSets(true);
                }

                if (postgresql.isChecked()) {
                    skills.getDatabase().setPostgresql(true);
                }
                if (mysql.isChecked()) {
                    skills.getDatabase().setMysql(true);
                }
                if (mongoDB.isChecked()) {
                    skills.getDatabase().setMongoDB(true);
                }
                if (dynamoDB.isChecked()) {
                    skills.getDatabase().setDynamoDB(true);
                }

                if (AWS.isChecked()) {
                    skills.getCloud().setAWS(true);
                }
                if (heroku.isChecked()) {
                    skills.getCloud().setHeroku(true);
                }
                if (firebase.isChecked()) {
                    skills.getCloud().setFirebase(true);
                }
                if (azure.isChecked()) {
                    skills.getCloud().setAzure(true);
                }

                if (iOS.isChecked()) {
                    skills.getPlatforms().setiOS(true);
                }
                if (android.isChecked()) {
                    skills.getPlatforms().setAndroid(true);
                }
                if (linux.isChecked()) {
                    skills.getPlatforms().setLinux(true);
                }
                if (web.isChecked()) {
                    skills.getPlatforms().setWeb(true);
                }
                if (react.isChecked()) {
                    skills.getPlatforms().setReact(true);
                }

                dev.setSkills(skills);

                // Then save this into database
            }
        });
    }
}