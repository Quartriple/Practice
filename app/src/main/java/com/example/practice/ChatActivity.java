package com.example.practice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends UtilActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ChatData> chatDataList;
    private EditText EditText_chat;
    private Button Button_send;
    private String nick;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private String CHAT_NAME;
    private String USER_NAME;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();

        CHAT_NAME = intent.getStringExtra("user_chat");
        USER_NAME = intent.getStringExtra("user_edit");




        mAuth = FirebaseAuth.getInstance();

        nick = mAuth.getCurrentUser().getEmail();




        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(CHAT_NAME);
        setSupportActionBar(toolbar);

        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String msg = EditText_chat.getText().toString();

               if(msg != null){
                   ChatData chat = new ChatData();
                   chat.setNickname(nick);
                   chat.setMsg(msg);
                   myRef.child("chat").child(CHAT_NAME).push().setValue(chat);
               }
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        chatDataList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatDataList, ChatActivity.this, nick);
        recyclerView.setAdapter(mAdapter);

        // Write a message to the database

        createNotificationChannel();
        openChat();
    }
    public void openChat(){


        myRef.child("chat").child(CHAT_NAME).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatData chat = dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter)mAdapter).addChat(chat);

                if(!chat.getNickname().equals(nick)) {
                    Intent intent = new Intent(ChatActivity.this,  ChatListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(ChatActivity.this, 0, intent, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(ChatActivity.this, "888")
                            .setStyle(new NotificationCompat.MessagingStyle("Me"))
                            .setSmallIcon(R.drawable.midas)
                            .setContentTitle(chat.getNickname())
                            .setContentText(chat.getMsg())
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(chat.getMsg()))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ChatActivity.this);

                    // notificationId is a unique int for each notification that you must define
                    notificationManager.notify(888, builder.build());
                }

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
    private void createNotificationChannel(){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.project_id);
            String description = getString(R.string.desc_firebase_ui);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("888", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
