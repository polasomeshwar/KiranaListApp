package com.example.kiranalist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+Params.DB_TABLE+"("+Params.KEY_ID+" INTEGER PRIMARY KEY,"
                +Params.KEY_TITLE+" TEXT UNIQUE,"+Params.KEY_LIST+" TEXT"+")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addItem(listmodel l)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_TITLE,l.getTitle());
        Log.d("pola",l.getTitle());
        contentValues.put(Params.KEY_LIST,l.getList());
        db.insert(Params.DB_TABLE,null,contentValues);
        db.close();
    }
    public boolean check(listmodel l)
    {
        List<listmodel> l1 = new ArrayList<listmodel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+Params.DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        boolean result = true;
        if(cursor.moveToFirst())
        {
            do {
                listmodel l2 = new listmodel();
                l2.setId(Integer.parseInt(cursor.getString(0)));
                l2.setTitle(cursor.getString(1));
                l2.setList(cursor.getString(2));
                l1.add(l2);
            }while(cursor.moveToNext());
        }
        for(listmodel l3:l1)
        {
            if(l3.getTitle().equals(l.getTitle()))
            {
                result=false;
            }
        }
        return result;
    }
    public List<listmodel> getallLists()
    {
        List<listmodel> l1 = new ArrayList<listmodel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+Params.DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                listmodel l2 = new listmodel();
                l2.setId(Integer.parseInt(cursor.getString(0)));
                l2.setTitle(cursor.getString(1));
                l2.setList(cursor.getString(2));
                l1.add(l2);
            }while(cursor.moveToNext());
        }
        return l1;
    }
    public void deletes(String s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.DB_TABLE,Params.KEY_TITLE+"=?",new String[]{s});
    }
//    public String getListitems(String title)
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * From"+Params.DB_TABLE+" WHERE "+Params.KEY_TITLE+" = "+title;
//        Cursor cursor = db.rawQuery(query,null);
//        String s="";
//        if(cursor.moveToFirst())
//        {
//            s = cursor.getString(0);
//        }
//        return s;
//    }
}
