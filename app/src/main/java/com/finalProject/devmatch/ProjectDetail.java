package com.finalProject.devmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProjectDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        TextView name = findViewById(R.id.textView4);
        TextView description = findViewById(R.id.textView8);
        TextView link = findViewById(R.id.textView10);

        String displayName = getIntent().getStringExtra("mNameView");
        String displayDescription = getIntent().getStringExtra("mDescriptionView");
        String displayLink = getIntent().getStringExtra("mLinkView");

        name.setText(displayName);
        description.setText(displayDescription);
        link.setText(displayLink);
    }
}
