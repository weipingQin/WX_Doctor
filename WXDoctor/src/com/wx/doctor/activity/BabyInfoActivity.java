package com.wx.doctor.activity;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BabyConductDocotorTask;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireBabyInfoTask;
import com.wx.doctor.asyncTask.RequireBabyListTask;
import com.wx.doctor.model.Baby;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class BabyInfoActivity extends BaseActivity {

	public static final String TAG = BabyInfoActivity.class.getSimpleName();

	public ProgressDialog progress;
	private String babyName;
	private EditText babyNameEdit,userNameEdit,jiezhongEdit,parentNameEdit;
	private TextView sexTextView,placeEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.babyinfo);
		init();
	}

	private void init(){
		initActionBar("±¶±¶œÍ«È", true);
		babyNameEdit = (EditText)findViewById(R.id.babyinfo_username_edit);
		sexTextView = (TextView)findViewById(R.id.babyinfo_sex_text);
		placeEdit = (TextView)findViewById(R.id.babyinfo_place_textview);
		userNameEdit = (EditText)findViewById(R.id.babyinfo_birthday_edit);
		jiezhongEdit = (EditText)findViewById(R.id.babyinfo_jiezhong_edit);
		parentNameEdit = (EditText)findViewById(R.id.babyinfo_parentname_edit);
		String babyPhoneNumber = MyApplication.appCache.getBabyPhoneNumber();
		String babyId = MyApplication.appCache.getBabyId();
		new RequireBabyInfoTask(requireBabyInfoUiChange,new DocApi(MyApplication.mContext)).execute(JsonUtil.getBabyInfoJsonString(babyPhoneNumber,babyId));
	}
	
	private UiChange requireBabyInfoUiChange = new UiChange(){

		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(BabyInfoActivity.this, getString(R.string.require_data));
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			Baby baby = (Baby)result;
			if(baby != null){
				babyNameEdit.setText(baby.alias);
				sexTextView.setText(baby.sex);
				placeEdit.setText(baby.cityname);
				userNameEdit.setText(baby.realname);
				jiezhongEdit.setText(baby.cityname);
				parentNameEdit.setText(baby.doctorName);
			}
		}
	};
}
