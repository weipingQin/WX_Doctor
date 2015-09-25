package com.wx.doctor.asyncTask;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;

public class RequireVirfixTask extends BaseTask<String, Integer, Boolean> {

	private DocApi mApi;

	public RequireVirfixTask(UiChange uiChange,DocApi api){
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
			return mApi.requireVerfixCode(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}	
}
