package com.wx.doctor.sqlite;

import java.io.ByteArrayOutputStream;

import com.wx.doctor.model.User;
import com.wx.doctor.sqlite.DatabaseHelper.Tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * @author qwp
 * @date 2015-2-13
 * 
 */
public class DBService {
	private Context mContext;
	private DatabaseHelper databaseHelper;

	private static DBService instance;

	private DBService(Context context){
		mContext = context;
		databaseHelper = new DatabaseHelper(context);
	}

	public static DBService getInstance(Context context){
		if(instance == null){
			instance = new DBService(context);
		}
		return instance;
	}

	public SQLiteDatabase getDataBase(){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		if(db == null){
			throw new SQLiteException("Can NOT find any writable database.");
		}
		return db;
	}

	public void addUser(User user){
		SQLiteDatabase db = getDataBase();
		db.insert(Tables.User, null, createUserItem(user));
		db.close();
	}

	public ContentValues createUserItem(User user){
		ContentValues values = new ContentValues();
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		values.put(DatabaseHelper.User.PHONENUMBER, user.userPhoneNumber);
		values.put(DatabaseHelper.User.USERNAME,user.userName);
		values.put(DatabaseHelper.User.PASSWORD,user.userPassword);
		values.put(DatabaseHelper.User.PIC,user.userHeaderImage);
		values.put(DatabaseHelper.User.PICPATH,user.userHeaderIamgePath);
		values.put(DatabaseHelper.User.CREATEDATE,user.userCreateDate);
		return values;
	}
	
	public void getUser(User user){
		
	}
	
	/*public void deleteDatabase(){
		databaseHelper
	}
	*/
}