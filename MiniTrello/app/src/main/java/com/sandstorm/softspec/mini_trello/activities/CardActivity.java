package com.sandstorm.softspec.mini_trello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.Comment;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.view.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Comment> comments;
    private Card card;
//    private Card dummyCard;
//    private CardList cardList;
//    private CardList dummyList;
    private TextView title;
    private TextView description;
    private AlertDialog.Builder dialogBuilder;
    private Button commentButton;
    private EditText commentInput;
    private CommentAdapter commentAdapter;
    private ListView commentListView;
    private Button deleteButton;


    private void commentDialog() {
        commentInput = new EditText(this);
        dialogBuilder.setTitle("Comment");
        dialogBuilder.setMessage("Your Comment");
        dialogBuilder.setView(commentInput);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveComment();
            }

        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelComment();
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

        dialogBuilder = new AlertDialog.Builder(this);
        title = (TextView) findViewById(R.id.card_title);
        description = (TextView) findViewById(R.id.card_description);
        comments = new ArrayList<Comment>();
        commentListView = (ListView) findViewById(R.id.comment_list_view);
        deleteButton = (Button) findViewById(R.id.delete_card_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeleteButton();
            }
        });


        commentAdapter = new CommentAdapter(this, R.layout.cell, comments);
        commentListView.setAdapter(commentAdapter);

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

    private void setDeleteButton() {
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
                cancelDelete();
            }
        });
        AlertDialog deleteDialog = dialogBuilder.create();
        deleteDialog.show();
    }

    private void delete() {
        Storage.getInstance().loadList().get((int) getIntent()
                .getSerializableExtra("cardListIndex")).loadList()
                .remove((int) getIntent().getSerializableExtra("cardIndex"));
    }

    @Override
    public void onClick(View v) {

    }

    private void cancelComment() {
        Toast.makeText(getApplicationContext(), "Your Comment has been disposed", Toast.LENGTH_SHORT).show();

    }
    private void cancelDelete() {
        Toast.makeText(getApplicationContext(), "Your card are not deleted", Toast.LENGTH_SHORT).show();
    }

    private void saveComment() {
        Storage.getInstance()
                .loadList().get((int) getIntent().getSerializableExtra("cardListIndex"))
                    .loadList().get((int) getIntent().getSerializableExtra("cardIndex"))
                        .addComment(commentInput.getText().toString());


        restart();

        Toast.makeText(getApplicationContext(), "Your Comment has been saved", Toast.LENGTH_SHORT).show();
    }

    private void restart() {
        comments.clear();

        for(Comment comment :  Storage.getInstance().loadList().get((int) getIntent().getSerializableExtra("cardListIndex")).loadList().get((int) getIntent().getSerializableExtra("cardIndex")).getCommentList()) {

            comments.add(comment);
        }
        commentAdapter.notifyDataSetChanged();

    }
}
