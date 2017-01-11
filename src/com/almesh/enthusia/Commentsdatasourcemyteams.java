package com.almesh.enthusia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Commentsdatasourcemyteams {
	private SQLiteDatabase databasemyteams;	
	private MySQLiteHelpermyteams dbHelpermyteams;
	public Commentsdatasourcemyteams(Context context) {
		dbHelpermyteams = new MySQLiteHelpermyteams(context);
	}
public void addmyteams(String branch,String sport) {  
		
		ContentValues values = new ContentValues();
			values.put("BRANCH",branch);
			values.put("SPORT",sport);
				
			
			databasemyteams.insert(MySQLiteHelpermyteams.TABLE_NAME, null,values);
			
			Log.d("myteamsadded", "branch= "+branch+" Sport= "+sport);
			
	}

public void open() throws SQLException {
	databasemyteams = dbHelpermyteams.getWritableDatabase();
}
public void read() {
	databasemyteams=dbHelpermyteams.getReadableDatabase();
}
public void cleardata()
{
	String[] dummy={};
	databasemyteams.delete(MySQLiteHelpermyteams.TABLE_NAME,"1",dummy);
}
public void clearteams(String branch,String sport)
{
	String[] dummy={branch,sport};
	databasemyteams.delete(MySQLiteHelpermyteams.TABLE_NAME,"BRANCH=? AND SPORT=?",dummy);
	Log.d("myteamsdeleted", "branch= "+branch+" Sport= "+sport);
}

public Boolean viewmyteams(String branch,String sport)
{
	read();
	Cursor cursor2 = null;
	
	cursor2 = databasemyteams.query(MySQLiteHelpermyteams.TABLE_NAME,null,null, null,null, null,null);
	String sport_db=null,branch_db=null;
	
	cursor2.moveToFirst();
	while(!cursor2.isAfterLast())
	{
		
		branch_db=cursor2.getString(cursor2.getColumnIndex("BRANCH"));
		sport_db=cursor2.getString(cursor2.getColumnIndex("SPORT"));
		Log.d("myteamsinviewmyteams", "branch= "+branch+" sport= "+sport);
		if(branch.equals(branch_db))
			if(sport.equals(sport_db))
				return true;
		cursor2.moveToNext();
		
	}
	cursor2.close();
	return false;


}
public void get_myteams()
{
	read();
	Cursor cursor2 = null;
	
	cursor2 = databasemyteams.query(MySQLiteHelpermyteams.TABLE_NAME,null,null, null,null, null,null);
	String sport_db=null,branch_db=null;
	
	cursor2.moveToFirst();
	while(!cursor2.isAfterLast())
	{
		
		branch_db=cursor2.getString(cursor2.getColumnIndex("BRANCH"));
		sport_db=cursor2.getString(cursor2.getColumnIndex("SPORT"));
		Log.d("myteamsinviewmyteams", "branch= "+branch_db+" sport= "+sport_db);
		EnthusiaActivity.displaymyteams(branch_db,sport_db);
		cursor2.moveToNext();
		
	}
	cursor2.close();
	


}

public void close() {
	dbHelpermyteams.close();
}
}