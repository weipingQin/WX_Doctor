package com.common.http;

public class NetworkType {

	public static final int NET_TYPE_UNAVAILABLE = 0;
	public static final int NET_TYPE_WIFI = 1;
	public static final int NET_TYPE_MOBILE_NET = 2;
	public static final int NET_TYPE_MOBILE_WAP = 3;

	private int type = NET_TYPE_UNAVAILABLE;
	private String proxy = null;
	private int port = 0;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
