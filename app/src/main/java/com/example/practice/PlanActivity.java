package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {
    private String planTitle;
    private String planAuthor;
    private String planMember;
    private String planBody;
    private String planTerm;
    private TextView dTitle;
    private TextView dAuthor;
    private TextView dMember;
    private TextView dTerm;
    private TextView dBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        planTitle = intent.getStringExtra("plan_title");
        planAuthor = intent.getStringExtra("plan_author");
        planMember = intent.getStringExtra("plan_member");
        planBody = intent.getStringExtra("plan_body");
        planTerm = intent.getStringExtra("plan_term");

        dTitle = findViewById(R.id.display_title);
        dAuthor = findViewById(R.id.display_author);
        dMember = findViewById(R.id.display_member);
        dTerm = findViewById(R.id.display_term);
        dBody = findViewById(R.id.display_body);

        dTitle.setText(planTitle);
        dAuthor.setText(planAuthor);
        dMember.setText(planMember);
        dTerm.setText(planTerm);
        dBody.setText(planBody);
    }
}
