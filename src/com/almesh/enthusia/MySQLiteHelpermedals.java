package com.almesh.enthusia;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySQLiteHelpermedals extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String create_query="create table medals(ID integer primary key autoincrement,BRANCH text not null,GOLD text not null,SILVER text not null,BRONZE text null);";
	private static final String DATABASE_NAME = "medals.db";
	static final String TABLE_NAME = "medals";
	public MySQLiteHelpermedals(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(create_query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS medals");
		onCreate(db);
	}
}
