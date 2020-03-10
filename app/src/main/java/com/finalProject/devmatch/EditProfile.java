package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

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
    }
}