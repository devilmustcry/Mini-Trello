package com.sandstorm.softspec.mini_trello.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;

public class ListActivity extends AppCompatActivity {

    CardList dummyList;
    CardList cardList;
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

        dummyList = (CardList) getIntent().getSerializableExtra("cardLists");

        cardList = Storage.getInstance().findList(dummyList);

        listTitle = (TextView) findViewById(R.id.list_title);

        listTitle.setText(cardList.getListTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });


    }

}
