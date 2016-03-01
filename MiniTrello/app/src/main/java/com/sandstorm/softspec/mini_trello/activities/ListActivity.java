package com.sandstorm.softspec.mini_trello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CardAdapter;
import com.sandstorm.softspec.mini_trello.view.CardListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Card> cards;
    private CardAdapter cardAdapter;
    private ListView cardListView;
    private CardList dummyList;
    private CardList cardList;
    TextView listTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();


    }

    private void initComponents() {

        cards = new ArrayList<Card>();

        cardListView = (ListView) findViewById(R.id.card_list);

        cardAdapter = new CardAdapter(this, R.layout.cell, cards);

        cardListView.setAdapter(cardAdapter);

        dummyList = (CardList) getIntent().getSerializableExtra("cardLists");

        cardList = Storage.getInstance().findList(dummyList);

        listTitle = (TextView) findViewById(R.id.list_title);

        listTitle.setText(cardList.getListTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, NewCardActivity.class);
                intent.putExtra("cardList", dummyList);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        cards.clear();

        for(Card card :  Storage.getInstance().findList(dummyList).getList()) {

            cards.add(card);
        }
        cardAdapter.notifyDataSetChanged();

    }

}
