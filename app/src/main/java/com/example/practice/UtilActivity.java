package com.example.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UtilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mDatabase.getReference("Users");

    public ProgressBar mProgressBar;

    public void addUser(){
        FirebaseUser user = mAuth.getCurrentUser();
        Class fuser = user.getClass();
        mRef.push().setValue(fuser);
    }

    public void setProgressBar(int resId) {
        mProgressBar = findViewById(resId);
    }

    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.search :
                Toast.makeText(getApplicationContext(), "Search Click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option :

                return true;
            case R.id.news :
               Intent intent = new Intent(this, NewsActivity.class);
               startActivity(intent);

               return true;

            case R.id.chat:
                Intent chat_intent = new Intent(this, ChatListActivity.class);
                startActivity(chat_intent);
                return true;

            case R.id.plan :
                Intent plan_intent = new Intent(this, PlanListActivity.class);
                startActivity(plan_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
    } }
 /*   private void getNickname(){
        if(mAuth.getCurrentUser() != null) {
            String nickname = mAuth.getCurrentUser().getDisplayName();
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("nickname",nickname);
            startActivity(intent);
        }
    } */

}
