package com.wx.doctor.activity;

import com.example.wxdoctor.R;
import com.wx.doctor.model.Doctor;

import android.os.Bundle;

public class DoctorConductExpertActivity extends BaseActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctorcontactexpert);
		Bundle bundle = getIntent().getExtras();
		Doctor doctor = (Doctor)bundle.getSerializable("doctor");
		init(doctor.doctorName);
	}
	
	/**
	 * 做一些相关组件的初始化 
	 */
	private void init(String doctorName){
		//跳转到医生详情页面
		initActionBarForDetail(doctorName);
	}
}
