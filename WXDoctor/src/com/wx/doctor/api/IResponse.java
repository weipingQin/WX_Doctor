package com.wx.doctor.api;

import org.json.JSONObject;

public interface IResponse {
	
	/**
	 * http���󷵻ء�
	 * @param type ����ʱ������������ͻ������ơ�
	 * @param info ���������ص����ݣ�����ΪJSONObject
	 */
	public void httpResp(String type, JSONObject info);
}


