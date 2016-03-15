package com.sandstorm.softspec.mini_trello.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Comment;

import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {


    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell, null);
        }

        TextView comment_text = (TextView)v.findViewById(R.id.comment_text);
        TextView time_text = (TextView)v.findViewById(R.id.time_text);

        Comment comment = getItem(position);

        comment_text.setText(comment.getCommentMessage());
        time_text.setText(comment.getCreatedTime());

        return v;
    }
}
