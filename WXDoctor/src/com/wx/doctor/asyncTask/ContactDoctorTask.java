package com.wx.doctor.asyncTask;

import java.util.List;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.model.User;

public class ContactDoctorTask extends BaseTask<String, Integer, Boolean>{
	
	protected DocApi mApi;
	
	public ContactDoctorTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}
	
	@Override
	protected Boolean doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null){
			return null;
		}
		try {
			return mApi.requireDoctorContactBaby(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}	
}
