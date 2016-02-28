package com.sandstorm.softspec.mini_trello.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.mini_trello.R;
import com.sandstorm.softspec.mini_trello.models.CardList;
import com.sandstorm.softspec.mini_trello.models.Storage;

public class NewListActivity extends AppCompatActivity {

    private EditText title;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        initcomponents();
    }

    private void initcomponents() {
        title = (EditText) findViewById(R.id.new_title_edit);
        saveButton = (Button) findViewById(R.id.save_list_button);
        System.out.println(title.getText().toString());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Storage.getInstance().addList(new CardList(title.getText().toString()));
                System.out.println("Size in newListActivity : " + Storage.getInstance().loadList().size());
=======

>>>>>>> b7b16c658e0319e1142f96b6175b675877ccd812
                finish();
            }
        });
    }

    private void saveNewList() {
        Storage.getInstance().addList(new CardList(title.getText().toString()));
    }

}
