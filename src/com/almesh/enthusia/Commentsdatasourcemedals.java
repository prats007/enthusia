package com.almesh.enthusia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Commentsdatasourcemedals {
	private SQLiteDatabase databasemedals;	
	private MySQLiteHelpermedals dbHelpermedals;
	public Commentsdatasourcemedals(Context context) {
		dbHelpermedals = new MySQLiteHelpermedals(context);
	}
public void addmedals(String branch,String gold,String silver,String bronze) {  
		
		ContentValues values = new ContentValues();
			values.put("BRANCH",branch);
			values.put("GOLD",gold);
			values.put("SILVER",silver);
			values.put("BRONZE",bronze);
			
			
			
			databasemedals.insert(MySQLiteHelpermedals.TABLE_NAME, null,values);
			
			Log.d("medals", "branch= "+branch+" gold= "+gold);
			
	}

public void open() throws SQLException {
	databasemedals = dbHelpermedals.getWritableDatabase();
}
public void read() {
	databasemedals=dbHelpermedals.getReadableDatabase();
}
public void cleardata()
{
	String[] dummy={};
	databasemedals.delete(MySQLiteHelpermedals.TABLE_NAME,"1",dummy);
}

public void viewmedals()
{
	read();
	Cursor cursor2 = null;
	
	cursor2 = databasemedals.query(MySQLiteHelpermedals.TABLE_NAME,null,null, null,null, null,"GOLD DESC" );
	String gold=null,silver=null,bronze=null,branch=null;
	
	cursor2.moveToFirst();
	while(!cursor2.isAfterLast())
	{
		branch=cursor2.getString(cursor2.getColumnIndex("BRANCH"));
		gold=cursor2.getString(cursor2.getColumnIndex("GOLD"));
		silver=cursor2.getString(cursor2.getColumnIndex("SILVER"));
		bronze=cursor2.getString(cursor2.getColumnIndex("BRONZE"));
		Log.d("medalsinviewmedals", "branch= "+branch+" gold= "+gold);
		EnthusiaActivity.displaymedals(branch,gold,silver,bronze);
		cursor2.moveToNext();
		
	}
	cursor2.close();
	


}
public void close() {
	dbHelpermedals.close();
}
}