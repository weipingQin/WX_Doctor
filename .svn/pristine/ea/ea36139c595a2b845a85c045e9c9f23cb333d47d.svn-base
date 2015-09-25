package com.wx.doctor.asyncTask;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.User;

public class RegisterTask extends BaseTask<String, Integer, User> {
	
	private DocApi mApi;

	public RegisterTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}
	
	@Override
	protected User doInBackground(String... para) {
		// TODO Auto-generated method stub
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.register(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
