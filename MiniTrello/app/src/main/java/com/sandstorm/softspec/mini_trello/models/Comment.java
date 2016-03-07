package com.sandstorm.softspec.mini_trello.models;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Zen on 3/7/16.
 */
public class Comment {

    private String message;
    private long createdTime;


    public Comment(String message){
        this.message = message;
        createdTime = System.currentTimeMillis();
    }

    public String getCommentMessage(){
        return message;
    }

    public long getActualCreatedTime(){
        return createdTime;
    }

    public String getCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm");
        Date date = new Date(createdTime);
        return sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (createdTime != comment.createdTime) return false;
        return !(message != null ? !message.equals(comment.message) : comment.message != null);

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (int) (createdTime ^ (createdTime >>> 32));
        return result;
    }


    public static class createdTimeComparator implements Comparator<Comment>{

        @Override
        public int compare(Comment c1, Comment c2) {
            long l1 = c1.getActualCreatedTime();
            long l2 = c2.getActualCreatedTime();

            return (int)(l2 - l1) ;
        }
    }
}
