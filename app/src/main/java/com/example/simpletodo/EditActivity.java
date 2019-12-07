package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private final String TAG = EditActivity.class.getSimpleName();

    EditText etItem;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById(R.id.etItem);
        btnSave = findViewById(R.id.btnSave);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Edit Item");


        String retrievedItemText = getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT);
        etItem.setText(retrievedItemText);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent that contains results of whatever the user modified
                Intent intent = new Intent();
                // pass the data (result of editing)
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                // set result of the intent
                setResult(RESULT_OK, intent);
                // finish the activity (close the screen and go back)
                finish();
            }
        });
    }
}
