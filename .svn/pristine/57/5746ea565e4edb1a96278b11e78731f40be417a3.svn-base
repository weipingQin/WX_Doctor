package com.wx.doctor.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.model.Where;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


public class BaseActivity extends SherlockFragmentActivity {

	protected Context mContext;
	protected LayoutInflater mInflater;
	protected ActionBar mActionBar;
	public ProgressDialog progress;
	protected static MainActivity mainActivity;


	//@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mInflater = LayoutInflater.from(mContext);
	}

	protected void initActionBar(String title,boolean isShow){
		mActionBar = getSupportActionBar();
		if(!isShow){
			mActionBar.hide();
			return;
		}
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.action_bar, null);
		ImageView backView = (ImageView) view.findViewById(R.id.actionbar_back);
		TextView titleView = (TextView) view.findViewById(R.id.actionbar_title);

		OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v){
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		};
		backView.setOnClickListener(clickListener);
		titleView.setOnClickListener(clickListener);
		if(AppUtil.isNotEmpty(title)){
			titleView.setText(title);
		}
		mActionBar.setCustomView(view);
	}

	protected void initActionBarForMain(String title){
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.actionbar_home, null);
		ImageView backView = (ImageView) view.findViewById(R.id.actionbar_back);
		TextView titleView = (TextView) view.findViewById(R.id.actionbar_title);
		backView.setVisibility(View.GONE);
		if(AppUtil.isNotEmpty(title)){
			titleView.setText(title);
		}
		mActionBar.setCustomView(view);
	}

	public void initActionBarHome(){
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.actionbar_home, null);

		ImageView backView = (ImageView) view.findViewById(R.id.actionbar_back);
		ImageView adddoctorView = (ImageView)view.findViewById(R.id.actionbar_add_doctor);
		TextView titleView = (TextView) view.findViewById(R.id.actionbar_title);
		titleView.setVisibility(View.GONE);
		TextView infoView = (TextView)view.findViewById(R.id.actionbar_doctor_info);
		infoView.setVisibility(View.GONE);

		OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v){
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		};
		adddoctorView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,AddNotJoinBabyListActivity.class);
				start_activity(intent);
			}
		});
		backView.setOnClickListener(clickListener);
		titleView.setOnClickListener(clickListener);
		adddoctorView.setOnClickListener(clickListener);
		mActionBar.setCustomView(view);
	}

	protected void initActionBarForDetail(String title){
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.actionbar_detail_item, null);

		ImageView backView = (ImageView) view.findViewById(R.id.actionbar_back);
		TextView titleView = (TextView) view.findViewById(R.id.actionbar_title);
		titleView.setText(title);
		TextView infoView = (TextView)view.findViewById(R.id.actionbar_doctor_info);
		infoView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mContext instanceof BabyConductDocotorActivity){
					Intent intent = new Intent(mContext,BabyInfoActivity.class);
					start_activity(intent);
				}
				if(mContext instanceof DoctorConductExpertActivity){
					Intent intent2 = new Intent(mContext,DoctorInfoActivity.class);
					start_activity(intent2);
				}
			}
		});
		backView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		});
		mActionBar.setCustomView(view);
	}

	public void start_activity(Intent intent){
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void start_activity2(Intent intent){
		startActivity(intent);
		overridePendingTransition(R.anim.push_top_in, R.anim.push_top_out);
	}

	public void start_activityForResult(Intent intent,int requestCode){
		startActivityForResult(intent,requestCode);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void onResume(){
		super.onResume();
	}

	public void onPause(){
		super.onPause();
	}

	/**
	 * 
	 * 再按一次退出当前的应用
	 */
	private long exitTime = 0;
	@Override	
	public boolean dispatchKeyEvent(android.view.KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if(this instanceof MainActivity){
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					AppUtil.showShortMessage(mContext, getString(R.string.click_again_to_exit));
					exitTime = System.currentTimeMillis();
				}
			}else{
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
			finish();
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}