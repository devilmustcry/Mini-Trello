package com.sandstorm.softspec.mini_trello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;
import com.sandstorm.softspec.mini_trello.models.Tag;

public class NewListActivity extends AppCompatActivity {

    private EditText title;
    private EditText tag;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        initcomponents();
    }

    private void initcomponents() {
        title = (EditText) findViewById(R.id.new_list_title_edit);

        tag = (EditText) findViewById(R.id.new_list_tag);

        saveButton = (Button) findViewById(R.id.save_list_button);

        cancelButton = (Button) findViewById(R.id.cancel_list_button);
//        System.out.println(title.getText().toString());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewList();
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });

    }

    private void saveNewList() {
        if(tag.getText().toString().equals(""))
            tag.setText("All");
        Storage.getInstance().addList(new CardList(title.getText().toString(),tag.getText().toString()));

        Storage.getInstance().addTag(new Tag(tag.getText().toString()));
//        System.out.println(Storage.getInstance().getTagList().toString());
    }

}
