package com.wx.doctor.fragment;

import com.example.wxdoctor.R;
import com.wx.doctor.activity.AboutActivity;
import com.wx.doctor.activity.FeedbackActivity;
import com.wx.doctor.activity.GradeActivity;
import com.wx.doctor.activity.LoginActivity;
import com.wx.doctor.activity.ModifyPasswordActivity;
import com.wx.doctor.activity.MyInfoActivity;
import com.wx.doctor.activity.UpdateActivity;
import com.wx.doctor.adapter.MyInfoAdapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyInfoFragment extends BaseFragment{

	private LinearLayout nullLayout;
	private ListView showListView;
	private ProgressDialog progress;
	private MyInfoAdapter mAdapter;
	private RelativeLayout btnMyInfo,btnModifyPassword,btncheckUpdate,btnGrade,btnFeedBack,btnAbout;
	private TextView btnExit;
	private static MyInfoFragment myInfoFragment; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myInfoFragment = this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.myinfo, null);
		nullLayout = (LinearLayout)view.findViewById(R.id.main_no_list);
		showListView = (ListView)view.findViewById(R.id.main_list_listview);
		init(view);
		return view;
	}

	private void init(View view){
		initActionBar("我的信息",true);
		btnMyInfo = (RelativeLayout)view.findViewById(R.id.myinfo_myinfo);
		btnModifyPassword = (RelativeLayout)view.findViewById(R.id.myinfo_modify_password);
		btncheckUpdate = (RelativeLayout)view.findViewById(R.id.myinfo_check_update);
		btnGrade = (RelativeLayout)view.findViewById(R.id.myinfo_grade);
		btnFeedBack = (RelativeLayout)view.findViewById(R.id.myinfo_user_feedback);
		btnAbout = (RelativeLayout)view.findViewById(R.id.myinfo_about);
		btnExit = (TextView)view.findViewById(R.id.myinfo_exit);
		
		btnMyInfo.setOnClickListener(onClickListener);
		btnModifyPassword.setOnClickListener(onClickListener);
		btncheckUpdate.setOnClickListener(onClickListener);
		btnGrade.setOnClickListener(onClickListener);
		btnFeedBack.setOnClickListener(onClickListener);
		btnAbout.setOnClickListener(onClickListener);
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		Intent intent = null;
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.myinfo_myinfo:
				intent = new Intent(getActivity(),MyInfoActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_modify_password:
				intent = new Intent(getActivity(),ModifyPasswordActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_check_update:
				intent = new Intent(getActivity(),UpdateActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_grade:
				intent = new Intent(getActivity(),GradeActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_user_feedback:
				intent = new Intent(getActivity(),FeedbackActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_about:
				intent = new Intent(getActivity(),AboutActivity.class);
				startActivity(intent);
				break;
			case R.id.myinfo_exit:
				/*intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);*/
				getActivity().finish();
				break;
			default:
				break;
			}
		}
	};
	
}
