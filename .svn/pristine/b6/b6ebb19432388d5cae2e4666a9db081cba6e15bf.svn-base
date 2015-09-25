package com.wx.doctor.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	public static final int DB_VER = 1;

	public static final String DB_NAME = "doctor.db";
	
	interface Tables {
		String User						= "user";
		String UserInfo 				= "userInfo";
		String Baby						= "baby";
		String Doctor					= "doctor";
	}
	
	public interface User{
		public final String PHONENUMBER = "phonenum";
		public final String USERNAME 	= "alias";
		public final String PASSWORD 	= "password";
		public final String PIC			= "pic";
		public final String PICPATH     = "picpath";
		public final String CREATEDATE  = "createdate";
	}
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VER);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+Tables.User +"("
				  +User.PHONENUMBER +"unique not null,"
				  +User.USERNAME + "varchar(50) default null,"
				  +User.PASSWORD + "varchar(50) not null,"	
				  +User.PIC +" blob not null,"
				  +User.PICPATH +"text not null,"
				  +User.CREATEDATE + "text not null");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for(int i = oldVersion ; i <= newVersion;i++){
			if(i == DB_VER){
				db.execSQL("alter table "+Tables.User);
			}
		}
	}

}