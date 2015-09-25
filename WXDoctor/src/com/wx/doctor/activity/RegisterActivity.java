package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RegisterTask;
import com.wx.doctor.asyncTask.RequireVirfixTask;
import com.wx.doctor.fragment.BaseFragment;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.User;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;

public class RegisterActivity extends BaseCompontentsActivity {
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initAttr() {
		registerBtn.setVisibility(View.VISIBLE);
		submitBtn.setVisibility(View.GONE);
		modifyPhoneBtn.setVisibility(View.GONE);
		passwordText.setText(getString(R.string.password));
		//phoneEdit.setBackgroundResource(R.drawable.input_tel);
		registerBtn.setOnClickListener(onClickListener);
		verfixBtn.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			switch(arg0.getId()){
			case R.id.register_btn_textview:
				//String device_token = UmengRegistrar.getRegistrationId(mContext);
				phoneNumber = phoneEdit.getText().toString().trim();
				userName = userNameEdit.getText().toString().trim();
				String password = newPasswordEdit.getText().toString().trim();
				String comfirmPassword = confirmPasswordEdit.getText().toString().trim();
				String verfixNumber= verfixCodeEdit.getText().toString().trim();

				if(AppUtil.isEmpty(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_phone));
					return;
				}
				if(!AppUtil.isMobilePhoneNumber(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkphone));
					return;
				}
				if(AppUtil.isEmpty(verfixNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkcode));
					return;
				}
				if(AppUtil.isEmpty(password)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_password));
					return;
				}
				if(AppUtil.isEmpty(comfirmPassword)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_confirm_password));
					return;
				}

				if(!password.equals(comfirmPassword)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_repassword));
					return;
				}
				String jsonString = JsonUtil.getRegisterJsonString(userName,phoneNumber,password,getIMEI(),getIMSI());
				new RegisterTask(registerUiChange,new DocApi(mContext)).execute(jsonString);
				break;
			case R.id.validateinfo_require_verfix:
				phoneNumber = phoneEdit.getText().toString().trim();
				if(!AppUtil.isNotEmpty(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_phone));
					return;
				}
				if(!AppUtil.isMobilePhoneNumber(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkphone));
					return;
				}
				new RequireVirfixTask(VirfixuiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.getVerfixCodeJsonString(phoneNumber));
				startTimerTask();
				break;
			}
		}
	};
	
	/**
	 * 生成imei的方法 
	 */
	public  String getIMEI(){
		TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
		return manager.getDeviceId();
	}
	
	/**
	 * 生成imsi的方法
	 */
	public  String getIMSI(){
		TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
		return manager.getSubscriberId();
	}

	
	private UiChange VirfixuiChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(RegisterActivity.this, "正在发送验证码,请稍后...");
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			boolean success = (Boolean)result;
			if(success){
				AppUtil.showShortMessage(MyApplication.mContext, "发送验证码成功！");
			}else{
				AppUtil.showShortMessage(MyApplication.mContext, "发送验证码失败!");
			}
		}
	};


	private UiChange registerUiChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(RegisterActivity.this, "正在注册,请稍后...");
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			User user =(User)result;
			if(user!=null){
				MyApplication.appCache.setUserInfo(user);
				AppUtil.showShortMessage(RegisterActivity.this, "您已注册成功");
			}else{
				AppUtil.showShortMessage(RegisterActivity.this, "注册失败,请稍后重试!");
			}
		}

	};
}
