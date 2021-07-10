package com.example.kiranalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewList extends AppCompatActivity {

    TextView t1;
    String listitems;
    String iurllist;
    String title;
    Button share;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        t1 = findViewById(R.id.textView);
        share = findViewById(R.id.sharelist);
        edit = findViewById(R.id.update);
        t1.setMovementMethod(new ScrollingMovementMethod());
        Intent i = getIntent();
        String s = i.getStringExtra("Title");
        MyDbHandler db = new MyDbHandler(getApplicationContext());
        List<listmodel> l1 = db.getallLists();
        for(listmodel l:l1)
        {
            if(l.getTitle().equals(s))
            {
                title = l.getTitle();
                listitems = l.getList();
                iurllist = listitems;
            }
        }
        t1.setText(listitems);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT,"Kirana List '\n'"+iurllist);
                i.setType("text/plain");
                startActivity(i);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Edit.class);
                i.putExtra("title",title);
                i.putExtra("items",listitems);
                startActivity(i);
            }
        });
    }
}
