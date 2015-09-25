package com.wx.doctor.activity;


import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.adapter.BabyListAdapter;
import com.wx.doctor.adapter.ExpertListAdapter;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BabyConductDocotorTask;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.util.JsonUtil;

public class BabyConductDocotorActivity extends BaseActivity{
	
	public static final String TAG = BabyConductDocotorActivity.class.getSimpleName();
	
	private ExpertListAdapter mAdapter;
	private ListView showListView;
	private LinearLayout nullLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctorcontactbaby);
		Bundle bundle = getIntent().getExtras();
		Baby baby = (Baby)bundle.getSerializable("baby");
		init(baby.alias,MyApplication.appCache.getBabyPhoneNumber());
	}
	
	
	private void init(String name,String babyPhoneNumber){
		initActionBarForDetail(name);
		requireData(JsonUtil.getDoctorImJsonString(babyPhoneNumber));
	}
	
	private void requireData(String jsonString){
		new BabyConductDocotorTask(requireBabyUiChange, new DocApi(MyApplication.mContext)).execute(jsonString);
	}
	
	private UiChange requireBabyUiChange = new UiChange() {
		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(BabyConductDocotorActivity.this, getString(R.string.require_data));
		}

		@Override
		
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			List<Doctor> doctorList = (List<Doctor>)result;
			if(doctorList !=null  && doctorList.size() > 0 ){
				showDoctorList(doctorList);
				///将数据存入到缓存数据库 
				//AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_success));
			}else{
				AppUtil.showShortMessage(BabyConductDocotorActivity.this, getString(R.string.require_data_fail));
			}
		}
	};
	
	private void showDoctorList(List<Doctor> list){
		showListView.setVisibility(View.VISIBLE);
		nullLayout.setVisibility(View.GONE);
		if(mAdapter == null){
			mAdapter = new ExpertListAdapter(BabyConductDocotorActivity.this, list);
			showListView.setAdapter(mAdapter);
		}else{
			mAdapter.update(list);
		}
	}
}
