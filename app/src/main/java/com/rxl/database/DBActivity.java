package com.rxl.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rxl.marqueeview.R;

public class DBActivity extends AppCompatActivity implements View.OnClickListener {


    private Button insert, delete, update, query;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:

                ContentValues values = new ContentValues();
                values.put("name", "777");
                values.put("sex", "222");

                long result = db.insert("test", null, values);
                Log.i("MainActivity", "插入结果--> " + result);

                break;
            case R.id.delete:

                long results = db.delete("test", "name=?", new String[]{"777"});
                Log.i("MainActivity", "删除结果--> " + results);

                break;
            case R.id.update:
                ContentValues values1 = new ContentValues();
                values1.put("name", "888");
                values1.put("sex", "222");
                long re = db.update("test", values1, "sex=?", new String[]{"222"});
                Log.i("MainActivity", "修改结果--> " + re);
                break;
            case R.id.query:
                Cursor cr = db.query("test", new String[]{"name"}, "sex=?", new String[]{"222"}, null, null, null);
                boolean r = cr.moveToFirst();
                Log.i("MainActivity", "查询结果--> " + r);
                Log.i("MainActivity", "查询结果--> " + cr.getString(0));

                break;
        }
    }
}
