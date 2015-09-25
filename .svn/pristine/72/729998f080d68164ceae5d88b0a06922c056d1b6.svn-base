package com.common.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.common.util.AppUtil;

import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;
import android.net.Uri;

public class MyHttpClient {

	private static final String TAG = "MyHttpClient";
	private static HttpClient myHttpClient;

	private static final int GET = 0;
	public static final int POST = 1;
	private static final int POST_WITH_FILE = 4;
	private static final int PUT = 2;
	private static final int DELETE = 3;

	/** 获得 HttpClient (HTTP, HTTPS) */
	public static synchronized HttpClient getHttpClient() {
		if (null == myHttpClient) {
			try {
				HttpParams params = new BasicHttpParams();
				// 设置基本参数
				HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
				HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
				HttpProtocolParams.setUseExpectContinue(params, false);
				params.setParameter("Proxy-Connection", "Keep-Alive");

				// // 为并发多线程配置该项
				// params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE,
				// new ConnPerRouteBean(10));
				// params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS,
				// 10);

				// 超时设置
				ConnManagerParams.setTimeout(params, 30 * 1000); // 从连接池中取连接的超时时�?获取连接的最大等待时�?
				HttpConnectionParams.setConnectionTimeout(params, 30 * 1000); // 连接超时
				HttpConnectionParams.setSoTimeout(params, 30 * 1000); // 读取超时
				// Socket 缓存大小
				HttpConnectionParams.setSocketBufferSize(params, 8 * 1024);

				// 设置我们的HttpClient支持HTTP和HTTPS两种模式
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				registry.register(new Scheme("https", sf, 443));
				// 使用线程安全的连接管理来创建HttpClient
				ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, registry);
				myHttpClient = new DefaultHttpClient(conMgr, params);
			} catch (Exception e) {
				return new DefaultHttpClient();
			}
		}
		// 每次请求都清除cookie，避免造成干扰
		BasicCookieStore cookie = new BasicCookieStore();
		((DefaultHttpClient) myHttpClient).setCookieStore(cookie);
		return myHttpClient;
	}

	public static void clearCookie() {
		BasicCookieStore cookie = new BasicCookieStore();
		((DefaultHttpClient) getHttpClient()).setCookieStore(cookie);
	}

	/** 判断Wap网络并设置代理 */
	public static boolean checkAndSetWap(Context mContext, HttpClient client) {
		NetworkType networkType = NetworkControl.getNetworkType(mContext);
		if (networkType != null && networkType.getType() == NetworkType.NET_TYPE_MOBILE_WAP) {
//			AppUtil.printLog(TAG, "is wap network!");
			String proxyName = networkType.getProxy();
			int proxyPort = networkType.getPort();

			Uri uri = Uri.parse("content://telephony/carriers/preferapn"); // 获取当前正在使用的APN接入�?
			Cursor mCursor = mContext.getContentResolver().query(uri, null, null, null, null);
			if (mCursor != null && mCursor.getCount() > 0) {
				mCursor.moveToNext(); // 游标移至第一条记录
				String proxyStr = mCursor.getString(mCursor.getColumnIndex("proxy"));
//				AppUtil.printLog(TAG, "set wap proxy1 : " + proxyStr);
				if (proxyStr != null && proxyStr.trim().length() > 0) {
					proxyName = proxyStr;
					proxyPort = 80;
				}
			}
//			AppUtil.printLog(TAG, "set wap proxy : " + proxyName);
			HttpHost proxy = new HttpHost(proxyName, proxyPort);
			client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
			return true;
		}
		return false;
	}

	/**
	 * 封装POST请求
	 */
	public static String post(Context mContext, String httpUrl, List<NameValuePair> params) {
		return getHttpResult(mContext, POST, httpUrl, params);
	}

	/**
	 * 封装带文件的POST请求
	 */
	public static String postWithFile(Context mContext, String httpUrl, List<NameValuePair> params) {
		return getHttpResult(mContext, POST_WITH_FILE, httpUrl, params);
	}

	/**
	 * 封装GET请求
	 */
	public static String get(Context mContext, String httpUrl) {
		return getHttpResult(mContext, GET, httpUrl, null);
	}

	/**
	 * 封装DELETE请求
	 */
	public static String delete(Context mContext, String httpUrl) {
		return getHttpResult(mContext, DELETE, httpUrl, null);
	}
	
	/**
	 * 封装PUT请求
	 * @param mContext
	 * @param httpUrl
	 * @param params
	 * @return
	 */
	public static String put(Context mContext,String httpUrl,List<NameValuePair> params){
		return getHttpResult(mContext, PUT, httpUrl, params);
	}

	public static String getHttpResult(Context mContext, int method, String httpUrl, List<NameValuePair> params) {
		HttpResponse httpResponse = getHttpResponse(mContext, method, httpUrl, params);
		try {
			if (httpResponse != null) {
				HttpEntity resEntity = httpResponse.getEntity();
				if (resEntity != null) {
					String result = EntityUtils.toString(resEntity, HTTP.UTF_8);
					if (result != null) {
						return result;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
//			AppUtil.printLog(TAG, "读取失败" + e.getMessage());
		} catch (IOException e) {
//			AppUtil.printLog(TAG, "读取失败" + e.getMessage());
		}
		return "";
	}

	public static HttpResponse getHttpResponse(Context mContext, int method, String httpUrl, List<NameValuePair> params) {
		try {
			HttpClient client = getHttpClient();
			HttpRequestBase request = null;
			switch (method) {
			case GET:
				request = new HttpGet(httpUrl);
				break;
			case POST:
				request = new HttpPost(httpUrl);
				if (params != null) {
					((HttpPost) request).setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				}
				break;
			case PUT:
				request = new HttpPut(httpUrl);
				if (params != null) {
					((HttpPut) request).setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				}
				break;
			case DELETE:
				request = new HttpDelete(httpUrl);
				break;
			case POST_WITH_FILE:
				request = new HttpPost(httpUrl);
				if (params != null) {
					MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
					for (NameValuePair param : params) {
						if (param.getName().equals("uploadFile")) {
							// use FileBody to transfer the file data
							entity.addPart(param.getName(), new FileBody(new File(param.getValue())));
						} else {
							entity.addPart(param.getName(), new StringBody(param.getValue()));
						}
					}
					((HttpPost) request).setEntity(entity);
				}
				break;
			}
			// 取得HttpResponse
			HttpResponse httpResponse = client.execute(request);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				// 连接不成功时，判断Wap网络并设置代理
				boolean isWapProxy = checkAndSetWap(mContext, client);
				if (isWapProxy) {
					httpResponse = client.execute(request);
				}
			}
			// HttpStatus.SC_OK表示连接成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return httpResponse;
			} else {
//				AppUtil.printLog(TAG, "网络请求错误");
				// AppUtil.showShortMessage(mContext, "网络请求错误");
			}
		}catch (Exception e) {
			AppUtil.printLog(TAG, "网络错误" + e.getMessage());
			// AppUtil.showShortMessage(mContext, "网络错误");
		}
		return null;
	}

	public static InputStream getStream(String urlpath) throws Exception {
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inStream = conn.getInputStream();
			return filterInputStream(inStream);
		} else {
			return null;
		}
	}

	private static InputStream filterInputStream(InputStream is) throws IOException {
		String in = getStringFromInputStream(is);
		in = in.replace("&lt;", "<");
		in = in.replace("&gt;", ">");
		return getInputStreamFromString(in);
	}

	private static InputStream getInputStreamFromString(String str) {
		ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
		return bis;
	}

	private static String getStringFromInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}
