package com.almesh.enthusia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsdatasourceRSS {
	private SQLiteDatabase databaserss;	
	private MySQLiteHelperRSS dbHelperrss;
	public CommentsdatasourceRSS(Context context) {
		dbHelperrss = new MySQLiteHelperRSS(context);
	}
public void addrss(String title,String content,String link,String sporturl) {  //Link is the article link and url is a identifier to identify sport
		
		ContentValues values = new ContentValues();
			values.put("TITLE",title);
			values.put("CONTENT",content);
			values.put("LINK",link);
			values.put("SPORTURL",sporturl);
			
			
			
			databaserss.insert(MySQLiteHelperRSS.TABLE_NAME, null,values);
			
	}

public void open() throws SQLException {
	databaserss = dbHelperrss.getWritableDatabase();
}
public void read() {
	databaserss=dbHelperrss.getReadableDatabase();
}
public void cleardata(String sporturl)
{
	String[] dummy={};
	databaserss.delete(MySQLiteHelperRSS.TABLE_NAME,"SPORTURL='"+sporturl+"'",dummy);
}

public void viewrss(String sporturl)
{
	read();
	Cursor cursor2 = null;
	
	cursor2 = databaserss.query(MySQLiteHelperRSS.TABLE_NAME,null,"SPORTURL='"+sporturl+"'", null,null, null, null);
	String title=null,content=null,url=null;
	
	cursor2.moveToFirst();
	while(!cursor2.isAfterLast())
	{
		title=cursor2.getString(cursor2.getColumnIndex("TITLE"));
		content=cursor2.getString(cursor2.getColumnIndex("CONTENT"));
		url=cursor2.getString(cursor2.getColumnIndex("LINK"));
		sporturl=cursor2.getString(cursor2.getColumnIndex("SPORTURL"));
	
		EnthusiaActivity.displayrss(title,content,url,sporturl);
		cursor2.moveToNext();
		
	}
	cursor2.close();
	


}
public void close() {
	dbHelperrss.close();
}
}