package com.wx.doctor;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import java.util.ArrayList;
import java.util.List;

import com.common.util.AppUtil;
import com.wx.doctor.cache.AppCache;
import com.wx.doctor.model.User;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyApplication extends Application {
	public static final String TAG="MyApplication";
	
	public static Context mContext;
	
	public static Context getmContext() {
		return mContext;
	}

	public static AppCache appCache;
	
	public static User user;
	public static List<String> testData;
	public static List<String> testFindDoctorData;
	
	//记录下babyIds 
	public static String[] ids = null;
		
	private String Token = "+VDOQv4qSEBwhAV38KnvnLeU0FLY9lmnwqKKJQ79Nt7JDxITisPTn2PM0x4gMaraHsGFwyUPGcg=";
	/**
	 * 记录下babyId 
	 */
	public static void setIds(int len,String babyId){
		ids = new String[len];
		for(int i = 0 ; i < ids.length ; i++){
			ids[i] = babyId;
		}
	}
	/**
	 * 获取ids
	 */
	public static String[] getBabyIds(){
		return ids;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		appCache = new AppCache(this);
		Log.d(TAG,"1");
		//user = MyApplication.appCache.
		user = MyApplication.appCache.getUserInfo();
		/**
         * IMKit SDK 初始化
         */
       RongIM.init(this);
       
       // 设置用户信息提供者。
       RongIM.setGetUserInfoProvider(new RongIM.GetUserInfoProvider() {
           // App 返回指定的用户信息给 IMKit 界面组件。
           @Override
           public RongIMClient.UserInfo getUserInfo(String userId) {
               // 原则上 App 应该将用户信息和头像在移动设备上进行缓存，每次获取用户信息的时候，就不用再通过网络获取，提高加载速度，提升用户体验。我们后续将提供用户信息缓存功能，方便您开发。
        	   
        	   if(userId.equals("1")){
        		   RongIMClient.UserInfo user = new RongIMClient.UserInfo("1", "艾斯", "http://www.jf258.com/uploads/2013-07-24/063842629.jpg");
        	   }else if(userId.equals("2")){
        		   RongIMClient.UserInfo user2 = new RongIMClient.UserInfo("2", "奥巴马", "http://img1.qq.com/news/pics/13534/13534450.jpg");
        	   }else if(userId.equals("3")){
        		   RongIMClient.UserInfo user3 = new RongIMClient.UserInfo("3", "普京", "http://pic.33.la/20121212bzxh/2740.jpg");
        	   }
               return null; // 由开发者提供此方法。
           }
       }, false);
       
       // 设置好友信息提供者。      一般是从服务器取或者从本地缓存取
       RongIM.setGetFriendsProvider(new RongIM.GetFriendsProvider() {
           @Override
           public List<RongIMClient.UserInfo> getFriends() {
               // 返回 App 的好友列表给 IMKit 界面组件，供会话列表页中选择好友时使用。
               List<RongIMClient.UserInfo> list = new ArrayList<RongIMClient.UserInfo>();

               RongIMClient.UserInfo user1 = new RongIMClient.UserInfo("1", "艾斯", "http://www.jf258.com/uploads/2013-07-24/063842629.jpg");
               RongIMClient.UserInfo user2 = new RongIMClient.UserInfo("2", "奥巴马", "http://img1.qq.com/news/pics/13534/13534450.jpg");
               RongIMClient.UserInfo user3 = new RongIMClient.UserInfo("3", "普京", "http://pic.33.la/20121212bzxh/2740.jpg");
               
               list.add(user1);
               list.add(user2);
               list.add(user3);

               return list;
           }
       });
        
        /**
         * IMKit SDK 调用连接
         */
         try {
			RongIM.connect(Token, new RongIMClient.ConnectCallback() {

			     @Override
			     public void onSuccess(String s) {
			         //Connect 成功
			    	 Log.d("Connect", "loginSuccessfully");
			     }

			     @Override
			     public void onError(ErrorCode error) {
			         //Connect 失败
			    	 Log.d("Connect", "login failed");
			     }
			 });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getAppVersion() {
		String version = "";
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
			version = pi.versionName;
			if (AppUtil.isEmpty(version))
				version = "";
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

}
