package com.wx.doctor.adapter;

import java.util.List;

import com.common.image.SmartImage;
import com.common.image.SmartImageView;
import com.common.util.AppUtil;
import com.example.wxdoctor.R;
import com.wx.doctor.model.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpertListAdapter extends BaseAdapter{

	public LayoutInflater inflater;
	public Context mContext;
	public List<Doctor> mList;

	public ExpertListAdapter(Context context,List<Doctor> list){
		mContext = context;
		inflater = LayoutInflater.from(context);
		mList = list;
	}

	public void update(List<Doctor> list){
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

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.main_expert_item, null);
			holder = new ViewHolder();
			holder.HeaderImageView = (SmartImageView)convertView.findViewById(R.id.main_list_item_header_imageview);
			holder.doctorNameTextView = (TextView)convertView.findViewById(R.id.main_list_item_doctortitle_textview);
			holder.doctorTitle = (TextView)convertView.findViewById(R.id.main_list_item_doctortitle_textview);
			holder.doctorInfoTextView = (TextView)convertView.findViewById(R.id.main_expert_doctor_info_textview);
			if(AppUtil.isNotEmpty(mList.get(position).doctorPicpath)){
				holder.HeaderImageView.setImageUrl(mList.get(position).doctorPicpath);	
			}
			holder.doctorNameTextView.setText(mList.get(position).doctorName);
			holder.doctorTitle.setText(mList.get(position).doctorTitle);
			holder.doctorInfoTextView.setText(mList.get(position).doctorHosptialName);
			
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
		TextView  doctorNameTextView;
		TextView  doctorTitle;
		TextView  doctorInfoTextView;
	} 
}
