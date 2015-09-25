package com.wx.doctor.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import android.R;
import android.content.Context;

import com.google.gson.Gson;
import com.wx.doctor.MyApplication;

/**
 * 按微象规范发起http请求�? * 
 * 2014-04-16 ben改写�?1. 将原来的3个loadData()大部分重复代码合并�? 2. 增加�?个loadHttpData()
 * 
 * @author lichangpeng
 * @version 2015-4-13 send data to server
 */
public class NetUtils {
	private static Context mmContext = MyApplication.getmContext();

	/**
	 * 发�?post请求
	 * 
	 * @param url
	 * @param jsonParams
	 * @param netCallBack
	 */
	public static void loadHttpData(String url, String jsonParams, NetCallBack netCallBack) {
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
			requestParams.add(new BasicNameValuePair("lk", jsonParams));
			//Logger.i("params:" + jsonParams);
			httpPost.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
			HttpClient mHttpClient = new DefaultHttpClient();
			mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
			HttpResponse httpResponse = mHttpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				netCallBack.onSuccess(httpResponse);
				return;
			}
			netCallBack.onFailure("�������ӳ�ʱ");
		} catch (Exception e) {
			e.printStackTrace();
			netCallBack.onFailure("���������쳣");
		}
	}

	
	
}
