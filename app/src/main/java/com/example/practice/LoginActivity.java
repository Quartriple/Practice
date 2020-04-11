package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoginActivity extends UtilActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Class[] mDataset = {
            GoogleLoginActivity.class,
            FacebookLoginActivity.class
    };
    private int[] login_desc = {
            R.string.desc_google_sign_in,
            R.string.desc_facebook_login
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.project_id);
        setSupportActionBar(toolbar);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new LoginAdapter(mDataset, login_desc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getTag() != null){
                    int position = (int)v.getTag();
                    String Lclass = ((LoginAdapter)mAdapter).getLoginClassName(position);
                    try{
                    Class to_class = Class.forName(Lclass);
                    Intent intent = new Intent(LoginActivity.this, to_class);
                    intent.putExtra("desc_id",((LoginAdapter)mAdapter).getDesc(position));
                    startActivity(intent);
                    } catch (Exception e){
                    }

                }
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
