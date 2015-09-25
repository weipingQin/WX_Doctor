package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class LoadingActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);
		//initActionBar("", false);
		new Thread(runnable).start();
	}
	
	private Runnable  runnable = new Runnable(){

		@Override
		public void run() {
			AppUtil.sleep(1500);
			
			if(MyApplication.appCache.getUserInfo() != null){
				Intent  intent = new Intent(mContext,MainActivity.class);
				start_activity(intent);
			}else{
				Intent intent2 = new Intent(mContext,LoginActivity.class);
				start_activity(intent2);
			}
		}
	};
	
	private void init(){
		
	}
}
