package com.wx.doctor.activity;

import java.util.List;

import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.adapter.BabyListAdapter;
import com.wx.doctor.adapter.ContackDoctorAdapter;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.RequireBabyListTask;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.RequireBabyNotJoinListTask;
import com.wx.doctor.fragment.BaseFragment;
import com.wx.doctor.model.Baby;
import com.wx.doctor.util.JsonUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AddNotJoinBabyListActivity extends BaseActivity {
	
	private ListView showListView;
	private LinearLayout nullLayout;
	private ContackDoctorAdapter mAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnotjoinbabylist);
		init();
	}
	
	private void init(){
	    initActionBar("关联宝宝", true);
	    showListView = (ListView)findViewById(R.id.add_notjoin_babylist);
		nullLayout = (LinearLayout)findViewById(R.id.adddoctor_null);
	    requireBabyListData(JsonUtil.getNotJoinBabyListJsonString());
	}
	
	/**
	 * 获取BabyList
	 * @param jsonString
	 */
	private void requireBabyListData(String jsonString){
		new RequireBabyNotJoinListTask(requireBabyListUiChange,new DocApi(MyApplication.mContext)).execute(jsonString);
	}
	
	public void update(String jsonString){
		///如果数据的大小>0 则 显示数据列表 否则显示空列表  开辟一个记时间的缓存 每隔一段时间清理缓存
		//检测本地缓存的大小 若是本地缓存>0 从本地缓存获取 若是本地缓存<0 从后台获取 
		new RequireBabyNotJoinListTask(requireBabyListUiChange,new DocApi(MyApplication.mContext)).execute(jsonString);
	}
	
	
	public ContackDoctorAdapter getAdapter(){
		if(mAdapter !=null){
			return mAdapter;
		}
		return null;
	}
	
	private void showNull(){
		showListView.setVisibility(View.GONE);
		nullLayout.setVisibility(View.VISIBLE);
	}
	
	private void showBabyList(List<Baby> list){
		showListView.setVisibility(View.VISIBLE);
		nullLayout.setVisibility(View.GONE);
		if(mAdapter == null){
			mAdapter = new ContackDoctorAdapter(AddNotJoinBabyListActivity.this, list);
			showListView.setAdapter(mAdapter);
		}else{
			mAdapter.update(list);
		}
	}
	
	private UiChange requireBabyListUiChange = new UiChange() {
		@Override
		public void preUiChange() {
			progress = AppUtil.showProgress(AddNotJoinBabyListActivity.this, getString(R.string.require_data));
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
				AppUtil.showShortMessage(AddNotJoinBabyListActivity.this, getString(R.string.require_data_fail));
			}
		}
	};
}
