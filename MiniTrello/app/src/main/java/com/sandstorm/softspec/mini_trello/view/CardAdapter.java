package com.sandstorm.softspec.mini_trello.view;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.sandstorm.softspec.mini_trello.models.Card;

import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardAdapter extends ArrayAdapter<Card> {


    public CardAdapter(Context context , int resource , List<Card> objects){
        super(context, resource, objects);
    }

}
