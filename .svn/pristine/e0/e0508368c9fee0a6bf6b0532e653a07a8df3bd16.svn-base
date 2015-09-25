package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireModifyPasswordTask;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyPasswordActivity extends BaseActivity {
	
	private EditText oldpassword,newpassword,confirmPassword;
	private TextView btnSubmit;
	
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_modify_password);
		init();
	}
	
	private void init(){
		initActionBar("密码修改", true);
		oldpassword = (EditText)findViewById(R.id.myinfo_modify_password_oldpassword);
		newpassword = (EditText)findViewById(R.id.myinfo_modify_password_newpassword);
		confirmPassword = (EditText)findViewById(R.id.myinfo_modify_password_confirmpassword);
		btnSubmit = (TextView)findViewById(R.id.myinfo_modify_password_submit);
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phoneNumber = MyApplication.appCache.getUserPhoneNumber();
				String oldpassword = MyApplication.appCache.getUserPassword();
				String newPassword = newpassword.getText().toString().trim();
				
				if(AppUtil.isEmpty(phoneNumber)){
					AppUtil.showShortMessage(ModifyPasswordActivity.this, "您输入的密码为空！");
					return;
				}
				
				if(AppUtil.isEmpty(oldpassword)){
					AppUtil.showShortMessage(ModifyPasswordActivity.this, "您输入的密码为空！");
					return;
				}
				
				new RequireModifyPasswordTask(modifyPasswordUiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.
						getModifyPasswordJsonString(phoneNumber, oldpassword, newPassword));
			}
		});
	}
	
	
	private UiChange modifyPasswordUiChange  = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(ModifyPasswordActivity.this);
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			if(result != null){
			boolean success = (Boolean)result;
				if(success){
					AppUtil.showShortMessage(MyApplication.mContext, "修改密码成功，请牢记您的新密码！");
				}else{
					AppUtil.showShortMessage(MyApplication.mContext, "修改密码失败!");
				}
			}
		}
	};
}
