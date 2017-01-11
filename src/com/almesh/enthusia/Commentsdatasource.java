package com.almesh.enthusia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class Commentsdatasource {
	static int todaycount=0;
	private MySQLiteHelper dbHelper;
	private SQLiteDatabase database;	
	java.sql.Date dt1 = new java.sql.Date(System.currentTimeMillis());
	String  date_today = dt1.toString();
	public Commentsdatasource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		Log.d("jhol", "inopen");
		database = dbHelper.getWritableDatabase();
	}
	public void cleardata()
	{
		String[] dummy={};
    	database.delete(MySQLiteHelper.TABLE_NAME,"1",dummy);
	}
	public void add(String p1,String p2,String sport,String date,String time,String winner) {
		
		
		ContentValues values = new ContentValues();
			values.put("P1",p1);
			values.put("P2",p2);
			values.put("SPORT",sport);
			values.put("DATE", date);
			values.put("TIME", time);
			values.put("WINNER",winner);
			
			database.insert(MySQLiteHelper.TABLE_NAME, null,values);
			Log.d("check","Adding to phone p1="+p1+" winner="+winner);
	}
	public void view_previous(String sport)
    {
	 	
		Cursor cursor1 = null;
		database = dbHelper.getReadableDatabase();
		cursor1 = database.query(MySQLiteHelper.TABLE_NAME,null, "DATE<='"+date_today+"' AND SPORT='"+sport+"'", null,null, null, "DATE,TIME");
		String p1=null,p2=null,date=null,time=null,winner=null;
		todaycount=cursor1.getCount();
		Log.d("jhol", ""+todaycount);
		cursor1.moveToFirst();
		Log.d("jhol", "in view 1");
		while(!cursor1.isAfterLast())
		{
			p1=cursor1.getString(cursor1.getColumnIndex("P1"));
			p2=cursor1.getString(cursor1.getColumnIndex("P2"));
			date=cursor1.getString(cursor1.getColumnIndex("DATE"));
			time=cursor1.getString(cursor1.getColumnIndex("TIME"));
			winner=cursor1.getString(cursor1.getColumnIndex("WINNER"));
			Log.d("jhol", "in view 2");
			EnthusiaActivity.display(sport,p1,p2,date,time,winner);
			cursor1.moveToNext();
			Log.d("jhol", "in view 3");
		}
		cursor1.close();
		


}
	public void view_all(String sport,String branch)
{
 	Log.d("jhol", "inviewall");
 	Cursor cursor1 = null;
	database = dbHelper.getReadableDatabase();
	Log.d("checking db for", ""+branch+""+sport);
	cursor1 = database.query(MySQLiteHelper.TABLE_NAME,null, "(P1 LIKE '"+branch+"%'OR P2 LIKE '"+branch+"%')AND SPORT='"+sport+"'", null,null, null,"DATE,TIME");
	String p1=null,p2=null,date=null,time=null,winner=null;
	todaycount=cursor1.getCount();
	Log.d("jhol", ""+todaycount);
	cursor1.moveToFirst();
	Log.d("jhol", "in view 1");
	while(!cursor1.isAfterLast())
	{
		p1=cursor1.getString(cursor1.getColumnIndex("P1"));
		p2=cursor1.getString(cursor1.getColumnIndex("P2"));
		date=cursor1.getString(cursor1.getColumnIndex("DATE"));
		time=cursor1.getString(cursor1.getColumnIndex("TIME"));
		winner=cursor1.getString(cursor1.getColumnIndex("WINNER"));
		Log.d("jhol", "in view 2");
		EnthusiaActivity.display(sport,p1,p2,date,time,winner);
		cursor1.moveToNext();
		Log.d("jhol", "in view 3");
	}
	cursor1.close();
	


}
	
	 public void view(String sport)
	    {
		 	
			Cursor cursor1 = null;
			database = dbHelper.getReadableDatabase();
			cursor1 = database.query(MySQLiteHelper.TABLE_NAME,null, "DATE='"+date_today+"' AND SPORT='"+sport+"'", null,null, null, "TIME");
			String p1=null,p2=null,date=null,time=null,winner=null;
			todaycount=cursor1.getCount();
			Log.d("jhol", ""+todaycount);
			cursor1.moveToFirst();
			Log.d("jhol", "in view 1");
			while(!cursor1.isAfterLast())
			{
				p1=cursor1.getString(cursor1.getColumnIndex("P1"));
				p2=cursor1.getString(cursor1.getColumnIndex("P2"));
				date=cursor1.getString(cursor1.getColumnIndex("DATE"));
				time=cursor1.getString(cursor1.getColumnIndex("TIME"));
				winner=cursor1.getString(cursor1.getColumnIndex("WINNER"));
				Log.d("jhol", "in view 2");
				EnthusiaActivity.display(sport,p1,p2,date,time,winner);
				cursor1.moveToNext();
				Log.d("jhol", "in view 3");
			}
			cursor1.close();
			
	
	
}
	 
	 public void view_upcoming(String sport)
	    {
		 	
			Cursor cursor1 = null;
			database = dbHelper.getReadableDatabase();
			cursor1 = database.query(MySQLiteHelper.TABLE_NAME,null,"DATE>'"+date_today+"' AND SPORT='"+sport+"'", null,null,null, "DATE,TIME");
			String p1=null,p2=null,date=null,time=null,winner;
			EnthusiaActivity.upcoming_flag=1;
			cursor1.moveToFirst();
			while(!cursor1.isAfterLast())
			{
				p1=cursor1.getString(cursor1.getColumnIndex("P1"));
				p2=cursor1.getString(cursor1.getColumnIndex("P2"));
				date=cursor1.getString(cursor1.getColumnIndex("DATE"));
				time=cursor1.getString(cursor1.getColumnIndex("TIME"));
				winner=cursor1.getString(cursor1.getColumnIndex("WINNER"));
				EnthusiaActivity.display(sport,p1,p2,date,time,winner);
				cursor1.moveToNext();
			}
			cursor1.close();
			
	
	
	    }
	 public void close() {
			dbHelper.close();
		}
	 
}
