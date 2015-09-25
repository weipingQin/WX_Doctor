package com.wx.doctor.activity;

import com.common.image.SmartImageView;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireDoctorInfoTask;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MyInfoActivity extends BaseActivity {
	
	private ProgressDialog progress;
	private TextView userName,sex;
	private EditText zhicheng,yiyuan,phonenumber,jianjie;
	private SmartImageView headImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_myinfo);
		init();
		requireData();
	}
	
	private void init(){
		initActionBar("我的详细信息", true);
		userName = (TextView)findViewById(R.id.myinfo_doctorname_textview);
		sex = (TextView)findViewById(R.id.myinfo_doctorsex_textview);
		zhicheng = (EditText)findViewById(R.id.myinfo_myinfo_zhicheng);
		yiyuan = (EditText)findViewById(R.id.myinfo_myinfo_hospotial);
		phonenumber = (EditText)findViewById(R.id.myinfo_phonenumber);
		jianjie = (EditText)findViewById(R.id.myinfo_jianjie);
		headImage = (SmartImageView)findViewById(R.id.myinfo_header_imageview);
	}
	
	private void requireData(){
		String phoneNumber = MyApplication.appCache.getUserPhoneNumber();
		String  babyId = MyApplication.appCache.getBabyId();
		new RequireDoctorInfoTask(uiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.getDoctorInfoJsonString());
	}
	
	private UiChange uiChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = new ProgressDialog(MyInfoActivity.this);
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			if(result != null){
				Doctor doctor = (Doctor)result;
				userName.setText(doctor.doctorName);
				sex.setText(doctor.doctorSex);
				zhicheng.setText(doctor.doctorTitle);
				yiyuan.setText(doctor.doctorHosptialName);
				phonenumber.setText(doctor.doctorPhone);
				jianjie.setText(doctor.intro);
				headImage.setImageUrl(doctor.doctorPicpath);
			}else{
				AppUtil.showShortMessage(MyInfoActivity.this, "获取医生详细信息失败!");
			}
		}
		
	};
	
}
