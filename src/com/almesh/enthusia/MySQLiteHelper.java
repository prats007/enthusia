package com.almesh.enthusia;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String create_query="create table enthusia(ID integer primary key autoincrement,P1 text not null,P2 text not null,SPORT text not null,DATE text not null,TIME text not null,WINNER text);";
	private static final String DATABASE_NAME = "enthusia.db";
	static final String TABLE_NAME = "enthusia";
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(create_query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS enthusia");
		onCreate(db);
	}
}
