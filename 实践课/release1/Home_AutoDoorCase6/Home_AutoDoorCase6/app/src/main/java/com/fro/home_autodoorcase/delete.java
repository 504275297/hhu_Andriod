package com.fro.home_autodoorcase;

import androidx.appcompat.app.AlertDialog;


import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends Activity implements View.OnClickListener{
    EditText user;
    Button confirm;
    String u;
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.delete);
        user=(EditText)findViewById(R.id.user);
        confirm=(Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        u=user.getText().toString().trim();     //u表示用户输入的用户名

        if (u.equals("")) {
            Toast.makeText(delete.this, "用户名不能为空", Toast.LENGTH_SHORT).show();

        }
        SQLiteDatabase db;
        MyHelper helper=new MyHelper(v.getContext());
        db=helper.getWritableDatabase();
        Cursor cursor=db.query("Member",null,null,null,null,null,null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            String p=cursor.getString(0);
            while(!cursor.isAfterLast()){
                if(p.trim().equals(u.trim())){
                    helper.delete(db,"Member",u);
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    break;
                }
                cursor.moveToNext();
                if(cursor.isAfterLast()){
                    Toast.makeText(this, "删除失败！未找到用户！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(this, "删除失败！未找到用户！", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();

    }

}
