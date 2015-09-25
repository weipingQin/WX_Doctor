package com.common.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;

@SuppressWarnings("rawtypes")
public class HttpHelper {
	private Context mContext;

	public HttpHelper(Context context) {
		mContext = context;
	}

	/**
	 * 发送post请求
	 */
	public String mPost(String httpUrl, List<NameValuePair> params) {
		String httpResult = MyHttpClient.post(mContext, httpUrl, params);
		return httpResult;
	}

	/**
	 * 发送带文件的post请求
	 */
	public String mPostWithFile(String httpUrl, List<NameValuePair> params) {
		String httpResult = MyHttpClient.postWithFile(mContext, httpUrl, params);
		return httpResult;
	}

	/**
	 * 发送get请求
	 */
	public String mGet(String httpUrl) {
		String httpResult = MyHttpClient.get(mContext, httpUrl);
		return httpResult;
	}
	
	/**
	 *发送put请求 
	 */
	public String mPut(String httpUrl,List<NameValuePair> params){
		String httpResult = MyHttpClient.put(mContext, httpUrl, params);
		return httpResult;
	}
	
	/**
	 *发送delete请求 
	 */
	public String mDelete(String httpUrl){
		String httpResult = MyHttpClient.delete(mContext, httpUrl);
		return httpResult;
	}
	

	/**
	 * URL参数转换成string
	 */
	public String getBasicParamsString(List<NameValuePair> params) {
		String paramUrl = "";
		Iterator iter = params.iterator();
		while (iter.hasNext()) {
			BasicNameValuePair item = (BasicNameValuePair) iter.next();
			try {
				paramUrl += String.format("%s=%s&", item.getName(), URLEncoder.encode(item.getValue(), HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				paramUrl += "";
			}
		}
		if (paramUrl.endsWith("&")) {
			paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
		}
		return paramUrl;
	}
}