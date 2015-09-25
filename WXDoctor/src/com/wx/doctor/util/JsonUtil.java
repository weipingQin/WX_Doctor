package com.wx.doctor.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.wx.doctor.MyApplication;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.model.Where;

@SuppressLint("NewApi")
public class JsonUtil {
	
	public static final String TAG = JsonUtil.class.getSimpleName();
	/**
	 * 获取各个接口的Json数据格式 
	 */
	
	public static String getLoginJsonString(String phoneNumber){
		WXModel model = new WXModel();
		Context context = new Context();
		ReqType reqType = new ReqType();
		Data data = new Data();
		data.phonenum = phoneNumber;
		reqType.rt = ReqType.GET_DOC_USER;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 获取验证码信息的JsonString
	 * 实例格式:{"reqType":{"rt":"SMSCheck"},"Data":{"phonenum":"1358871889934",,"utype":"21"}}
	 * @return
	 */
	public static String getVerfixCodeJsonString(String phoneNumber){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_VERFIX_CODE;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.utype = "21";
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 检验登录接口 
	 * 
	 * Context区域是医生电话 Data区域是婴儿电话 
	 * @param phoneNumber
	 * @param password
	 * @param IMEIStr
	 * @param IMSIStr
	 * @param loginDate
	 * @param provinceName
	 * @param cityName
	 * @param districtname
	 * @return
	 */
	public static String checkLoginJsonString(String phoneNumber,String password,String IMEIStr,String IMSIStr,String loginDate,String provinceName,String cityName,String districtname){
		WXModel model = new WXModel();
		Context context = new Context();
		context.phonenum = phoneNumber;
		context.pwd = password;
		context.imei = IMEIStr;
		context.imsi = IMSIStr;
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_DOC_CHECK_USER;
		Data data = new Data();
		data.logindate = phoneNumber;
		data.provincename = provinceName;
		data.cityname = cityName;
		data.districtname = districtname;
		data.exa = "扩展1";
		data.exb = "扩展2";
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 注册用户的接口
	 * 
	 * Context区域是医生电话 Data区域是婴儿电话 
	 * 
	 * @param userName
	 * @param phoneNumber
	 * @param password
	 * @param ImeiStr
	 * @param ImsiStr
	 * @return
	 */
	public static String getRegisterJsonString(String userName,String phoneNumber,String password,String ImeiStr,String ImsiStr){
		WXModel model = new WXModel();
		Context context = new Context();
		ReqType reqType = new ReqType();
		Data data = new Data();
		data.alias = userName;
		data.phonenum = phoneNumber;
		data.password = password;
		data.outerid = "-1";
		reqType.rt = ReqType.GET_DOC_REGISTER;
		context.imei=ImeiStr;
		context.imsi=ImsiStr;
		context.phonenum=phoneNumber;
		context.pwd = password;
		model.setContext(context);
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		return gson.toJson(model);
	}
	/**
	 * 重置密码的接口JsonString
	 * 
	 * Context区域是医生电话 Data区域是婴儿电话 
	 * 
	 * 
	 * @param phoneNumber
	 * @param newPassword
	 * @param confrimPassword
	 * @return
	 */
	public static String getResetPasswordJsonString(String phoneNumber,String newPassword,String confrimPassword){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_DOC_MODIFY_PSWD;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.newpassword = newPassword;
		data.oldpassword = confrimPassword;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	public static String getModifyPasswordJsonString(String phoneNumber,String oldpassword,String newpassword){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_DOC_MODIFY_PSWD;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.newpassword = newpassword;
		data.oldpassword = oldpassword;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
		
	}
	
	/**
	 * 获取图片上传接口的JsonString 
	 * 
	 * Context区域是医生电话 Data区域是婴儿电话 
	 * 
	 * @param phoneNumberStr
	 * @param docIdStr
	 * @return
	 */
	public static String getPictureUploadJsonString(String phoneNumberStr,String docIdStr){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_DOC_UPLOAD_PIC;
		Data data = new Data();
		data.phonenum = phoneNumberStr;
		data.docid = docIdStr;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	/**
	 * 获取婴儿详细信息
	 */
	public static String getBabyInfoJsonString(String phoneNumber,String babyId){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = reqType.GET_DOC_BABY_LIST;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.babyid = babyId;
		model.setData(data);
		model.setReqType(reqType);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 获取医生详细信息
	 * 
	 * Context区域是医生电话 Data区域是婴儿电话 
	 * 
	 * 
	 * {"reqType":{"rt":"GetProDoctorInfo"},"Data":{"doctorid":2}} 
	 * 
	 * @return
	 */
	public static String getDoctorInfoJsonString(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_PRO_DOC_INFO;
		Data data = new Data();
		data.doctorid = "2";
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		Log.d(TAG,"getDoctorInfoJsonString"+jsonString);
		return jsonString;
	}
	/**
	 * 获取医院数据
	 * @param phoneNumber
	 * @param password
	 * @param ImeiStr
	 * @param IMesiStr
	 * @return
	 */
	public static String getHospitalJsonString(String phoneNumber,String password,String ImeiStr,String IMesiStr){
		WXModel model = new WXModel();
		Context context = new Context();
		context.phonenum = phoneNumber;
		context.pwd  = password;
		context.imei = ImeiStr;
		context.imsi = IMesiStr;
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_HOSPTIAL;
		Where where = new Where();
		where.provincename = "浙江省";
		where.cityname = "杭州市";
		where.districtname = "上城区";
		where.hospitalname = "南星街道社区卫生服务中心";
		model.setContext(context);
		model.setReqType(reqType);
		model.setWhere(where);
		Gson gson = new Gson();
		return gson.toJson(model);
	}
	
	/**
	 * 获取升级信息
	 */
	public static String getSysVersionString(String phoneNumber,String version){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = ReqType.GET_SYS_UPDATE;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.vertype = version;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString =  gson.toJson(model);
		return jsonString;
	}
	/**
	 * 获取宝宝信息的JsonString 
	 * @return
	 */
	public static  String getBabyListJsonString(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		Where where = new Where();
		where.index = "0";
		where.count = "10";
		where.doctorid = "1";
		where.babyInfo = "";
		reqType.rt = ReqType.GET_JOIN_BABY_LIST;
		model.setReqType(reqType);
		model.setWhere(where);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 获取还未关联的婴儿信息
	 * @return
	 */
	public static  String getNotJoinBabyListJsonString(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		Where where = new Where();
		where.index = "0";
		where.count = "10";
	//	where.doctorid = "1";
		where.babyInfo = "";
		reqType.rt = ReqType.GET_NOT_JOIN_BABY;
		model.setReqType(reqType);
		model.setWhere(where);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 宝宝关联医生
	 */
	public static String getJoinBabyToDoctorJsonString(String phoneNumber,String password,String IMesiStr,String IMeiStr){
		WXModel model = new WXModel();
		Context context = new Context();
		ReqType reqType = new ReqType();
		context.phonenum = phoneNumber;
		context.pwd = password;
		context.imei = IMeiStr;
		context.imsi = IMesiStr;
		reqType.rt = ReqType.GET_DOC_JOIN_DOC;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.babyid = "1234563";
		data.doctorid = "1";
		data.doctorname ="王医生";
		model.setReqType(reqType);
		model.setContext(context);
		model.setData(data);
		Gson gson = new Gson();
		return  gson.toJson(model);
	}
	
	/**
	 * 宝宝取消关联医生 
	 */
	public static String getCancelJoinBabyToDoctorJsonString(String phoneNumber,String password,String IMesiStr,String IMeiStr){
		WXModel model = new WXModel();
		Context context = new Context();
		ReqType reqType = new ReqType();
		context.phonenum = phoneNumber;
		context.pwd = password;
		context.imei = IMeiStr;
		context.imsi = IMesiStr;
		reqType.rt = ReqType.GET_DOC_NOT_JOIN_DOC;
		Data data = new Data();
		data.phonenum = phoneNumber;
		data.babyid = "1234563";
		data.doctorid = "1";
		data.doctorname ="王医生";
		model.setReqType(reqType);
		model.setContext(context);
		model.setData(data);
		Gson gson = new Gson();
		return  gson.toJson(model);
	}
	
	/**
	 * 获取医生列表
	 * 
	 * 服务端接收的原始数据：{"reqType":{"rt":"GetProDoctorList"},
	 * "where":{"count":"10","index":"0","prodoctorinfo":"王医生"}}
	 * 
	 * @return
	 */
	public static String getDoctorListJsonString(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		Where where = new Where();
		where.index = "0";
		where.count = "10";
		where.prodoctorinfo = "";
		reqType.rt = ReqType.GET_DOC_PRO_DOC;
		model.setReqType(reqType);
		model.setWhere(where);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 医生及时通讯获取消息
	 * 
	 */
	public static String getDoctorImJsonString(String babyPhoneNumber){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = reqType.GET_DOCTOR_TOKEN;
		Data data  = new Data();
		data.phonenum = babyPhoneNumber;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		return gson.toJson(model);
	}
	
	/*****************专家咨询****************************/
	/**
	 * 获取要咨询的专家列表
	 * @return
	 */
	public static String getProDoctorList(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = reqType.GET_DOC_PRO_DOC;
		Where where = new Where();
		where.index = "0";
		where.count = "10";
		where.prodoctorinfo = "王医生";
		model.setReqType(reqType);
		model.setWhere(where);
		Gson gson = new Gson();
		return gson.toJson(model);
	}
	
	/**
	 * 获取专家详细信息
	 */
	public static String getDoctorInfo(){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		reqType.rt = reqType.GET_PRO_DOC_INFO;
		Data data = new Data();
		data.doctorid = "2";
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 获取我的信息 
	 */
	public static String getMyInfo(String phoneNumber){
		WXModel model = new WXModel();
		ReqType reqType = new ReqType();
		Data data = new Data();
		data.phonenum = phoneNumber;
		reqType.rt = reqType.GET_DOC_USER;
		model.setReqType(reqType);
		model.setData(data);
		Gson gson = new Gson();
		String jsonString = gson.toJson(model);
		return jsonString;
	}
	
	/**
	 * 解析json文本，返回一个JSONObject
	 * @param content
	 * @return 如果content为空，或者解析错误，返回null
	 */
	public static JSONObject parseJSONObject(String content) {
		if(content==null || content.isEmpty()) return null;
		try {
			JSONTokener jsonParser = new JSONTokener(content);
			// 此时还未读取任何json文本，直接nextValue读取就是一个JSONObject对象。
			Object obj=jsonParser.nextValue();
			if(obj.getClass()==JSONObject.class)
				return (JSONObject) obj;
		} catch (JSONException ex) {
			//ex.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<JSONObject> toList(JSONArray arr){
		ArrayList<JSONObject> rslt=new ArrayList<JSONObject>();
		if(arr==null) return rslt;
		int len=arr.length();
		for(int i=0;i<len;i++){
			try {
				rslt.add(arr.getJSONObject(i));
			} catch (JSONException e) {
				//e.printStackTrace();
			}
		}
		return rslt;
	}
	
}
