package com.wx.doctor.fragment;

import com.actionbarsherlock.app.SherlockFragment;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.activity.AddNotJoinBabyListActivity;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.model.Where;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class BaseFragment extends SherlockFragment {
	
	protected android.content.Context mContext;
	protected LayoutInflater mInflater;
	protected android.app.ActionBar mActionBar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mInflater = LayoutInflater.from(mContext);
	}
	
	public void initActionBar(String title,boolean isShow){
		mActionBar = getActivity().getActionBar();
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
				getActivity().finish();
				getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		};
		backView.setOnClickListener(clickListener);
		titleView.setOnClickListener(clickListener);
		if(AppUtil.isNotEmpty(title)){
			titleView.setText(title);
		}
		mActionBar.setCustomView(view);
	}
	
	
	public void initActionBarHome(){
		mActionBar = getActivity().getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.actionbar_home, null);
		
		ImageView backView = (ImageView) view.findViewById(R.id.actionbar_back);
		ImageView adddoctorView = (ImageView)view.findViewById(R.id.actionbar_add_doctor);
		TextView titleView = (TextView) view.findViewById(R.id.actionbar_title);
		OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v){
				getActivity().finish();
				getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
		};
		adddoctorView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent  = new Intent(getActivity(),AddNotJoinBabyListActivity.class);
				startActivity(intent);
			}
		});
		backView.setOnClickListener(clickListener);
		titleView.setOnClickListener(clickListener);
		mActionBar.setCustomView(view);
	}
}
