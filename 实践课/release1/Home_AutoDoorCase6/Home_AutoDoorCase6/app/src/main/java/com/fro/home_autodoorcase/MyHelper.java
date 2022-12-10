package com.fro.home_autodoorcase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static String dbName="Door.db";
    private static String tableName1="Member";
    private static String tableName2="time";
    private static String superuser="superuser";


    public MyHelper(Context context){
        super(context,"door.db",null,2);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+tableName1+"("
                +" id varchar ,"
                +" cardID  varchar)");
        db.execSQL("create table if not exists "+tableName2+"("
                +" id varchar ,"
                +" time varchar)"
        );
        db.execSQL("create table if not exists "+superuser+"("
                +" password varcher)"
        );
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists "+tableName1);
        db.execSQL("drop table if exists "+tableName2);
        db.execSQL("drop table if exists "+superuser);
    }
    public void register(SQLiteDatabase db,String id,String card){
        ContentValues cValue=new ContentValues();
        cValue.put("id",id);
        cValue.put("cardID",card);
        db.insert(tableName1,null,cValue);
    }
    public void timeset(SQLiteDatabase db,String id,String time){
        ContentValues cValue=new ContentValues();
        cValue.put("id",id);
        cValue.put("time",time);
        db.insert(tableName2,null,cValue);
    }
    public void su(SQLiteDatabase db,String pw){
        ContentValues cValue=new ContentValues();
        cValue.put("password",pw);
        db.insert(superuser,null,cValue);
    }
    public void delete(SQLiteDatabase db,String table,String m){
        String sql="delete from "+table + " where id='"+m+"'";
        db.execSQL(sql);
    }
}

