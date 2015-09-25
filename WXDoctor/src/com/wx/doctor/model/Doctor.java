package com.wx.doctor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.common.util.AppUtil;
import com.common.util.DocException;

public class Doctor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2654360736146371294L;
	
	public String doctorId;
	public String doctorName;
	public String doctorPhone;
	public String doctorSex;
	public String doctorHosptialName;
	public String doctorForHosptialId;
	public String doctorTitle;
	public String doctorPicpath;
	public String cityName;
	public String districtname;
	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String intro;
	public String doctorHeadImage;
	public String token;
	
	/**
	 * 获取下一页专家咨询的列表 
	 * @param json
	 * @return
	 */
	public static List<Doctor> parseDoctorList(JSONObject json){
		List<Doctor> doctorList = new ArrayList<Doctor>(); 
		JSONArray jsonArray = AppUtil.getJsonArray(json, "Data");
		for(int i = 0 ; i < jsonArray.length() ; i++){
			try {
				JSONObject jsonData  = jsonArray.getJSONObject(i);
				Doctor doctor = new Doctor();
				doctor.doctorId = AppUtil.getJsonStringValue(jsonData, "id");
				doctor.doctorPhone = AppUtil.getJsonStringValue(jsonData, "phonenum");
				doctor.doctorSex = AppUtil.getJsonStringValue(jsonData, "sex");
				doctor.doctorName = AppUtil.getJsonStringValue(jsonData, "name");
				doctor.doctorHosptialName = AppUtil.getJsonStringValue(jsonData,"hospitalname");
				doctor.doctorTitle = AppUtil.getJsonStringValue(jsonData, "title");
				doctor.doctorPicpath = AppUtil.getJsonStringValue(jsonData, "picpath"); 
				doctorList.add(doctor);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return doctorList;
		}
		return doctorList;
	}
	
	/**
	 * 获取医生详细信息
	 */
	public static Doctor parseDoctorInfo(JSONObject json) throws DocException{
		JSONArray jsonArray = AppUtil.getJsonArray(json, "Data");
		Doctor doctor = null;
		for(int i = 0 ; i < jsonArray.length(); i++){
			doctor = new Doctor();
			try{
				JSONObject jsonData  = jsonArray.getJSONObject(i);
				doctor.doctorId = AppUtil.getJsonStringValue(jsonData, "id");
				doctor.doctorPhone = AppUtil.getJsonStringValue(jsonData, "phonenum");
				doctor.doctorSex = AppUtil.getJsonStringValue(jsonData, "sex");
				doctor.doctorName= AppUtil.getJsonStringValue(jsonData, "name");
				doctor.doctorForHosptialId = AppUtil.getJsonStringValue(jsonData, "hospitalid");
				doctor.doctorHosptialName = AppUtil.getJsonStringValue(jsonData, "hospitalname");
				doctor.doctorTitle = AppUtil.getJsonStringValue(jsonData, "title");
				doctor.cityName = AppUtil.getJsonStringValue(jsonData, "cityname");
				doctor.districtname = AppUtil.getJsonStringValue(jsonData, "districtname");
				doctor.intro = AppUtil.getJsonStringValue(jsonData, "intro");
				doctor.doctorPicpath = AppUtil.getJsonStringValue(jsonData, "picpath");
				return doctor;
			}catch(JSONException e){
				e.printStackTrace();
			}
		}
		return doctor;
	}
	
	/**
	 * 获取聊天IM相关信息
	 * @param json
	 * @return
	 * @throws DocException
	 */
	public static List<Doctor> parseIM(JSONObject json) throws DocException{
		List<Doctor> doctorList = new ArrayList<Doctor>(); 
		JSONArray jsonArray = AppUtil.getJsonArray(json, "Data");
		for(int i = 0 ; i < jsonArray.length() ; i++){
			try {
				JSONObject jsonData  = jsonArray.getJSONObject(i);
				Doctor doctor = new Doctor();
				doctor.doctorPhone = AppUtil.getJsonStringValue(jsonData, "phonenum");
				doctor.doctorName = AppUtil.getJsonStringValue(jsonData, "name");
				doctor.doctorPicpath = AppUtil.getJsonStringValue(jsonData, "picpath");
				doctor.doctorId = AppUtil.getJsonStringValue(jsonData, "docid");
				doctor.token = AppUtil.getJsonStringValue(jsonData, "token");
				doctorList.add(doctor);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return doctorList;
		}
		return doctorList;
	}
}
