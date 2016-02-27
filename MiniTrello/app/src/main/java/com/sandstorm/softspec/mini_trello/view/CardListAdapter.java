package com.sandstorm.softspec.mini_trello.view;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.sandstorm.softspec.mini_trello.models.CardList;

import java.util.List;

/**
 * Created by Zen on 2/27/16.
 */
public class CardListAdapter extends ArrayAdapter<CardList> {


    public CardListAdapter(Context context, int resource, List<CardList> objects){
        super(context, resource, objects);
    }


}
