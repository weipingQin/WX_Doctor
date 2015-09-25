package com.wx.doctor.api;

import org.apache.http.HttpResponse;

/**
 * @author lichangpeng
 * @version 2015-4-13
 * 
 */
public interface NetCallBack {
	void onSuccess(HttpResponse response);

	void onFailure(String msg);
}
