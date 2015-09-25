package com.wx.doctor.activity;

import java.util.List;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireUpDateTask;
import com.wx.doctor.model.Type;
import com.wx.doctor.util.JsonUtil;

import android.os.Bundle;
import android.widget.TextView;

public class UpdateActivity extends BaseActivity {
	
	private TextView currentVersion,btnUpdate;
	private TextView linkUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		init();
		requireData();
	}
	
	private void init(){
		initActionBar("检查更新", true);
		currentVersion = (TextView)findViewById(R.id.tv_cur_version);
		btnUpdate = (TextView)findViewById(R.id.tv_btn_app_update);
		linkUrl = (TextView)findViewById(R.id.tv_btn_app_download);
	}
	
	private void requireData(){
		/*String phoneNumber = MyApplication.appCache.getUserPhoneNumber();
		String version = "";*/
		/**
		 * 测试数据 
		 */
		String phoneNumber = "13588719517";
		String version = "12";
		new RequireUpDateTask(requireUpdateUiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.getSysVersionString(phoneNumber, version));
	}
	
	private UiChange requireUpdateUiChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(UpdateActivity.this);
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress != null){
				progress.dismiss();
			}
			List<Type> typeList = (List<Type>)result;
			if(typeList != null){
				for(int i = 0 ; i <typeList.size() ; i++){
					currentVersion.setText(typeList.get(i).version);
					linkUrl.setText(typeList.get(i).upaddress);
				}
			}else{
				AppUtil.showShortMessage(UpdateActivity.this, "获取最新消息失败!");
			}
		}
	};
}
