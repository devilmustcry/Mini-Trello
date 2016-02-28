package com.sandstorm.softspec.mini_trello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CardListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CardList> cardLists;
    private ListView cardListView;
    private CardListAdapter cardListAdapter;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();

    }

    private void initComponents() {

        cardLists = new ArrayList<CardList>();
        cardListAdapter = new CardListAdapter(this, R.layout.list_cell, cardLists);
        cardListView = (ListView) findViewById(R.id.main_list_view);
        cardListView.setAdapter(cardListAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, NewListActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        count++;

        cardLists.clear();

        for(CardList cardList : Storage.getInstance().loadList()) {

            cardLists.add(cardList);
        }
        cardListAdapter.notifyDataSetChanged();

    }

}
