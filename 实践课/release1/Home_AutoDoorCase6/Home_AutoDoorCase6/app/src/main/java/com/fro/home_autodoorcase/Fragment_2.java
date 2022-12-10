package com.fro.home_autodoorcase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class Fragment_2 extends Fragment implements View.OnClickListener {
    Button start1;
    String textView;
    EditText password, username;

    /**
     * A simple {@link Fragment} subclass.
     */
    public Fragment_2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment2, container, false);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        start1 = (Button) getActivity().findViewById(R.id.btn_register);
        textView = Const.CARD_ID;
        password = (EditText) getActivity().findViewById(R.id.password_r);
        username = (EditText) getActivity().findViewById(R.id.username_r);

        start1.setOnClickListener(this);
    }
    public void onClick(View view) {
        String pwd=password.getText().toString().trim();
        String unm=username.getText().toString().trim();
        String ID=textView;
        String p;
        SQLiteDatabase db;
        MyHelper helper=new MyHelper(view.getContext());
        db=helper.getWritableDatabase();
        Cursor cursor=db.query("superuser",null,null,null,null,null,null);
        if(cursor.moveToNext()){
            cursor.moveToFirst();
            p=cursor.getString(0);
        }
        else{
            p="12345";
        }
        if(ID==null) {
            Toast.makeText(view.getContext(), "请刷卡！", Toast.LENGTH_SHORT).show();
        }
        else if(pwd.equals(p)) {
            db = helper.getWritableDatabase();
            String select = "id=?";
            String[] columns = new String[]{"id"};
            String[] selectionArgs = new String[]{unm};
            cursor = db.query("Member", columns,
                    select, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                Toast.makeText(view.getContext(), "信息已存在！", Toast.LENGTH_SHORT).show();
            } else {
                select = "cardid=?";
                columns = new String[]{"id"};
                selectionArgs = new String[]{ID};
                cursor = db.query("Member", columns,
                        select, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(view.getContext(), "信息已存在！", Toast.LENGTH_SHORT).show();
                } else {
                    helper.register(db, unm, ID);
                    Toast.makeText(view.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(view.getContext(), "管理员密码错误", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }
}
