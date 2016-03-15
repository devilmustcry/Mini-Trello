package com.sandstorm.softspec.mini_trello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.CustomClickListener;
import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CardViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Card> cards;
    private CardViewAdapter cardAdapter;
    private RecyclerView cardListView;
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

        cardListView = (RecyclerView) findViewById(R.id.rv_list);
        cardListView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        cardListView.setLayoutManager(llm);

        cardAdapter = new CardViewAdapter(cards, new CustomClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(ListActivity.this, CardActivity.class);
                intent.putExtra("cardIndex", position);
                intent.putExtra("cardListIndex",getIntent().getSerializableExtra("index"));
                startActivity(intent);
            }
        });

        cardListView.setAdapter(cardAdapter);

//        dummyList = (CardList) getIntent().getSerializableExtra("cardLists");

        cardList = Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("index"));

        listTitle = (TextView) findViewById(R.id.list_title);

        listTitle.setText(cardList.getListTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, NewCardActivity.class);
                intent.putExtra("cardList", cardList);
                startActivity(intent);
            }
        });


        FloatingActionButton delete_button = (FloatingActionButton) findViewById(R.id.delete_list_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage.getInstance().deleteList(Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("index")));
                finish();
            }
        });


//       cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent intent = new Intent(ListActivity.this, CardActivity.class);
//               intent.putExtra("card", cards.get(position));
//               intent.putExtra("cardList", cardList);
//               startActivity(intent);
//           }
//       });




    }

    @Override
    protected void onStart() {
        super.onStart();

        cards.clear();

        for(Card card :  Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("index")).getList()) {

            cards.add(card);
        }
        cardAdapter.notifyDataSetChanged();

    }

}
