package com.wx.doctor.fragment;


import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import java.net.URLEncoder;

import org.json.JSONObject;

import com.wx.doctor.api.IResponse;
import com.wx.doctor.api.TokenGetter;
import com.wx.doctor.model.Doctor;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatWithExpertFragment extends BaseFragment implements IResponse{
	
	private final Handler mHandler = new Handler() {
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	//	融云IM代码 begin
	private TokenGetter tokenGetter=null;
	
	private Doctor doctor;
	private void initRongIM(String PhoneNumber) {
		//获取token
		tokenGetter= new TokenGetter(mContext, this);
//		int babyid=SPCfgs.getCurBabyID();
		tokenGetter.getFromServer(PhoneNumber);//将异步返回到httpResp
	}
	@Override
	public void httpResp(String type, JSONObject info) {
        Log.v(TAG, "httpResp "+info);
		this.startIMConnect(info);
	}
	
	private void startIMConnect(final JSONObject bbInfo) {
//      Log.v(TAG, "startConnect "+babyToken);
		/**
		 * 在整个应用程序全局，只需要调用一次 connect 方法，SDK 会负责自动重连。
		 * 您也可以跟自己的需要选择主动重连。
		 * 
		 * IMKit SDK调用第二步，建立与服务器的连接
		 */
		RongIM.connect(bbInfo.optString("token"), new RongIMClient.ConnectCallback() {

			@Override
			public void onSuccess(String userId) {
		        Log.v(TAG, "connect onSuccess "+userId);
				// Connect 成功
//		        rongIMConnected=true;
		        
		        String doctorId=doctor.getDoctorPhone()+"";
				String title = doctor.getDoctorName() + "在线咨询";
				try {
					// 改用下面的方式启动，可以向intent添加额外的参数。
					// RongIM.getInstance().startPrivateChat(this.mContext, doctorId, title);

					// 聊天会话 ConversationFragment
					// rong://{packagename:应用包名}/conversation/[private|discussion|group]?targetId={目标Id}&[title={开启会话名称}]
					Uri uri = Uri.parse("rong://com.example.wxdoctor/conversation/private?targetId="
									+ doctorId + "&title=" + URLEncoder.encode(title));
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					putIntentExtra(doctor, intent);
					intent.putExtra("baby_picpath", bbInfo.optString("picpath"));// 宝宝的头像
					Log.v(TAG, "startChatWithDoctorNEW "+doctorId);
					startActivityForResult(intent, 2);
					
				} catch (Exception ex) {
					// 没有网络时会有异常。
					ex.printStackTrace();
				}
			}

			@Override
			public void onError(RongIMClient.ErrorCode error) {
				// Connect 失败
				CommonUtils.showT("连接失败,请检查您的网络");
		        Log.v(TAG, "connect onError "+error);
		        Log.v(TAG, "connect onError error.getValue(): "+error.getValue());
			}

			@Override
			public void onTokenIncorrect() {
				// TODO Auto-generated method stub
				
			}
		});
		
		RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
          @Override
          public UserInfo getUserInfo(String s) {
              Log.e("TESTTEST",s);
              UserInfo userInfo = null;
              for (DoctorInfo doctor : mDoctorList) {
              	String rongDoctorUserId = doctor.getDoctorphonenum()+"_"+doctor.getDoctorid();
                  if (rongDoctorUserId.equals(s)) {
                  	if (!TextUtils.isEmpty(doctor.getPicpath())) {
                  		userInfo = new UserInfo(s, doctor.getDoctorname(), Uri.parse(doctor.getPicpath()));
						}
                  }
              }
              if (null==userInfo&&(bbInfo.optString("phonenum")+"_"+bbInfo.optString("babyid")).equals(s)) {
              	if (!TextUtils.isEmpty(bbInfo.optString("picpath"))) {
              		userInfo = new UserInfo(s,bbInfo.optString("name"),Uri.parse(bbInfo.optString("picpath")));
					}
				}
              return userInfo;
          }
      },true);
	}
	//	融云IM代码 end
	

}
