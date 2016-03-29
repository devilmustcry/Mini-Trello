package com.sandstorm.softspec.mini_trello.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    private AlertDialog.Builder commentDialogBuilder;
    private AlertDialog.Builder deleteDialogBuilder;
    private Button commentButton;
    private EditText commentInput;
    private CommentAdapter commentAdapter;
    private ListView commentListView;
    private Button deleteButton;
    private EditText titleEdit;
    private EditText descriptionEdit;


    private void commentDialog() {
        commentInput = new EditText(this);
        commentDialogBuilder.setTitle("Comment");
        commentDialogBuilder.setMessage("Your Comment");
        commentDialogBuilder.setView(commentInput);
        commentDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveComment();
            }

        });
        commentDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelComment();
            }
        });

        AlertDialog commentDialog = commentDialogBuilder.create();
        commentDialog.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initComponents();
    }

    private void initComponents() {

        commentDialogBuilder = new AlertDialog.Builder(this);
        deleteDialogBuilder = new AlertDialog.Builder(this);
        title = (TextView) findViewById(R.id.card_title);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setVisibility(View.INVISIBLE);
                titleEdit.setVisibility(View.VISIBLE);
            }
        });

        description = (TextView) findViewById(R.id.card_description);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.INVISIBLE);
                descriptionEdit.setVisibility(View.VISIBLE);
            }
        });

        comments = new ArrayList<Comment>();
        commentListView = (ListView) findViewById(R.id.comment_list_view);
        deleteButton = (Button) findViewById(R.id.delete_card_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeleteButton();
            }
        });

        titleEdit = (EditText) findViewById(R.id.card_title_edit);
        titleEdit.setVisibility(View.INVISIBLE);

        titleEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    Storage.getInstance().loadList().get((int) getIntent()
                            .getSerializableExtra("cardListIndex")).loadList()
                                .get((int) getIntent().getSerializableExtra("cardIndex"))
                                    .setCardTitle(titleEdit.getText().toString());
                    title.setText(titleEdit.getText().toString());
                    titleEdit.setVisibility(View.INVISIBLE);
                    title.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

        descriptionEdit = (EditText) findViewById(R.id.card_description_edit);
        descriptionEdit.setVisibility(View.INVISIBLE);

        descriptionEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    Storage.getInstance().loadList().get((int) getIntent()
                            .getSerializableExtra("cardListIndex")).loadList()
                                .get((int) getIntent().getSerializableExtra("cardIndex"))
                                    .setCardDescription(descriptionEdit.getText().toString());
                    description.setText(descriptionEdit.getText().toString());
                    descriptionEdit.setVisibility(View.INVISIBLE);
                    description.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
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

        deleteDialogBuilder.setTitle("Delete");
        deleteDialogBuilder.setMessage("Are you sure?");
        deleteDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
                finish();
            }
        });
        deleteDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelDelete();
            }
        });
        AlertDialog deleteDialog = deleteDialogBuilder.create();

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
