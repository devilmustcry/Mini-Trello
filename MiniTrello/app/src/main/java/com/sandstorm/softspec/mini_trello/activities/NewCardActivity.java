package com.sandstorm.softspec.mini_trello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.Card;
import com.sandstorm.softspec.mini_trello.models.Storage;

public class NewCardActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button saveButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        initComponents();
    }

    private void initComponents() {
        title = (EditText) findViewById(R.id.new_card_title_edit);

        description = (EditText) findViewById(R.id.new_card_description);


        saveButton = (Button) findViewById(R.id.save_card_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSaveButton();
                finish();
            }
        });


        cancelButton = (Button) findViewById(R.id.cancel_card_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void setSaveButton() {
     //   Card newCard = new Card(title.getText().toString(),description.getText().toString());
    }


}
