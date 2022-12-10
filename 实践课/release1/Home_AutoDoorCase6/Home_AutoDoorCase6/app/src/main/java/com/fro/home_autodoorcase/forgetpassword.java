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

public class forgetpassword extends Activity implements View.OnClickListener {
    EditText password1,password2;
    Button btn;
    protected void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        setContentView(R.layout.forgetpassword);
        password1=(EditText)findViewById(R.id.password1);
        password2=(EditText)findViewById(R.id.password2);
        btn=(Button)findViewById(R.id.bt_confirm);
        btn.setOnClickListener(this);
    }
    public void onClick(View v) {


        String pwd0 = password1.getText().toString();
        String pwd1 = password2.getText().toString();
        if (v.getId() == R.id.bt_confirm) {
            if (pwd0.equals("")) {
                Toast.makeText(forgetpassword.this, "密码不能为空", Toast.LENGTH_SHORT).show();

            } else if (!pwd0.equals(pwd1)) {
                Toast.makeText(forgetpassword.this, "两次输入不一致请重新输入", Toast.LENGTH_SHORT).show();

            } else {
                SQLiteDatabase db;
                MyHelper helper = new MyHelper(v.getContext());
                db = helper.getWritableDatabase();
                Cursor cursor = db.query("superuser", null, null, null, null, null, null);
                db.execSQL("delete from superuser");
                db.execSQL("delete from Member");
                db.execSQL("delete from time");
                helper.su(db, pwd0);
                cursor.close();
                db.close();
                Toast.makeText(this, "重置成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
