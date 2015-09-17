package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	/** 数据库名称 */
	private static final String DB_NAME = "CRIMS_DB.db";
	/** 版本 */
	private static final int DB_VERSION = 1;
	
	private String table_create_sql;
	
	
	public DBHelper(Context context, String  table_create_sql) {
		super(context, DB_NAME, null, DB_VERSION);
		this.table_create_sql = table_create_sql;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(table_create_sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
