package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.ResetPasswordTask;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.util.JsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ResetPasswordActivity extends BaseCompontentsActivity {

	private String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initAttr() {
		modifyPhoneBtn.setVisibility(View.VISIBLE);
		registerBtn.setVisibility(View.GONE);
		submitBtn.setVisibility(View.VISIBLE);
		passwordText.setText(getString(R.string.new_password));
		phone = MyApplication.appCache.getUserPhoneNumber();
		if(AppUtil.isNotEmpty(phone)){
			String phoneStr = phone.substring(0,3)+"****"+phone.substring(7,11);
			phoneEdit.setText(phoneStr);
		}
		submitBtn.setOnClickListener(onClickListener);
		modifyPhoneBtn.setOnClickListener(onClickListener);
		verfixBtn.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener(){
		
		@Override
		public void onClick(View arg0) {
			switch(arg0.getId()){
			case R.id.validateinfo_modify_textview:
				phoneEdit.setText("");
				phoneEdit.setEnabled(true);
				break;
			case R.id.submit_btn_textview:
			{
				String verfixCode = verfixCodeEdit.getText().toString().trim();
				String newPassword = newPasswordEdit.getText().toString().trim();
				String confrimPassword = confirmPasswordEdit.getText().toString().trim();
				String phoneNumber = phoneEdit.getText().toString().trim();

				if(phoneNumber.indexOf("*") == -1){
					phone = phoneNumber;
				}
				if(AppUtil.isEmpty(phone)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_phone));
					return;
				}
				if(!AppUtil.isMobilePhoneNumber(phone)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkphone));
					return;
				}
				if(AppUtil.isEmpty(verfixCode)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkcode));
					return;
				}
				if(!newPassword.equals(confrimPassword)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_repassword));
					return;
				}
				String JsonString = JsonUtil.getResetPasswordJsonString(phoneNumber,newPassword,confrimPassword);
				new ResetPasswordTask(resetPasswordChange, new DocApi(mContext)).execute(JsonString);
			}
			break;

			case R.id.validateinfo_require_verfix:
			{
				String phoneNumber = phoneEdit.getText().toString().trim();
				if(phoneNumber.indexOf("*") == -1){
					phone = phoneNumber;
				}
				if(AppUtil.isEmpty(phone)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_phone));
					return;
				}
				if(!AppUtil.isMobilePhoneNumber(phone)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkphone));
					return;
				}
			//	new RequireVirfixTask(VerfixuiChange, new KeyApi2(mContext)).execute(phone,"2");
				startTimerTask();
			}
			break;
			}
		}
	};

	private UiChange resetPasswordChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(mContext);
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			boolean success = (Boolean) result;
			if(success){
				AppUtil.showShortMessage(mContext,getString(R.string.reset_pwd_success));
				Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
				start_activity(intent);
				finish();
			}else{
				AppUtil.showShortMessage(mContext,getString(R.string.reset_pwd_fail_retry));
			}
		}
	};
}
