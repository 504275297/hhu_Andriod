package com.fro.home_autodoorcase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Main extends AppCompatActivity implements View.OnClickListener{
    LinearLayout bt_1,bt_2,bt_3;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private Fragment_1 fragment1;
    private Fragment_2 fragment2;
    private Fragment_3 fragment3;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bt_1=findViewById(R.id.line1_tea);
        bt_2=findViewById(R.id.line2_tea);
        bt_3=findViewById(R.id.line3_tea);
        /*
        se_1=findViewById(R.id.members);
        se_2=findViewById(R.id.search);
        se_3=findViewById(R.id.changepassword);
        se_4=findViewById(R.id.statistics);
         */
        iv1=findViewById(R.id.iv1_tea);
        iv2=findViewById(R.id.iv2_tea);
        iv3=findViewById(R.id.iv3_tea);
        Init();
        InitFragment(1);
        iv1.setImageResource(R.drawable.class2);
        MyHelper helper=new MyHelper(this);
    }
    private void Init(){
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        /*
        se_1.setOnClickListener(this);
        se_2.setOnClickListener(this);
        se_3.setOnClickListener(this);
        se_4.setOnClickListener(this);
         */
    }
    private void hideAllFragment(FragmentTransaction transaction){
        if (fragment1!= null){
            transaction.hide(fragment1);
        }

        if (fragment2!= null){
            transaction.hide(fragment2);
        }

        if (fragment3!= null){
            transaction.hide(fragment3);
        }

        // transaction.commit();
    }
    private void InitFragment(int index){
        //使用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();

        //启动事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //将所有的Fragment隐藏
        hideAllFragment(transaction);

        switch (index){
            case 1:
                if (fragment1== null){
                    fragment1 = new Fragment_1();
                    transaction.add(R.id.frame_content,fragment1);
                }
                else{
                    transaction.show(fragment1);
                }
                break;

            case 2:
                if (fragment2== null){
                    fragment2 = new Fragment_2();
                    transaction.add(R.id.frame_content,fragment2);
                }
                else{
                    transaction.show(fragment2);
                }
                break;

            case 3:
                if (fragment3== null){
                    fragment3 = new Fragment_3();
                    transaction.add(R.id.frame_content,fragment3);
                }
                else{
                    transaction.show(fragment3);
                }
                break;

        }
        //提交事务
        transaction.commit();
    }
    public void onClick(View v){
        restartButton();
        switch(v.getId()){
            case R.id.line1_tea:
                //Toast.makeText(StudentLand.this,"first", Toast.LENGTH_SHORT).show();
                iv1.setImageResource(R.drawable.class2);
                //tv_first.setTextColor(getResources().getColor(R.color.colorTextViewPress));
                InitFragment(1);
                break;

            case R.id.line2_tea:
                iv2.setImageResource(R.drawable.create2);
                //tv2.setTextColor(getResources().getColor(R.color.colorTextViewPress));
                InitFragment(2);
                break;

            case R.id.line3_tea:
                iv3.setImageResource(R.drawable.person2);
                //tv3.setTextColor(getResources().getColor(R.color.colorTextViewPress));
                InitFragment(3);
                break;
        }
    }
    private void restartButton(){
        //设置为未点击状态
        iv1.setImageResource(R.drawable.class1);
        iv2.setImageResource(R.drawable.create1);
        iv3.setImageResource(R.drawable.person1);
    }
}
