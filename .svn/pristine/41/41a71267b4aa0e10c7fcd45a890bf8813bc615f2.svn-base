package com.wx.doctor.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

public class WiFiManager {

	//设置是否打开Wifi 
	public static void toggleWiFi(Context context, boolean enabled) { 
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE); 
		wifiManager.setWifiEnabled(enabled); 
	} 

	public static void toggleMobileData(Context context, boolean enabled) {   
		ConnectivityManager connectivityManager =  
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		Class<?> connectivityManagerClass = null; 
		Field connectivityManagerField = null; 
		Class<?> iConnectivityManagerClass = null; 
		Object iConnectivityManagerObject = null; 
		Method setMobileDataEnabledMethod = null; 
		try { 
			connectivityManagerClass = Class.forName(connectivityManager.getClass().getName()); 
			connectivityManagerField = connectivityManagerClass.getDeclaredField("mService"); 
			connectivityManagerField.setAccessible(true); 
			iConnectivityManagerObject = connectivityManagerField.get(connectivityManager); 
			iConnectivityManagerClass = Class.forName(iConnectivityManagerObject.getClass().getName()); 
			setMobileDataEnabledMethod =  
					iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE); 
			setMobileDataEnabledMethod.setAccessible(true); 
			setMobileDataEnabledMethod.invoke(iConnectivityManagerObject,enabled); 
		} catch (ClassNotFoundException e) {    
			e.printStackTrace();   
		} catch (NoSuchFieldException e) {    
			e.printStackTrace();   
		} catch (SecurityException e) {    
			e.printStackTrace();   
		} catch (NoSuchMethodException e) {    
			e.printStackTrace();   
		} catch (IllegalArgumentException e) {    
			e.printStackTrace();   
		} catch (IllegalAccessException e) {    
			e.printStackTrace();   
		} catch (InvocationTargetException e) {    
			e.printStackTrace();   
		}  
	} 

}
