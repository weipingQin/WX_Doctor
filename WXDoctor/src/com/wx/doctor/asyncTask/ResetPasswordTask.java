package com.wx.doctor.asyncTask;

import org.json.JSONException;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.User;

public class ResetPasswordTask extends BaseTask<String, Integer, Boolean> {

	private DocApi mApi;

	public ResetPasswordTask(UiChange uiChange,DocApi api){
		super(uiChange);
		mApi = api;
	}

	@Override
	protected Boolean doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return false;
		try {
			return mApi.resetPassword(para[0]);
		} catch (DocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return false;
	}
}

