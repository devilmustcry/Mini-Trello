package com.sandstorm.softspec.mini_trello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.sandstorm.softspec.mini_trello.CustomClickListener;
import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CardViewListAdapter;
import com.sandstorm.softspec.mini_trello.view.MainSpinnerAdapter;
import com.sandstorm.softspec.mini_trello.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CardList> cardLists;
    private List<Tag> tagLists;
    private CardViewListAdapter cardListAdapter;
    private RecyclerView cardListView;
    private MainSpinnerAdapter spinnerAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("MiniTrello");

        setSupportActionBar(toolbar);

        initComponents();

    }

    private void initComponents() {

        cardLists = new ArrayList<CardList>();
        tagLists = new ArrayList<Tag>();

        cardListAdapter = new CardViewListAdapter(cardLists,new CustomClickListener(){
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);

                CardList dummy = cardLists.get(position);
                Log.i("Dummy",dummy.getListTitle());
                int index = Storage.getInstance().findListIndex(dummy);
                intent.putExtra("index",index);
                startActivity(intent);

            }
        });

        cardListView = (RecyclerView) findViewById(R.id.rv_main);
        cardListView.setHasFixedSize(true);
        cardListView.setAdapter(cardListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        cardListView.setLayoutManager(llm);

        spinner = (Spinner) findViewById(R.id.main_spinner);

        spinnerAdapter = new MainSpinnerAdapter(this,tagLists);


        spinner.setAdapter(spinnerAdapter);
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    cardLists.clear();
                    for(CardList cardList : Storage.getInstance().loadList()) {

                        cardLists.add(cardList);
                    }
                }
                else {
                    cardLists.clear();
                    for (CardList cardList : Storage.getInstance().getListFromTag(Storage.getInstance().getTagList().get(position))) {
                        cardLists.add(cardList);
                    }
                }
                cardListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
//        count++;

        cardLists.clear();
        if(spinner.getSelectedItemPosition()==0||spinner.getSelectedItemPosition()==-1) {
            for (CardList cardList : Storage.getInstance().loadList()) {

                cardLists.add(cardList);
            }
        }
        else {
            for (CardList cardList : Storage.getInstance().getListFromTag(Storage.getInstance().getTagList().get(spinner.getSelectedItemPosition()))) {
                cardLists.add(cardList);
            }
        }
        cardListAdapter.notifyDataSetChanged();

        tagLists.clear();

        for(Tag tag : Storage.getInstance().getTagList()) {

            tagLists.add(tag);

        }

        spinnerAdapter.notifyDataSetChanged();

    }



}
