package com.wx.doctor.asyncTask;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;

public class RequireModifyPasswordTask extends BaseTask<String, Integer, Boolean>{

	protected DocApi  mApi;
	public RequireModifyPasswordTask(UiChange requireBabyUiChange,DocApi api) {
		super(requireBabyUiChange);
		mApi = api;
	}
	
	@Override
	protected Boolean doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.modifyPassword(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
