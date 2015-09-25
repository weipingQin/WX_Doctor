package com.wx.doctor.cache;

import com.common.util.Base64Util;
import com.wx.doctor.MyApplication;
import com.wx.doctor.model.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppCache {

	private SharedPreferences cache;

	private static final String FIRST_START_APP = "first_start_app_";
	private static final String USER_INFO 		= "user_info";//缓存用户信息 
	private static final String USER_PHONE		= "user_phone";
	private static final String USER_PASSWORD   = "user_password";
	private static final String IMEI_STR        = "imei";
	private static final String IMSI_STR        = "imsi";
	private static final String TOKEN 			= "token";
	private static final String BABY_PHONE 		= "babyPhonenumber";
	private static final String BABY_ID 		= "babyId";

	public AppCache(Context context){
		cache = context.getSharedPreferences("app_cache",Context.MODE_PRIVATE);
	}

	public void setFirstStartApp(boolean isFirst){
		Editor editor = cache.edit();
		editor.putBoolean(FIRST_START_APP, isFirst);
		editor.commit();
	}

	public boolean isFirstStartApp(){
		return cache.getBoolean(FIRST_START_APP + MyApplication.getAppVersion(),true);
	}

	public void setUserPhoneNumber(String phoneNumber){
		Editor editor = cache.edit();
		editor.putString(USER_PHONE, phoneNumber);
		editor.commit();
	}

	public String getUserPhoneNumber(){
		return cache.getString(USER_PHONE, "");
	}

	public void setUserInfo(User user){
		Editor editor = cache.edit();
		if(user == null){
			editor.putString(USER_INFO, null);
		}else{
			editor.putString(USER_INFO, Base64Util.objectToString(user));
		}
		editor.commit();
	}

	public User getUserInfo(){
		return (User) Base64Util.stringToObject(cache.getString(USER_INFO, ""));
	}

	public void removeUserInfo(){
		Editor editor = cache.edit();
		editor.remove(USER_INFO);
		editor.commit();
	}

	/***
	 * 对密码进行Base64编码
	 */
	public void setUserPassword(String password){
		Editor editor = cache.edit();
		editor.putString(USER_PASSWORD, Base64Util.objectToString(password));
		editor.commit();
	}

	public String getUserPassword(){
		return (String)Base64Util.stringToObject(cache.getString(USER_PASSWORD,""));
	}

	/*
	 * 将Imei和IMsi存入到缓存中去
	 */
	public void setIMEI(String imeiStr){
		Editor editor = cache.edit();
		editor.putString(IMEI_STR, Base64Util.objectToString(imeiStr));
		editor.commit();
	}

	public String getIMEI(){
		return (String)Base64Util.stringToObject(cache.getString(IMEI_STR,""));
	}

	public void setIMSI(String imsiStr){
		Editor editor = cache.edit();
		editor.putString(IMSI_STR, Base64Util.objectToString(imsiStr));
		editor.commit();
	}

	public String getIMSI(){
		return (String)Base64Util.stringToObject(cache.getString(IMSI_STR,""));
	}
	
	
	public void setToken(String token){
		Editor editor = cache.edit();
		editor.putString(TOKEN,token);
		editor.commit();
	}
	
	/**
	 * 获取医生接口返回过来的token值 用于IM通信聊天接口
	 */
	public String getToken(){
		return cache.getString(TOKEN, "");
	}
	
	/**
	 * 添加婴儿的电话号码 用于IM通信用
	 * @param babyPhoneNumber
	 */
	
	public void setBabyPhoneNumber(String babyPhoneNumber){
		Editor editor = cache.edit();
		editor.putString(BABY_PHONE,babyPhoneNumber);
		editor.commit();
	}
	public String getBabyPhoneNumber(){
		return cache.getString(BABY_PHONE, "");
	}
	
	/**
	 * 添加babyId
	 * @param babyId
	 */
	public void setBabyId(String babyId){
		Editor editor = cache.edit();
		editor.putString(BABY_ID,babyId);
		editor.commit();
	}
	
	/**
	 * 得到babyId
	 * @return
	 */
	public String getBabyId(){
		return cache.getString(BABY_ID, "");
	}
}
