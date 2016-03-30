package com.sandstorm.softspec.mini_trello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sandstorm.softspec.mini_trello.CustomClickListener;
import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CardViewAdapter;

import org.w3c.dom.Text;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Card> cards;
    private CardViewAdapter cardAdapter;
    private RecyclerView cardListView;
    private CardList cardList;
    TextView listTitle;
    EditText editListTitle;
    TextView listCardDate;
    private AlertDialog.Builder dialogBuilder;



    private void deleteDialog() {
        dialogBuilder.setTitle("Delete");
        dialogBuilder.setMessage("Are you sure?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
                finish();
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Your card are not deleted", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog deleteDialog = dialogBuilder.create();
        deleteDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MiniTrello");
        setSupportActionBar(toolbar);

        initComponents();


    }

    private void delete() {
        Storage.getInstance().loadList().remove((int) getIntent().getSerializableExtra("index"));
        finish();

    }

    private void initComponents() {

        cards = new ArrayList<Card>();

        dialogBuilder = new AlertDialog.Builder(this);
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

        listTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listTitle.setVisibility(View.INVISIBLE);
                editListTitle.setVisibility(View.VISIBLE);
            }
        });

        editListTitle = (EditText) findViewById(R.id.list_title_edit);
        editListTitle.setVisibility(View.INVISIBLE);
        editListTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("index"))
                            .setListTitle(editListTitle.getText().toString());
                    listTitle.setText(editListTitle.getText().toString());
                    editListTitle.setVisibility(View.INVISIBLE);
                    listTitle.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
//        listTitle.setText(cardList.getListTitle());

        setTitle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, NewCardActivity.class);
                intent.putExtra("cardListIndex", getIntent().getSerializableExtra("index"));

                startActivity(intent);
            }
        });


        FloatingActionButton delete_button = (FloatingActionButton) findViewById(R.id.delete_list_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Storage.getInstance().deleteList(Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("index")));
                deleteDialog();
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

    private void setTitle() {

        String title = cardList.getListTitle();

        title += " # " + cardList.getTag().getTagName();
        listTitle.setText(title);
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
