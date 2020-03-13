package com.example.practice;

import java.io.Serializable;

public class ChatListData implements Serializable {

    private String chatName;
    private String userName;

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
