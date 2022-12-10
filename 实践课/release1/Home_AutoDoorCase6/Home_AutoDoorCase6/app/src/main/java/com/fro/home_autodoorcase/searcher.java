package com.fro.home_autodoorcase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class searcher extends Activity implements View.OnClickListener{
    EditText ids;
    Button time,FINALLY;
    int flag=0;
    TextView search_sum;
    protected void onCreate(Bundle savedInstantState){

        super.onCreate(savedInstantState);
        setContentView(R.layout.search);
        ids=(EditText)findViewById(R.id.ids);
        time=(Button)findViewById(R.id.time);
        FINALLY=(Button)findViewById(R.id.FINALLY);
        time.setOnClickListener(this);
        FINALLY.setOnClickListener(this);
        search_sum = (TextView) findViewById(R.id.tv_sum);
}

    public void onClick(View v){

        String id = ids.getText().toString().trim();
        if (v.getId() == R.id.time) {
            final Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(searcher.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    c.set(year, monthOfYear, dayOfMonth);
                    time.setText(DateFormat.format("yyy-MM-dd", c));
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
            flag = 1;

        } else if (flag == 1) {    //选了日期
                if (ids.getText().toString().trim().equals("")) {      //只选了日期
                    String TIMES = time.getText().toString().trim();   //日期在此.
                    StringBuilder sum = new StringBuilder();
                    SQLiteDatabase db;
                    MyHelper ggg = new MyHelper(searcher.this);
                    db = ggg.getWritableDatabase();
                    Cursor cursor = db.query("time", null,
                            null, null, null, null, null);

                    sum.append("结果如下：");
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        String id1 = cursor.getString(0);
                        String time = cursor.getString(2);
                        if (TIMES.equals
                                (time.substring(0, TIMES.length()))) {    //截取前面的部分比较。如2018-12-31
                            sum.append("\nID       time\n");
                            sum.append(id1).append("\t").append(time).append("\n");
                        }

                        while (cursor.moveToNext()) {
                            String id2 = cursor.getString(0);
                            String time1 = cursor.getString(1);
                            if (TIMES.equals
                                    (time1.substring(0, TIMES.length()))) {
                                sum.append(id2).append("\t").append(time1).append("\n");
                            }
                        }
                    }
                    cursor.close();
                    db.close();
                    search_sum.setText(sum.toString());
                } else {    //选了日期，选了ID

                    String id3 = ids.getText().toString().trim();
                    String TIMES = time.getText().toString().trim();   //日期在此.
                    SQLiteDatabase db;
                    MyHelper ggg = new MyHelper(searcher.this);
                    db = ggg.getWritableDatabase();

                    StringBuilder sum = new StringBuilder();

                    String sql = "select * from time where id=\"" + id3 + "\"";

                    Cursor cursor = db.rawQuery(sql, null);
                    while (cursor.moveToNext()) {
                        String ID = cursor.getString(0); //获取第一列的值,第一列的索引从0开始
                        String TIME = cursor.getString(1);//获取第二列的值

                        if(TIMES.equals(TIME.substring(0,TIMES.length()))){
                            sum.append("ID          time:\n");
                            sum.append(ID).append("\t").append(TIME).append("\n");
                        }
                    }
                    cursor.close();
                    db.close();
                    search_sum.setText(sum.toString());



                }
            } else {     //flag=0；  //没选日期
                if (ids.getText().toString().trim().equals("")) {    //啥都没选
                    Toast.makeText(this, "选一个呗", Toast.LENGTH_SHORT).show();
                } else {       //只选了ID
                    String id4 = ids.getText().toString().trim();    //获取id

                    SQLiteDatabase db;
                    MyHelper ggg = new MyHelper(searcher.this);
                    db = ggg.getWritableDatabase();

                    StringBuilder sum = new StringBuilder();

                    String sql = "select * from time where id=\"" + id4 + "\"";
                    // System.out.println("差部分：" + sql);
                    Cursor cursor = db.rawQuery(sql, null);
                    while (cursor.moveToNext()) {
                        String ID = cursor.getString(0); //获取第一列的值,第一列的索引从0开始
                        String TIME = cursor.getString(1);//获取第二列的值
                        sum.append("ID                   time\n");
                        sum.append(ID).append("\t").append(TIME).append("\n");
                    }
                    cursor.close();
                    db.close();
                    search_sum.setText(sum.toString());
                }
            }
        }
    }
