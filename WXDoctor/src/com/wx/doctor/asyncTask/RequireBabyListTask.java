package com.wx.doctor.asyncTask;

import java.util.List;

import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.Baby;

public class RequireBabyListTask extends BaseTask<String, Integer, List<Baby>> {
	
	private DocApi  mApi;

	public RequireBabyListTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}
	
	@Override
	protected List<Baby> doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.requireBabyList(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
