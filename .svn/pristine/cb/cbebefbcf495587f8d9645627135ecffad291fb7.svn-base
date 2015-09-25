package com.wx.doctor.asyncTask;

import java.util.List;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Type;

public class RequireUpDateTask extends BaseTask<String, Integer, List<Type>>{
	
	protected DocApi mApi;
	
	public RequireUpDateTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}
	
	@Override
	protected List<Type> doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.requireType(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
