package com.fro.home_autodoorcase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changepassword extends Activity implements View.OnClickListener {
    EditText password_o, password, password_2;
    Button b, go;
    String p;
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.changepassword);
        password_o = (EditText) findViewById(R.id.password_o);
        password = (EditText) findViewById(R.id.password);
        password_2 = (EditText) findViewById(R.id.password_2);
        b = (Button) findViewById(R.id.btn_confirm);
        go = (Button) findViewById(R.id.forgetpassword);
        b.setOnClickListener(this);
        go.setOnClickListener(this);


    }

    public void onClick(View v) {
        String pwd0 = password_o.getText().toString();
        String pwd1 = password.getText().toString();
        String pwd2 = password_2.getText().toString();
        String admin="admin";

        switch (v.getId()) {
            case R.id.btn_confirm:
                if(pwd0.equals("")){
                    Toast.makeText(changepassword.this, "原密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (!pwd1.equals(pwd2)) {
                    Toast.makeText(changepassword.this, "两次输入不一致请重新输入", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    SQLiteDatabase db;
                    MyHelper helper=new MyHelper(v.getContext());
                    db=helper.getWritableDatabase();
                    Cursor cursor=db.query("superuser",null,null,null,null,null,null);
                    if(cursor.getCount()!=0){
                        cursor.moveToFirst();
                        p=cursor.getString(0);
                    }
                    else{
                        p="12345";
                    }
                    if(pwd0.equals(p)){
                        String sql="delete from "+"superuser" + " where password= '"+p+"'";
                        db.execSQL(sql);
                        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                        helper.su(db,pwd1);
                    }
                    cursor.close();
                    db.close();

                }
                break;
            case R.id.forgetpassword:
                Intent intent = new Intent();
                intent.setClass(changepassword.this,forgetpassword.class);
                startActivity(intent);

            default:
                break;

        }

    }
}
