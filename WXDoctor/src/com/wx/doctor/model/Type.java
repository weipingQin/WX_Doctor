package com.wx.doctor.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.ArrayAdapter;

import com.common.util.AppUtil;
import com.common.util.DocException;

public class Type {
	
	public String version;
	public String upaddress;
	public String url;
	public String vertype;
	public String remark;
	
	/**
	 * 
	 * { "Message": "", "Result": 1, 
	"Data": [ { "ver": "1.0.0.0", "upaddress": "http://121.43.230.238:8050/appdownload.html", "vertype": 12, "remark": "医生安卓版本" 
	} ] }
	 * 
	 * 
	 * @param json
	 * @return
	 * @throws DocException
	 */
	
	public static List<Type> parseType(JSONObject json) throws DocException{
		List<Type> typeList = new ArrayList<Type>(); 
		JSONArray array = AppUtil.getJsonArray(json, "Data");
		Type type = null;
		for(int i = 0 ; i <array.length();i++){
			try {
				JSONObject jsonData  = array.getJSONObject(i);
				type = new Type();
				type.remark = AppUtil.getJsonStringValue(jsonData,"remark");
				type.version = AppUtil.getJsonStringValue(jsonData,"ver");
				type.upaddress = AppUtil.getJsonStringValue(jsonData, "upaddress");
				type.vertype = AppUtil.getJsonStringValue(jsonData,"vertype");
				typeList.add(type);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return typeList;
	}
}
