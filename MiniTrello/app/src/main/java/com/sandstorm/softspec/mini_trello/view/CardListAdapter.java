package com.sandstorm.softspec.mini_trello.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;

import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardListAdapter extends ArrayAdapter<CardList> {


    public CardListAdapter(Context context, int resource, List<CardList> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell, null);
        }

        TextView title = (TextView) v.findViewById(R.id.title_text);
        CardList lists = getItem(position);
        title.setText(lists.getListTitle());

        return v;
    }


}
