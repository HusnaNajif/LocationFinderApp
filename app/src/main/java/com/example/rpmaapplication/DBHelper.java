package com.example.rpmaapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private  static final String dbname="sigapplication1.db";




    public DBHelper(Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create table users (userid text primary key,password text)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i, int i1) {
        Mydb.execSQL("drop table if EXISTS users");
        Mydb.execSQL("drop table if EXISTS items");

    }

    public boolean insertuser(String userid,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("userid",userid);
        cv.put("password",password);
        long result=Mydb.insert("users",null,cv);
        if (result == -1) {
            return false;
        }
        else{
            return  true;
        }
        }

    public boolean verifyusername(String userid){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * from users where userid=?",new String[] {userid} );
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }
    }
    public boolean verifyusernamepassword(String userid,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * from users where userid=? and password=?",new String[] {userid,password} );
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    }







