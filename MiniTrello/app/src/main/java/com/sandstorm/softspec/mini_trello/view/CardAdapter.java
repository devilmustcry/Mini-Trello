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

import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardAdapter extends ArrayAdapter<Card> {


    public CardAdapter(Context context , int resource , List<Card> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell, null);
        }

        TextView card_title = (TextView)v.findViewById(R.id.title_text);

        Card card = getItem(position);

        card_title.setText(card.getTitle());


        return v;
    }
}
