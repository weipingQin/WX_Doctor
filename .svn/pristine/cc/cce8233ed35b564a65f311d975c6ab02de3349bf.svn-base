package com.wx.doctor.asyncTask;
import com.common.util.DocException;
import com.common.util.ErrorType;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.model.Doctor;
public class RequireDoctorInfoTask extends BaseTask<String, Integer, Doctor> {
	
	protected DocApi mApi;
	
	public RequireDoctorInfoTask(UiChange uiChange,DocApi api) {
		super(uiChange);
		mApi = api;
	}

	@Override
	protected Doctor doInBackground(String... para) {
		// TODO Auto-generated method stub
		super.doInBackground(para);
		if(errorType != null)
			return null;
		try {
			return mApi.requireDoctorInfo(para[0]);
		} catch (DocException e) {
			e.printStackTrace();
			errorType = new ErrorType(e.getStatusCode(),e.getMessage());
		}
		return null;
	}

}
