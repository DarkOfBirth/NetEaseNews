package com.example.dllo.neteasenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ggs on 16/9/26.
 */
public class DBTools {

    private SQLiteDatabase database;

    public DBTools(Context context) {

        MySQLHelper helper = new MySQLHelper
                (context, DBValues.DB_NAME , null , 1);
        database = helper.getWritableDatabase();
    }

    public void insertStudentTable
            (StudentBean bean ){
            ContentValues values = new ContentValues();
            values.put(DBValues.STUDENT_TABLE_NAME, bean.getName());
            values.put(DBValues.STUDENT_TABLE_AGE , bean.getAge());
            database.insert(DBValues.TABLE_NAME , null , values);
    }

    public void deleteStudentTable(StudentBean bean){

        database.delete(DBValues.TABLE_NAME,
                DBValues.STUDENT_TABLE_NAME + " = ?" ,
                new String[]{bean.getName()});

    }

    public ArrayList<StudentBean> queryStudentTable (){

        ArrayList<StudentBean> beanArrayList =
                new ArrayList<>();

        Cursor cursor = database.query(DBValues.TABLE_NAME ,
                null, null, null, null, null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.
                        getString(cursor.getColumnIndex(DBValues.STUDENT_TABLE_NAME));
                String age = cursor.
                        getString(cursor.getColumnIndex(DBValues.STUDENT_TABLE_NAME));

                StudentBean bean = new StudentBean();
                bean.setName(name);
                bean.setAge(age);
                beanArrayList.add(bean);
                Log.d("MainActivity", name + age);
            }
        }
        return beanArrayList;
    }

    /**
     * 更新数据库表
     * @param bean : 更新后的数据
     * @param name : 想要将谁更新
     */
    public void updateStudentTable(StudentBean bean, String name ){

        ContentValues values = new ContentValues();
        values.put(DBValues.STUDENT_TABLE_NAME , bean.getName());
        values.put(DBValues.STUDENT_TABLE_NAME , bean.getAge());

        database.update(DBValues.TABLE_NAME ,
                values ,
                 DBValues.STUDENT_TABLE_NAME + " = ?" ,
                new String[]{name});

    }

    public void insertHeadtiaoTable(HeadBean bean) {
        //(去重复) 判断, 如果这个数据类已经在数据库里了, 那么不插入
        boolean isExist = false;
        Cursor cursor = database.query("head", null, "title = ?", new String[]{bean.getTitle()}, null, null, null);
        int count = cursor.getCount();
        if (count > 0) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put("title", bean.getTitle());
        values.put("imgUrl", bean.getImgUrl());
        database.insert("head", null, values);
    }

    public ArrayList<HeadBean> queryHeadTable() {
        ArrayList<HeadBean> headBeen = new ArrayList<>();
        Cursor cursor = database.query("head", null, null, null, null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String imgUrl = cursor.getString(cursor.getColumnIndex("imgUrl"));

                HeadBean bean = new HeadBean();
                bean.setTitle(title);
                bean.setImgUrl(imgUrl);
                Log.d("DBTools", title);
                Log.d("DBTools", imgUrl);
                headBeen.add(bean);
            }
        }
        return headBeen;
    }
}
