package com.almesh.enthusia;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySQLiteHelperRSS extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String create_query="create table rsssample(ID integer primary key autoincrement,TITLE text not null,CONTENT text not null,LINK text not null,SPORTURL text null);";
	private static final String DATABASE_NAME = "rsssample.db";
	static final String TABLE_NAME = "rsssample";
	public MySQLiteHelperRSS(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(create_query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS rsssample");
		onCreate(db);
	}
}
