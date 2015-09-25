package com.wx.doctor.activity;

import android.os.Bundle;

import com.example.wxdoctor.R;

public class GradeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grade);
		init();
	}
	
	private void init(){
		initActionBar("”√ªß∆¿∑÷", true);
	}
}
