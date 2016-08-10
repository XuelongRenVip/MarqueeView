package com.rxl.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * RenXL
 * 2016/7/21 0021
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE test"
                + "("
                + "id INTEGER primary key autoincrement,"
                + "name varchar(16) not null,"
                + "sex varchar(16) not null"
                + ")";
        db.execSQL(sql);
        Log.i("MainActivity", "创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
