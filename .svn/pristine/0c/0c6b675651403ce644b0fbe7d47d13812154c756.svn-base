package com.wx.doctor.activity;

import com.google.gson.Gson;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.WXModel;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TestJsonActivity extends Activity{

	public static final String TAG = "TestJsonActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WXModel model = new WXModel();
		Context context = new Context();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_DOC_BABY_LIST;
		context.imei="1111";
		context.imsi="122121";
		context.phonenum="15356897456";
		context.pwd = "123456789";
		model.setContext(context);
		model.setReqType(reqType);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		Log.d(TAG,jsonString.toString());
	}
}
