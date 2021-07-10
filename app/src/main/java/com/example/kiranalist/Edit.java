package com.example.kiranalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    String title ;
    String List ;
    EditText listitems;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent i = getIntent();
        title = i.getStringExtra("title");
        List = i.getStringExtra("items");
        listitems = findViewById(R.id.itemslist);
        listitems.setText(List);
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHandler db = new MyDbHandler(getApplicationContext());
                String newlist = listitems.getText().toString();
                db.updates(title,newlist);
                Intent i = new Intent(getApplicationContext(),ListsView.class);
                startActivity(i);
            }
        });
    }
}