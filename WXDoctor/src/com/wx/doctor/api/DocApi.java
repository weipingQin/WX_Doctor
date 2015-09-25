package com.wx.doctor.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.common.util.AppUtil;
import com.common.util.BaseApi;
import com.common.util.DocException;
import com.example.wxdoctor.R;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.model.Type;
import com.wx.doctor.model.User;

public class DocApi extends BaseApi{

	public static final String TAG = "DocApi";
	private String phonenum="13456789000";//SPUtils.getString("phonenum");
	private String pwd="fuckGfw";//SPUtils.getString("pwd");//

	private Context mContext;

	public DocApi(Context context) {
		super(context);
		mContext = context;
	}
	
	private JSONObject getContext(){
		JSONObject jobj=new JSONObject();
		try {
			jobj.put("phonenum", this.phonenum);
			jobj.put("pwd", this.pwd);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
		return jobj;
	}
	private JSONObject getReqType(String rt){
		JSONObject jobj=new JSONObject();
		try {
			jobj.put("rt", rt);
		} catch (JSONException e) {
			//e.printStackTrace();
		}
		return jobj;
	}
	
	private void putPre(JSONObject jobj, String rt, boolean preContext){
		try {
			if(preContext) jobj.put("context", this.getContext());
			jobj.put("reqType", this.getReqType(rt));
		} catch (JSONException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * 用户注册
	 * @param JSONObject jsonString
	 * @return
	 * @throws DocException
	 */
	public User register(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.doctor_user), mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url, params);
		int errorCode = AppUtil.getJsonIntegerValue(json, "Result");
		if(errorCode == 1){
			JSONArray jsonarray = AppUtil.getJsonArray(json, "Data");
			for(int i = 0 ; i < jsonarray.length(); i++){
				JSONObject jsonobj;
				try {
					jsonobj = jsonarray.getJSONObject(i);
					User user = new User();
					user.userPhoneNumber = AppUtil.getJsonStringValue(jsonobj,"phonenum");
					user.userName = AppUtil.getJsonStringValue(jsonobj,"alias");
					user.userPassword = AppUtil.getJsonStringValue(jsonobj,"password");
					return user;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}else{
			return null;
		}
		return null;
	}

	/**
	 * 用户登录
	 * @param jsonString
	 * @return
	 * @throws JSONException 
	 */
	public User login(String jsonString) throws DocException, JSONException{
		String url = String.format(mContext.getString(R.string.doctor_user), mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url, params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			JSONArray jsonArray = AppUtil.getJsonArray(json, "Data");
			JSONObject UserData = jsonArray.getJSONObject(0);
			User user = new User();
			user.userPhoneNumber = AppUtil.getJsonStringValue(UserData,"phonenum");
			user.userName = AppUtil.getJsonStringValue(UserData,"alias");
			user.userPassword = AppUtil.getJsonStringValue(UserData,"password");
			return user;
		}else{
			return null;
		}
	}
	/**
	 * 重置密码接口
	 * @param jsonString
	 * @throws DocException
	 */
	public Boolean resetPassword(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.doctor_user), mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url, params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改密码 
	 */
	
	public Boolean modifyPassword(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.modify_password), mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url, params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 首页中获取宝宝信息列表
	 * @param jsonString
	 * @return
	 * @throws DocException
	 */
	public List<Baby> requireBabyList(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_baby_list), mContext.getString(R.string.host_url_baby));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Baby.parseBabyList(json);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取宝宝详细信息
	 * @param jsonString
	 * @return
	 * @throws DocException
	 */
	public Baby requireBabyInfo(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_babyinfo), mContext.getString(R.string.host_url_baby));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Baby.parseBaby(json);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取最新版本
	 */
	public List<Type> requireType(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.host_url_sys), mContext.getString(R.string.host_url_sys));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Type.parseType(json);
		}else{
			return null;
		}
	}
	
	/**
	 * 医生关联婴儿
	 * @return
	 */
	public boolean requireDoctorContactBaby(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_join_doctor),mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url, params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 获取IM请求接口
	 * @return
	 * @throws DocException
	 */
	public List<Doctor> requireIM(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_baby_im), mContext.getString(R.string.host_url_imApi));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Doctor.parseDoctorList(json);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取未关联的宝宝信息列表 
	 * @param jsonString
	 * @return
	 * @throws DocException
	 */
	public List<Baby> requireBabyNotJoinList(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_baby_not_list), mContext.getString(R.string.host_url_baby));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Baby.parseBabyList(json);
		}else{
			return null;
		}
	} 

	/**
	 * 获取专家列表
	 * getDoctorListJsonString
	 */
	public List<Doctor> requireDoctorList(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_prodoctor_list), mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Doctor.parseDoctorList(json);
		}else{
			return null;
		}
	}
	
	public Doctor requireDoctorInfo(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_prodoctor_list),mContext.getString(R.string.host_url_doctor));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk",jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code = AppUtil.getJsonIntegerValue(json, "Result");
		if(code == 1){
			return Doctor.parseDoctorInfo(json);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws DocException
	 */
	public boolean requireVerfixCode(String jsonString) throws DocException{
		String url = String.format(mContext.getString(R.string.require_verfix_code), mContext.getString(R.string.host_url_imApi));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("lk", jsonString));
		JSONObject json = getJsonResponseByPost(url,params);
		int code  = AppUtil.getJsonIntegerValue(json,"Result");
		if(code == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/*医生即时通讯获取Token
	注册请求类型：rt: GetDoctorToken 对应API: GetTokenApi；
	客户端请求：{"reqType":{"rt":"GetDoctorToken"},"Data":{"phonenum":"13588719517"}} 
	      服务端返回：
	{ "Message": "", "Result": 1, 
	"Data": [ { "docid": 1, "phonenum": 13588719517, "name": "王医生",
	 "token":"N9P4q9PGFdkN6q8OElYihqrnKqBzBhzc5T+g7KHbJduK1FS3heWFt1nnAktvtT7
	+/TjlEoMC43QI3WaXwzrH/zqa0Yw2pUWF", 
	"picpath": "http://121.43.230.238:8082/UPFile/13588719517/DoctorPhoto/13588719517.png" } ] }
	注意：Result:0 表示获取数据失败；
	 phonenum：医生用户    返回数据区的token就是返回的token
	                 Token 由 phonenum   去获取的token ，作为唯一标识*/
	
	public JSONObject buildGetDoctorTokenReq(String phonenum){
		JSONObject jobj=new JSONObject();
		this.putPre(jobj, "GetDoctorToken", false);
		
		try {
			JSONObject docToken=new JSONObject();
			docToken.put("phonenum", phonenum);
			jobj.put("Data", docToken);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobj;
	
	}

}
