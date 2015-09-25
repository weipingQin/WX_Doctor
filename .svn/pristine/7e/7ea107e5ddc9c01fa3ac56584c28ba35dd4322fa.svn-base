package com.wx.doctor.activity;

import java.util.List;

import com.common.image.SmartImage;
import com.common.image.SmartImageView;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireDoctorInfoTask;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.util.JsonUtil;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DoctorInfoActivity extends BaseActivity {
	
	private SmartImageView smartIamge;
	private EditText phoneEdit,basicEdit,hospitalName,doctorTitleText;
	private TextView doctorNameText,doctorSexText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctorinfo);
		init();
		requireData();
	}
	
	private void init(){
		smartIamge = (SmartImageView)findViewById(R.id.doctorinfo_header_imageview);
		doctorNameText = (TextView)findViewById(R.id.doctorinfo_doctorname_textview);
		doctorSexText = (TextView)findViewById(R.id.doctorinfo_doctorsex_textview);
		doctorTitleText = (EditText)findViewById(R.id.doctorinfo_title_editText);
		phoneEdit = (EditText)findViewById(R.id.doctorinfo_phonenumber_edittext);
		basicEdit = (EditText)findViewById(R.id.doctorinfo_basicinfo_edittext);
		hospitalName = (EditText)findViewById(R.id.doctorinfo_hospitalname_eidttext);
		
	}
	
	private void requireData(){
		new RequireDoctorInfoTask(DoctorInfoUiChange, new DocApi(mContext)).execute(JsonUtil.getDoctorInfoJsonString());
	}
	
	private UiChange DoctorInfoUiChange = new UiChange(){
		public void preUiChange() {
			progress = AppUtil.showProgress(DoctorInfoActivity.this, getString(R.string.require_data));
		};
		
		@Override
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			Doctor doctor = (Doctor)result;
			if(doctor !=null ){
				//if(doctor)
				/*if(AppUtil.isNotEmpty(doctor.doctorHeadImage)){
					smartIamge.setImageUrl(doctor.doctorHeadImage);
				}*/
				doctorNameText.setText(doctor.doctorName);
				doctorSexText.setText(doctor.doctorSex);
				doctorTitleText.setText(doctor.doctorTitle);
				phoneEdit.setText(doctor.doctorPhone);
				basicEdit.setText(doctor.intro);
				hospitalName.setText(doctor.doctorHosptialName);
				///将数据存入到缓存数据库 
				//AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_success));
			}else{
				AppUtil.showShortMessage(DoctorInfoActivity.this, getString(R.string.require_data_fail));
			}
		}
	};
}
