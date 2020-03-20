package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PlanEditActivity extends AppCompatActivity {

    private TextInputEditText planTitle;
    private TextInputEditText planAuthor;
    private TextInputEditText planMember;
    private TextInputEditText planTerm;
    private TextInputEditText planBody;
    private String titleVal;
    private String authorVal;
    private String memVal;
    private String bodyVal;
    private Integer termVal;
    private Button planSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_edit);

        planTitle = findViewById(R.id.plan_title);
        planAuthor = findViewById(R.id.plan_author);
        planMember = findViewById(R.id.plan_member);
        planTerm = findViewById(R.id.plan_term);
        planBody = findViewById(R.id.plan_body);

        planSubmit = findViewById(R.id.plan_submit);
        planSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanData plan = new PlanData();
                titleVal = planTitle.getText().toString();
                authorVal = planAuthor.getText().toString();
                memVal = planMember.getText().toString();
                bodyVal = planBody.getText().toString();
                try{
                termVal = Integer.parseInt(planTerm.getText().toString());
                } catch (NumberFormatException e){
                } catch (Exception e){}
                if(vaildation()){
                    plan.setTitle(titleVal);
                    plan.setAuthor(authorVal);
                    plan.setMember(memVal);
                    plan.setTerm(termVal);
                    plan.setBody(bodyVal);
                    Intent intent = new Intent(PlanEditActivity.this, NewsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean vaildation(){
        if(titleVal.equals("")){
            Toast.makeText(getApplicationContext(), "Title is Required", Toast.LENGTH_SHORT).show();
            return false;
        } else if(authorVal.equals("")) {
            Toast.makeText(getApplicationContext(), "Author is Required", Toast.LENGTH_SHORT).show();
            return false;
        } else if(memVal.equals("")) {
            Toast.makeText(getApplicationContext(), "Member is Required", Toast.LENGTH_SHORT).show();
            return false;
        } else  if(termVal == null) {
            Toast.makeText(getApplicationContext(), "Expected term is Required", Toast.LENGTH_SHORT).show();
            return false;
        } else if(bodyVal.equals("")){
            Toast.makeText(getApplicationContext(), "Body is Required", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
