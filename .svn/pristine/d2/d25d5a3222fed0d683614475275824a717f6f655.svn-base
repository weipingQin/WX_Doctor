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
 * æŒ‰å¾®è±¡è§„èŒƒå‘èµ·httpè¯·æ±‚ã€? * 
 * 2014-04-16 benæ”¹å†™ï¼?1. å°†åŸæ¥çš„3ä¸ªloadData()å¤§éƒ¨åˆ†é‡å¤ä»£ç åˆå¹¶ã? 2. å¢åŠ äº?ä¸ªloadHttpData()
 * 
 * @author lichangpeng
 * @version 2015-4-13 send data to server
 */
public class NetUtils {
	private static Context mmContext = MyApplication.getmContext();

	/**
	 * å‘é?postè¯·æ±‚
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
			netCallBack.onFailure("ÍøÂçÁ¬½Ó³¬Ê±");
		} catch (Exception e) {
			e.printStackTrace();
			netCallBack.onFailure("ÍøÂçÁ¬½ÓÒì³£");
		}
	}

	
	
}
