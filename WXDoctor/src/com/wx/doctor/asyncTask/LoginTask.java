package com.wx.doctor.asyncTask;

import org.json.JSONException;
import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.User;

public class LoginTask extends BaseTask<String, Integer, User> {
	
	private DocApi mApi;
	
	public LoginTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}

	@Override
	protected User doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			try {
				return mApi.login(para[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
