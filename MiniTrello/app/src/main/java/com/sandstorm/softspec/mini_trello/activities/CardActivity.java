package com.sandstorm.softspec.mini_trello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    private Card card;
//    private Card dummyCard;
    private CardList cardList;
    private CardList dummyList;
    private TextView title;
    private TextView description;
    private AlertDialog.Builder dialogBuilder;
    private Button commentButton;


    private void commentDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        EditText nameInput = new EditText(this);
        EditText commentInput = new EditText(this);
        dialogBuilder.setTitle("Comment");
        dialogBuilder.setMessage("Your Name?");
        dialogBuilder.setView(nameInput);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveComment();
            }

        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel();
            }
        });

        AlertDialog commentDialog = dialogBuilder.create();
        commentDialog.show();

    }


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
        card = Storage.getInstance().loadList().get((int) getIntent()
                .getSerializableExtra("cardListIndex")).loadList().get((int) getIntent().getSerializableExtra("cardIndex"));

        title.setText(card.getTitle());
        description.setText(card.getDescription());

        commentButton = (Button) findViewById(R.id.comment_card_button);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void cancel() {
        Toast.makeText(getApplicationContext(), "Your Comment has been disposed", Toast.LENGTH_SHORT).show();

    }

    private void saveComment() {

    }
}
