package com.example.kiranalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddList extends AppCompatActivity {

    EditText title;
    EditText items;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        title = findViewById(R.id.title);
        items = findViewById(R.id.items);
        Button b1 = findViewById(R.id.submit);
        final MyDbHandler db = new MyDbHandler(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = title.getText().toString();
                String l = items.getText().toString();
                if(t.length()!=0&&l.length()!=0)
                {
                    listmodel l1 = new listmodel();
                    l1.setTitle(t);
                    l1.setList(l);
                    boolean cheack = db.check(l1);
                    if(cheack) {
                        db.addItem(l1);
                        Intent i = new Intent(getApplicationContext(), ListsView.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(AddList.this, "Title already Taken", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddList.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
