
package com.wx.doctor.activity;

import io.rong.imkit.RongIM;

import com.wx.doctor.activity.MainActivity;
import com.example.wxdoctor.R;
import com.wx.doctor.fragment.BabyListFragment;
import com.wx.doctor.fragment.ExpertListFragment;
import com.wx.doctor.fragment.MyInfoFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends BaseActivity{
	private RadioButton mRbuttonMyRoom,mRbuttonMyExpert,mRbuttonMyInfo;
	private LinearLayout mSearchBtn;
	private boolean isShowMainFragment = true;
	private static MainActivity mainActivity;
	private BabyListFragment babyListFragment;
	private ExpertListFragment expertListFragment;
	private MyInfoFragment myInfoFragment;
	private android.support.v4.app.FragmentManager fragmentManager;
	
	private Handler mHandler;//处理荣云服务
	
	/**
	 * @author qwp
	 * date 2015-4-4
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mainActivity = this;
		fragmentManager = mainActivity.getSupportFragmentManager();
		
		mHandler = new Handler();
		init();
	}

	/**
	 * 初始化各个UI组件
	 */
	private void init(){
		mSearchBtn       = (LinearLayout)findViewById(R.id.Checkin_search);
		mRbuttonMyRoom 	 = (RadioButton)findViewById(R.id.main_activity_myroom_rb);
		mRbuttonMyExpert = (RadioButton)findViewById(R.id.main_activity_myexpert_rb);
		mRbuttonMyInfo 	 = (RadioButton)findViewById(R.id.main_activity_myinfo_rb);
		
		mRbuttonMyRoom.setOnClickListener(onClickListener);
		mRbuttonMyExpert.setOnClickListener(onClickListener);
		mRbuttonMyInfo.setOnClickListener(onClickListener);
		mSearchBtn.setOnClickListener(onClickListener);
		mRbuttonMyRoom.setChecked(true);
		showMyRoomList();
	}

	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.main_activity_myroom_rb:
				showMyRoomList();
				break;
			case R.id.main_activity_myexpert_rb:
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						RongIM.getInstance().startConversationList(MainActivity.this);
//					}
//				});
				showMyExpertList();
				break;
			case R.id.main_activity_myinfo_rb:
				showMyInfoList();
				break;
			case R.id.Checkin_search:
				//Intent intent = new Intent(MainActivity.this,SearchActivity.class);
				//start_activity2(intent);
				break;
			}
		}

	};
	
	private void showMyRoomList(){
		if(!isShowMainFragment) return;
		isShowMainFragment = true;
		if(babyListFragment == null){
			babyListFragment = new BabyListFragment();
		}
		fragmentManager.beginTransaction().replace(R.id.main_activity_top, babyListFragment).commit();
	}
	
	private void showMyExpertList(){
		if(!isShowMainFragment) return;
		isShowMainFragment = true;
		if(expertListFragment == null){
			expertListFragment = new ExpertListFragment();
		}
		fragmentManager.beginTransaction().replace(R.id.main_activity_top, expertListFragment).commit();
	}
	
	private void showMyInfoList(){
		if(!isShowMainFragment) return;
		isShowMainFragment = true;
		if(myInfoFragment == null){
			myInfoFragment = new MyInfoFragment();
		}
		fragmentManager.beginTransaction().replace(R.id.main_activity_top, myInfoFragment).commit();
	}
	
}
