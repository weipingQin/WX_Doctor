package com.wx.doctor.api;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.wx.doctor.MyApplication;
import com.wx.doctor.util.JsonUtil;



import android.content.Context;
import android.text.TextUtils;


/**
 * 用于获取医生即时通讯Token
 * @author shen
 *
 */
public class TokenGetter {
	
private static String TAG = "TokenGetter";
	
	private Context mContext;
	private IResponse dataProc;
	
	public TokenGetter(Context context, IResponse dataProc){
		this.mContext=context;
		this.dataProc=dataProc;
	}
	
	public void getFromServer(String phonenum) {
		if (!CommonUtils.isNetworkAvailable(this.mContext)) {
			CommonUtils.showT("请检查你的网络");
			return;
		}
		
		DocApi doc = new DocApi(mContext);
		JSONObject jsonObj = doc.buildGetDoctorTokenReq(phonenum);
		String url = "http://121.43.230.238:8082/IMApi/GetTokenApi.aspx";
		TaskEngine.getInstance().submit(new AsyncHttpTask(url, jsonObj.toString()));

		
	}
	
	
	
	private class AsyncHttpTask extends UITask {
		private String result;
		private final String jsonParams;
		private final String mUrl;

		public AsyncHttpTask(String url, String jsonParams) {
			this.mUrl = url;
			this.jsonParams = jsonParams;
		}

		@Override
		public void runInBackground() {
			NetUtils.loadHttpData(mUrl, jsonParams, new NetCallBack() {

				@Override
				public void onSuccess(HttpResponse response) {
					try {
						result = EntityUtils.toString(response.getEntity());
						//Logger.i(TAG, "Result:" + result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(String msg) {
					CommonUtils.showT(msg);
				}
			});
		}

		@Override
		public void runInUIThread() {
			if (TextUtils.isEmpty(result)) {
				return;
			}
			procJsonString(result);
		}

	}
	
	

	private void procJsonString(String jsonString) {
		JSONObject jobj = JsonUtil.parseJSONObject(jsonString);
		if (jobj == null) {
			//Logger.i(TAG, "json格式错误");
			CommonUtils.showT("服务器返回数据格式错误1");
			return;
		}
		JSONArray arr = jobj.optJSONArray("Data");
		if (null == arr) {
			CommonUtils.showT("服务器返回数据格式错误2");
			return;
		}
		ArrayList<JSONObject> serverList = JsonUtil.toList(arr);
		if (null != serverList && serverList.size() > 0) {
			dataProc.httpResp("", serverList.get(0));
		}else{
			CommonUtils.showT("没找到结果");
		}
	}

}
