package com.wx.doctor.asyncTask;

import java.util.List;
import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.Doctor;

public class RequireDoctorListTask extends BaseTask<String, Integer, List<Doctor>> {
	
	private DocApi  mApi;

	public RequireDoctorListTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}
	
	@Override
	protected List<Doctor> doInBackground(String... para) {
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.requireDoctorList(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}
}
