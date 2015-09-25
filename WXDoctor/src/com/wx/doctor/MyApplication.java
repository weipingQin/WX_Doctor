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
	
	//��¼��babyIds 
	public static String[] ids = null;
		
	private String Token = "+VDOQv4qSEBwhAV38KnvnLeU0FLY9lmnwqKKJQ79Nt7JDxITisPTn2PM0x4gMaraHsGFwyUPGcg=";
	/**
	 * ��¼��babyId 
	 */
	public static void setIds(int len,String babyId){
		ids = new String[len];
		for(int i = 0 ; i < ids.length ; i++){
			ids[i] = babyId;
		}
	}
	/**
	 * ��ȡids
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
         * IMKit SDK ��ʼ��
         */
       RongIM.init(this);
       
       // �����û���Ϣ�ṩ�ߡ�
       RongIM.setGetUserInfoProvider(new RongIM.GetUserInfoProvider() {
           // App ����ָ�����û���Ϣ�� IMKit ���������
           @Override
           public RongIMClient.UserInfo getUserInfo(String userId) {
               // ԭ���� App Ӧ�ý��û���Ϣ��ͷ�����ƶ��豸�Ͻ��л��棬ÿ�λ�ȡ�û���Ϣ��ʱ�򣬾Ͳ�����ͨ�������ȡ����߼����ٶȣ������û����顣���Ǻ������ṩ�û���Ϣ���湦�ܣ�������������
        	   
        	   if(userId.equals("1")){
        		   RongIMClient.UserInfo user = new RongIMClient.UserInfo("1", "��˹", "http://www.jf258.com/uploads/2013-07-24/063842629.jpg");
        	   }else if(userId.equals("2")){
        		   RongIMClient.UserInfo user2 = new RongIMClient.UserInfo("2", "�°���", "http://img1.qq.com/news/pics/13534/13534450.jpg");
        	   }else if(userId.equals("3")){
        		   RongIMClient.UserInfo user3 = new RongIMClient.UserInfo("3", "�վ�", "http://pic.33.la/20121212bzxh/2740.jpg");
        	   }
               return null; // �ɿ������ṩ�˷�����
           }
       }, false);
       
       // ���ú�����Ϣ�ṩ�ߡ�      һ���Ǵӷ�����ȡ���ߴӱ��ػ���ȡ
       RongIM.setGetFriendsProvider(new RongIM.GetFriendsProvider() {
           @Override
           public List<RongIMClient.UserInfo> getFriends() {
               // ���� App �ĺ����б�� IMKit ������������Ự�б�ҳ��ѡ�����ʱʹ�á�
               List<RongIMClient.UserInfo> list = new ArrayList<RongIMClient.UserInfo>();

               RongIMClient.UserInfo user1 = new RongIMClient.UserInfo("1", "��˹", "http://www.jf258.com/uploads/2013-07-24/063842629.jpg");
               RongIMClient.UserInfo user2 = new RongIMClient.UserInfo("2", "�°���", "http://img1.qq.com/news/pics/13534/13534450.jpg");
               RongIMClient.UserInfo user3 = new RongIMClient.UserInfo("3", "�վ�", "http://pic.33.la/20121212bzxh/2740.jpg");
               
               list.add(user1);
               list.add(user2);
               list.add(user3);

               return list;
           }
       });
        
        /**
         * IMKit SDK ��������
         */
         try {
			RongIM.connect(Token, new RongIMClient.ConnectCallback() {

			     @Override
			     public void onSuccess(String s) {
			         //Connect �ɹ�
			    	 Log.d("Connect", "loginSuccessfully");
			     }

			     @Override
			     public void onError(ErrorCode error) {
			         //Connect ʧ��
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
