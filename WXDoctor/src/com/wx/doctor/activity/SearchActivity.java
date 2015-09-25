package com.wx.doctor.activity;

import com.example.wxdoctor.R;

import android.os.Bundle;
import android.view.Window;

public class SearchActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.actionbar_home);
	}
}
