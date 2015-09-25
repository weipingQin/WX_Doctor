package com.wx.doctor.fragment;

import java.util.List;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.google.gson.Gson;
import com.wx.doctor.MyApplication;
import com.wx.doctor.activity.AddNotJoinBabyListActivity;
import com.wx.doctor.activity.BabyConductDocotorActivity;
import com.wx.doctor.activity.BabyInfoActivity;
import com.wx.doctor.activity.BaseActivity;
import com.wx.doctor.activity.FragmentCallBack;
import com.wx.doctor.activity.LoginActivity;
import com.wx.doctor.activity.MainActivity;
import com.wx.doctor.adapter.BabyListAdapter;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireBabyListTask;
import com.wx.doctor.asyncTask.RequireBabyNotJoinListTask;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Context;
import com.wx.doctor.model.Data;
import com.wx.doctor.model.ReqType;
import com.wx.doctor.model.User;
import com.wx.doctor.model.WXModel;
import com.wx.doctor.model.Where;
import com.wx.doctor.util.JsonUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class BabyListFragment extends BaseFragment{
	private LinearLayout nullLayout;
	private ListView showListView;
	private ProgressDialog progress;
	private BabyListAdapter mAdapter;
	private  BabyListFragment babyListFragment; 
	FragmentCallBack fragmentCallBack = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		babyListFragment = this;
		requireBabyListData(JsonUtil.getBabyListJsonString());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_list, null);
		nullLayout = (LinearLayout)view.findViewById(R.id.main_no_list);
		showListView = (ListView)view.findViewById(R.id.main_list_listview);
		showListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(getActivity(),BabyConductDocotorActivity.class);
				Bundle bundle = new Bundle();
				Baby baby = (Baby)mAdapter.getItem(position);
				bundle.putSerializable("baby", baby);
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
		update(JsonUtil.getBabyListJsonString());
	}

	private void init(View view){
		initActionBarHome();
	}

	/**
	 * 获取BabyList
	 * @param jsonString
	 */
	private void requireBabyListData(String jsonString){
		new RequireBabyListTask(requireBabyListUiChange,new DocApi(MyApplication.mContext)).execute(jsonString);
	}

	private void showNull(){
		showListView.setVisibility(View.GONE);
		nullLayout.setVisibility(View.VISIBLE);
	}


	private void showBabyList(List<Baby> list){
		showListView.setVisibility(View.VISIBLE);
		nullLayout.setVisibility(View.GONE);
		if(mAdapter == null){
			mAdapter = new BabyListAdapter(getActivity(), list);
			showListView.setAdapter(mAdapter);
		}else{
			mAdapter.update(list);
		}
	}

	public void update(String jsonString){
		///如果数据的大小>0 则 显示数据列表 否则显示空列表  开辟一个记时间的缓存 每隔一段时间清理缓存
		//检测本地缓存的大小 若是本地缓存>0 从本地缓存获取 若是本地缓存<0 从后台获取 
		new RequireBabyListTask(requireBabyListUiChange,new DocApi(MyApplication.mContext)).execute(jsonString);
	}

	public BabyListAdapter getAdapter(){
		if(mAdapter !=null){
			return mAdapter;
		}
		return null;
	}

	private UiChange requireBabyListUiChange = new UiChange() {
		@Override
		public void preUiChange() {
			//progress = AppUtil.showProgress(getActivity(), getString(R.string.require_data));
		}

		@Override
		public void lateUiChange(Object result) {
			if(progress !=null){
				progress.dismiss();
			}
			List<Baby> babyList = (List<Baby>)result;
			if(babyList !=null  && babyList.size() > 0 ){
				showBabyList(babyList);
				///将数据存入到缓存数据库 
				//AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_success));
			}else{
				showNull();
				AppUtil.showShortMessage(getActivity(), getString(R.string.require_data_fail));
			}
		}
	};
}

