package com.wx.doctor.adapter;

import java.util.List;

import com.common.image.SmartImage;
import com.common.image.SmartImageView;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.MyApplication;
import com.wx.doctor.api.DocApi;
import com.wx.doctor.asyncTask.BaseTask.UiChange;
import com.wx.doctor.asyncTask.ContactDoctorTask;
import com.wx.doctor.model.Baby;
import com.wx.doctor.model.Doctor;
import com.wx.doctor.util.JsonUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ContackDoctorAdapter extends BaseAdapter{

	public LayoutInflater inflater;
	public Context mContext;
	public List<Baby> mList;

	//private ProgressDialog progress;

	public ContackDoctorAdapter(Context context,List<Baby> list){
		mContext = context;
		inflater = LayoutInflater.from(context);
		mList = list;
	}

	public void update(List<Baby> list){
		mList = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.adddoctoritem, null);
			holder = new ViewHolder();
			holder.HeaderImageView = (SmartImageView)convertView.findViewById(R.id.adddoctor_item_header_imageview);
			holder.babyNameTextView = (TextView)convertView.findViewById(R.id.adddoctor_item_header_textview);
			holder.babyInfoTextView = (TextView)convertView.findViewById(R.id.adddoctor_item_babyinfo_textview);
			holder.babyAgeTextView = (TextView)convertView.findViewById(R.id.adddoctor_item_age_textview);
			holder.babyContactDoctorView = (TextView)convertView.findViewById(R.id.adddoctor_item_contact_textview);
			holder.babyContactDoctorView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					/*String phoneNumber = MyApplication.appCache.getUserPhoneNumber();
					String password = MyApplication.appCache.getUserPassword();
					String IMesiStr = MyApplication.appCache.getIMSI();
					String IMeiStr  = MyApplication.appCache.getIMEI();*/
					//测试数据 
					String phoneNumber = "13588719517";
					String password	= "fuckGfw";
					String IMesiStr = "460000000000000";
					String IMeiStr = "353641012835017";
					new ContactDoctorTask(contactUiChange, new DocApi(MyApplication.mContext)).execute(JsonUtil.getJoinBabyToDoctorJsonString(phoneNumber, password, IMesiStr, IMeiStr));
				}
			});
			//	holder.HeaderImageView.setImageResource(mList.get(position).picpath);
			holder.HeaderImageView.setImageUrl(mList.get(position).picpath);
			holder.babyNameTextView.setText(mList.get(position).realname);
			holder.babyInfoTextView.setText(mList.get(position).doctorName+"("+ mList.get(position).phoneNumber+")");
			holder.babyAgeTextView.setText(mList.get(position).babyAge);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag(); 
		}
		if(position % 2!=0){
			convertView.setBackgroundColor(0xf9f9f9);
		}else{
			convertView.setBackgroundColor(0xffffff);
		}
		return convertView;
	}

	class ViewHolder{
		SmartImageView HeaderImageView;
		TextView  babyNameTextView;
		TextView  babyInfoTextView;
		TextView  babyAgeTextView;
		TextView  babyContactDoctorView;
	} 

	private UiChange contactUiChange = new UiChange(){

		@Override
		public void preUiChange() {
			//progress = AppUtil.showProgress(MyApplication.mContext);
		}

		@Override
		public void lateUiChange(Object result) {
			/*if(progress !=null){
				progress.dismiss();
			}*/
			Boolean success = (Boolean)result;
			if(success){
				AppUtil.showShortMessage(MyApplication.mContext, "您已成功关联此医生!");
			}else{
				AppUtil.showShortMessage(MyApplication.mContext, "关联医生失败！");
			}
		}

	};

}
