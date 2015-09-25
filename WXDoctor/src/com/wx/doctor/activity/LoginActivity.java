package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.common.util.BaseApi;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.LoginTask;
import com.wx.doctor.asyncTask.RegisterTask;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.User;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

	private EditText phoneNumberEdit,passwordEdit,userNameEdit;
	private TextView loginBtn,registerBtn,resetPasswordBtn;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		init();
	}

	private void init(){
		phoneNumberEdit = (EditText)findViewById(R.id.login_phone_edit);
		passwordEdit	= (EditText)findViewById(R.id.login_password_edit);
		userNameEdit     = (EditText)findViewById(R.id.login_name_edit);

		registerBtn 	 = (TextView)findViewById(R.id.login_register_textview);
		resetPasswordBtn = (TextView)findViewById(R.id.login_resetpassword_textview);
		loginBtn = (TextView)findViewById(R.id.login_btn_textview);

		registerBtn.setOnClickListener(onClickListener);
		resetPasswordBtn.setOnClickListener(onClickListener);
		loginBtn.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.login_btn_textview:
				String phoneNumber = phoneNumberEdit.getText().toString().trim();
				String password = passwordEdit.getText().toString().trim();
				String userName = userNameEdit.getText().toString().trim();
				if(AppUtil.isEmpty(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_phone));
					return;
				}
				
				if(AppUtil.isEmpty(password)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_password));
					return;
				}
				
				if(AppUtil.isEmpty(userName)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_username));
					return;
				}
				
				if(!AppUtil.isMobilePhoneNumber(phoneNumber)){
					AppUtil.showShortMessage(mContext, getString(R.string.input_message_checkphone));
					return;
				}
				new LoginTask(loginUiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.getLoginJsonString(phoneNumber));
				break;
			case R.id.login_resetpassword_textview:
				Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
				start_activity(intent);
				break;
			case R.id.login_register_textview:
				Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
				start_activity(intent2);
				break;
			}
		}
	};

	private UiChange loginUiChange = new UiChange() {
		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(LoginActivity.this, getString(R.string.login_waiting));
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			User user = (User)result;
			//boolean success = (Boolean)result;
			if(user!=null){
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				start_activity(intent);
				AppUtil.showShortMessage(getApplicationContext(), getString(R.string.login_success));
			}else{
				AppUtil.showShortMessage(getApplicationContext(), getString(R.string.login_fail_retry));
			}
		}
	};
}
