package com.sandstorm.softspec.mini_trello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;

public class CardActivity extends AppCompatActivity {

    private Card card;
//    private Card dummyCard;
    private CardList cardList;
    private CardList dummyList;
    private TextView title;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initComponents();
    }

    private void initComponents() {

        title = (TextView) findViewById(R.id.card_title);
        description = (TextView) findViewById(R.id.card_description);

//        dummyList = (CardList) getIntent().getSerializableExtra("cardList");

//        dummyCard = (Card) getIntent().getSerializableExtra("card");
        card = Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("cardListIndex")).loadList().get((int) getIntent().getSerializableExtra("cardIndex"));

        title.setText(card.getTitle());
        description.setText(card.getDescription());

    }
}
