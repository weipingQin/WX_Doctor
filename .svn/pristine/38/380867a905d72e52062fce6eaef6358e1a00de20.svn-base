package com.wx.doctor.activity;

import com.example.wxdoctor.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 抽象出公共组件的activity 
 * 增加定时器倒计时,一分钟后才可以发验证码 
 * @author Administrator
 * @date 2014-12-1
 */
public abstract class BaseCompontentsActivity extends BaseActivity {

	protected EditText phoneEdit,verfixCodeEdit,newPasswordEdit,confirmPasswordEdit,userNameEdit;
	protected TextView modifyPhoneBtn,verfixBtn;

	protected TextView passwordText; 
	protected TextView registerBtn,submitBtn;

	private MyCount mCount;
	
	protected String phoneNumber,userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validateinfo);
		init();
	}

	protected void init(){
		phoneEdit = (EditText)findViewById(R.id.validateinfo_phone_editview);
		verfixCodeEdit = (EditText)findViewById(R.id.validateinfo_verify_edit);
		newPasswordEdit = (EditText)findViewById(R.id.validateinfo_newpassword_edit);
		confirmPasswordEdit = (EditText)findViewById(R.id.validateinfo_confirm_edit);
		userNameEdit = (EditText)findViewById(R.id.validateinfo_username_editview);
		
		verfixBtn = (TextView)findViewById(R.id.validateinfo_require_verfix);
		modifyPhoneBtn = (TextView)findViewById(R.id.validateinfo_modify_textview);
		registerBtn = (TextView)findViewById(R.id.register_btn_textview);
		submitBtn = (TextView) findViewById(R.id.submit_btn_textview);
		passwordText = (TextView)findViewById(R.id.validateinfo_password_textview);
		
		initAttr();
	}
	protected abstract void initAttr();

	protected void startTimerTask(){
		if(mCount != null){
			mCount.onFinish();
		}
		mCount = new MyCount(60000,1000);
		mCount.start();
	}

	/**
	 * 定义一个倒计时的内部类
	 */
	private class MyCount extends CountDownTimer{
		public MyCount(long millisInFuture, long countDownInterval) {     
			super(millisInFuture, countDownInterval);     
		}     

		@Override     
		public void onFinish() {    
			verfixBtn.setEnabled(true);
			verfixBtn.setText(BaseCompontentsActivity.this.getString(R.string.require_verify_code));   
		}     

		@Override     
		public void onTick(long millisUntilFinished) { 
			verfixBtn.setEnabled(false);
			verfixBtn.setText(millisUntilFinished/1000+"秒");
		}    
	}

	protected void onDestory(){
		super.onDestroy();
	}
}
