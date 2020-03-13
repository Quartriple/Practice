package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.ChatActivity;
import com.example.practice.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText user_chat, user_edit;
    private Button user_next;
    private List<ChatListData> mDataset;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        user_chat = (EditText) findViewById(R.id.user_chat);
        user_edit = (EditText) findViewById(R.id.user_edit);
        user_next = (Button) findViewById(R.id.user_next);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        user_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_edit.getText().toString().equals("") || user_chat.getText().toString().equals("")) {
                    return;
                }
                ChatListData chatList = new ChatListData();
                chatList.setChatName(user_chat.getText().toString());
                chatList.setUserName(user_edit.getText().toString());

                Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
                intent.putExtra("user_chat", user_chat.getText().toString());
                intent.putExtra("user_edit", user_edit.getText().toString());
                startActivity(intent);
            }
        });
        showChatList();

    }

    public void showChatList(){

        mDataset = new ArrayList<>();
        mAdapter = new ChatListAdapter(mDataset,  new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v.getTag() != null){
                    int postion = (int)v.getTag();
                    Intent intent = new Intent(ChatListActivity.this,ChatActivity.class);
                    intent.putExtra("user_chat", ((ChatListAdapter)mAdapter).getChatList(postion).getChatName());
                    intent.putExtra("user_edit", ((ChatListAdapter)mAdapter).getChatList(postion).getUserName());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(mAdapter);

        myRef.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatListData chatList = dataSnapshot.getValue(ChatListData.class);
                ((ChatListAdapter)mAdapter).addChatList(chatList);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}