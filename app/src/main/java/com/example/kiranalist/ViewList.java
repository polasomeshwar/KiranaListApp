package com.example.kiranalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.List;

public class ViewList extends AppCompatActivity {

    TextView t1;
    String listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        t1 = findViewById(R.id.textView);
        t1.setMovementMethod(new ScrollingMovementMethod());
        Intent i = getIntent();
        String s = i.getStringExtra("Title");
        MyDbHandler db = new MyDbHandler(getApplicationContext());
        List<listmodel> l1 = db.getallLists();
        for(listmodel l:l1)
        {
            if(l.getTitle().equals(s))
            {
                listitems = l.getList();
            }
        }
        t1.setText(listitems);
    }
}
