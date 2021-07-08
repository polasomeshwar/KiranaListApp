package com.example.kiranalist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListsView extends AppCompatActivity {
    ListView l1;
    ArrayAdapter<List<String>>ad;
    List<String> lists;
    List<listmodel> list;
    MyDbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_view);
        db = new MyDbHandler(getApplicationContext());
        l1 = findViewById(R.id.lists);
        lists = new ArrayList<String>();
        list = db.getallLists();
        for(listmodel l1:list)
        {
            lists.add(l1.getTitle());
        }
        ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lists){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                return view;
            }
        };
        l1.setAdapter(ad);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lists.get(position);
                Intent i = new Intent(view.getContext(),ViewList.class);
                i.putExtra("Title",s);
                startActivity(i);
            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(ListsView.this).setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are You Sure ?")
                        .setMessage("Do You Want To Delete This List ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = lists.get(which_item);
                                lists.remove(which_item);
                                db.deletes(s);
                                ad.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
    }
}
