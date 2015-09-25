package com.wx.doctor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.common.util.AppUtil;
import com.common.util.DocException;
import com.wx.doctor.MyApplication;

public class Baby implements Serializable{
	
	private static final long serialVersionUID = -7199090895056966110L;
	
	private static final String TAG = "Baby";
	
	public String babyId;
	public String phoneNumber;
	public String doctorName;
	public String realname;
	public String alias;
	public String sex;
	public String birthday;
	public String picpath;
	public String babyAge;
	public String docId;
	public String provinceid;
	public String provincename;
	public String cityid;
	public String cityname;
	public String hospitalid;
	public String hospitalname;
	public String doctorid;
	public String doctorname;
	public String joindate;
	public String createdate;
	public String modifydate;
	public String ntype;
	public String nflag;
	public String begindate;
	public String enddate;
	public String payrecid;
	
	public List<String> ids = new ArrayList<String>();
	
	/**
	 * 获取首页我的诊所宝宝列表
	 * @param json
	 * @return
	 */
	public static List<Baby> parseBabyList(JSONObject json) throws DocException{
		List<Baby> babyList = new ArrayList<Baby>();
		JSONArray jsonArray = AppUtil.getJsonArray(json, "Data");
		for(int i = 0 ; i < jsonArray.length() ; i++){
				try {
					JSONObject jsonData = jsonArray.getJSONObject(i);
					Baby baby = new Baby();
					String id  = AppUtil.getJsonStringValue(jsonData, "id");
					if(id != null){
						MyApplication.setIds(babyList.size(),id);
					}
					baby.phoneNumber = AppUtil.getJsonStringValue(jsonData, "phonenum");
					MyApplication.appCache.setBabyPhoneNumber(baby.phoneNumber);
					baby.doctorName = AppUtil.getJsonStringValue(jsonData, "usrname");
					baby.realname = AppUtil.getJsonStringValue(jsonData, "name");
					baby.sex = AppUtil.getJsonStringValue(jsonData, "sex");
					baby.birthday = AppUtil.getJsonStringValue(jsonData, "birthday");
					baby.picpath = AppUtil.getJsonStringValue(jsonData, "picpath");
					baby.alias = AppUtil.getJsonStringValue(jsonData, "alias");
					babyList.add(baby);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}
		return babyList;
	}
	
	/**
	 * 解析婴儿详细信息
	 * @param json
	 * @return
	 * @throws DocException
	 */
	public static Baby parseBaby(JSONObject json) throws DocException{
		JSONArray array = AppUtil.getJsonArray(json, "Data");
		Baby baby = null;
		for(int i = 0 ; i <array.length();i++){
			try {
				JSONObject jsonData  = array.getJSONObject(i);
				baby = new Baby();
				baby.babyId = AppUtil.getJsonStringValue(jsonData, "id");
				baby.phoneNumber = AppUtil.getJsonStringValue(jsonData, "phonenum");
				baby.doctorName = AppUtil.getJsonStringValue(jsonData, "usrname");
				baby.realname = AppUtil.getJsonStringValue(jsonData, "name");
				baby.alias =AppUtil.getJsonStringValue(jsonData, "alias");
				baby.sex = AppUtil.getJsonStringValue(jsonData, "sex");
				baby.birthday = AppUtil.getJsonStringValue(jsonData, "birthday");
			    baby.provinceid = AppUtil.getJsonStringValue(jsonData, "provinceid");
			    baby.provincename = AppUtil.getJsonStringValue(jsonData, "provincename");
			    baby.cityid = AppUtil.getJsonStringValue(jsonData, "cityid");
			    baby.cityname = AppUtil.getJsonStringValue(jsonData, "cityname");
			    baby.hospitalid = AppUtil.getJsonStringValue(jsonData, "hospitalid");
			    baby.hospitalname= AppUtil.getJsonStringValue(jsonData, "hospitalname");
			    baby.doctorid= AppUtil.getJsonStringValue(jsonData, "doctorid");
			    baby.doctorname= AppUtil.getJsonStringValue(jsonData, "doctorname");
			    baby.joindate= AppUtil.getJsonStringValue(jsonData, "joindate");
			    baby.createdate= AppUtil.getJsonStringValue(jsonData, "createdate");
			    baby.modifydate= AppUtil.getJsonStringValue(jsonData, "modifydate");
			    baby.ntype= AppUtil.getJsonStringValue(jsonData, "ntype");
			    baby.nflag= AppUtil.getJsonStringValue(jsonData, "nflag");
			    baby.begindate= AppUtil.getJsonStringValue(jsonData, "begindate");
			    baby.enddate= AppUtil.getJsonStringValue(jsonData, "enddate");
			    baby.payrecid= AppUtil.getJsonStringValue(jsonData, "payrecid");
				baby.picpath = AppUtil.getJsonStringValue(jsonData, "picpath");
				baby.alias = AppUtil.getJsonStringValue(jsonData, "alias");
				return baby;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return baby;
	}
}
