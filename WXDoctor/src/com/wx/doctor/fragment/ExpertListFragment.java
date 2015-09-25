package com.wx.doctor.fragment;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.activity.BabyConductDocotorActivity;
import com.wx.doctor.activity.DoctorConductExpertActivity;
import com.wx.doctor.adapter.BabyListAdapter;
import com.wx.doctor.adapter.ExpertListAdapter;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.RequireBabyListTask;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireDoctorListTask;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.util.JsonUtil;

public class ExpertListFragment extends BaseFragment{
	
	private LinearLayout nullLayout;
	private ListView showListView;
	private ProgressDialog progress;
	private ExpertListAdapter mAdapter;
	private static ExpertListFragment expertListFragment; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		expertListFragment = this;
		requireDoctorListData(JsonUtil.getDoctorListJsonString());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_list, null);
		nullLayout = (LinearLayout)view.findViewById(R.id.main_no_list);
		showListView = (ListView)view.findViewById(R.id.main_list_listview);
		//跳转到专家聊天界面 
		showListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(getActivity(),DoctorConductExpertActivity.class);
				Bundle bundle = new Bundle();
				Doctor doctor = (Doctor)mAdapter.getItem(position);
				bundle.putSerializable("doctor", doctor);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		init(view);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void init(View view){
		initActionBar("专家咨询",true);
	}
	
	/**
	 * 获取 DoctorList
	 * @param jsonString
	 */
	private void requireDoctorListData(String jsonString){
		new RequireDoctorListTask(requireDoctorListUiChange,new DocApi(MyApplication.mContext)).execute(jsonString);
	}
	
	private void showNull(){
		showListView.setVisibility(View.GONE);
		nullLayout.setVisibility(View.VISIBLE);
	}
	
	private void showDoctorList(List<Doctor> list){
		showListView.setVisibility(View.VISIBLE);
		nullLayout.setVisibility(View.GONE);
		if(mAdapter == null){
			mAdapter = new ExpertListAdapter(getActivity(), list);
			showListView.setAdapter(mAdapter);
		}else{
			mAdapter.update(list);
		}
	}
	
	public void update(String jsonString){
		///如果数据的大小>0 则 显示数据列表 否则显示空列表  开辟一个记时间的缓存 每隔一段时间清理缓存
		//检测本地缓存的大小 若是本地缓存>0 从本地缓存获取 若是本地缓存<0 从后台获取 
		new RequireDoctorListTask(null,new DocApi(MyApplication.mContext)).execute(jsonString);
	}
	
	public ExpertListAdapter getAdapter(){
		if(mAdapter !=null){
			return mAdapter;
		}
		return null;
	}
	
	private UiChange requireDoctorListUiChange = new UiChange() {
		@Override
		public void preUiChange() {
		//	progress = AppUtil.showProgress(getActivity(), getString(R.string.require_data));
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			List<Doctor> doctorList = (List<Doctor>)result;
			if(doctorList !=null  && doctorList.size() > 0 ){
				showDoctorList(doctorList);
				///将数据存入到缓存数据库 
				//AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_success));
			}else{
				AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_fail));
			}
		}
	};
}
