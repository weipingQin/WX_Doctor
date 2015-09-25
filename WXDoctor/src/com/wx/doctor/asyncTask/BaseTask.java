
package com.wx.doctor.asyncTask;

import android.os.AsyncTask;

import com.common.util.AppUtil;
import com.common.util.ErrorType;
import com.wx.doctor.MyApplication;

public abstract class BaseTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

	protected UiChange mUiChange;
	protected ErrorType errorType;

	public interface UiChange {
		void preUiChange();

		void lateUiChange(Object result);
	}

	protected BaseTask(UiChange uiChange) {
		mUiChange = uiChange;
	}

	@Override
	protected void onPreExecute() {
		if (mUiChange != null)
			mUiChange.preUiChange();
	}

	@Override
	protected Result doInBackground(Params... para) {
		return null;
	}

	@Override
	protected void onPostExecute(Result result) {
		if (errorType != null) {
			AppUtil.showShortMessage(MyApplication.mContext, errorType.getErrorBody());
		}
		if (mUiChange != null)
			mUiChange.lateUiChange(result);
	}
}
