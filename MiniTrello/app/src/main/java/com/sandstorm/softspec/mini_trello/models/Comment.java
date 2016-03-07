package com.sandstorm.softspec.mini_trello.models;

import java.util.Date;

/**
 * Created by Zen on 3/7/16.
 */
public class Comment {

    private String message;
    private final String createdReplyTime;


    public Comment(String message){
        this.message = message;
        Date date = new Date();
        createdReplyTime = date.toLocaleString();
    }

    public String getCommentMessage(){
        return message;
    }

    public String getCreatedCommentTime(){
        return  createdReplyTime;
    }





}
