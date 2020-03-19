package com.example.practice;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public  class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {
    private  List<ChatListData> mDataset;
    public static View.OnClickListener onClickListener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_chat_room;
        public TextView TextView_msg;
        public View rootView;



        public MyViewHolder(View v) {
            super(v);
            TextView_chat_room = v.findViewById(R.id.TextView_chat_room);
            TextView_msg = v.findViewById(R.id.latest_msg);
            rootView = v;
            v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatListAdapter(List<ChatListData> myDataset,  View.OnClickListener onClick) {
        mDataset = myDataset;
        onClickListener = onClick;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat_list, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

            ChatListData chatList = mDataset.get(position);

            holder.TextView_chat_room.setText(chatList.getChatName());
            holder.TextView_msg.setText(chatList.getUserName());
            holder.rootView.setTag(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0: mDataset.size();
    }

    public void addChatList(ChatListData chatList){
        mDataset.add(chatList);
        notifyItemInserted(mDataset.size() - 1);
    }

    public ChatListData getChatList(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }
}


