package com.example.dllo.neteasenews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ggs on 16/9/26.
 */
public class MySQLHelper extends SQLiteOpenHelper {
    public MySQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //这个方法在运行时只走一次, 每次建表要重新卸载
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table head(" +
                "id integer primary key autoincrement," +
                " imgUrl BLOB , " +
                "title text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
