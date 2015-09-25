package com.wx.doctor.adapter;

import java.util.List;

import com.common.image.SmartImageView;
import com.example.wxdoctor.R;
import com.wx.doctor.model.Baby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BabyListAdapter extends BaseAdapter {
	
	public LayoutInflater inflater;
	public Context mContext;
	public List<Baby> mList;
	
	
	public BabyListAdapter(Context context,List<Baby> list){
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
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.main_list_item, null);
			holder = new ViewHolder();
			holder.HeaderImageView = (SmartImageView)convertView.findViewById(R.id.main_list_item_header_imageview);
			holder.babyNameTextView = (TextView)convertView.findViewById(R.id.main_list_item_header_textview);
			holder.babyInfoTextView = (TextView)convertView.findViewById(R.id.main_list_item_babyinfo_textview);
			holder.babyAgeTextView = (TextView)convertView.findViewById(R.id.main_list_item_age_textview);
			
			holder.weiguanlianImageView = (ImageView)convertView.findViewById(R.id.main_list_item_weiguanlian);
			holder.yiguanlianImageView = (ImageView)convertView.findViewById(R.id.main_list_item_yiguanlian);
			holder.weiguanlianImageView.setVisibility(View.GONE);
			
			holder.HeaderImageView.setImageUrl(mList.get(position).picpath);
			holder.babyNameTextView.setText(mList.get(position).realname);
			holder.babyInfoTextView.setText(mList.get(position).doctorName+"("+ mList.get(position).phoneNumber+")");
			//holder.babyAgeTextView.setText(mList.get(position).);
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
		ImageView yiguanlianImageView;
		ImageView weiguanlianImageView;
		TextView  babyNameTextView;
		TextView  babyInfoTextView;
		TextView  babyAgeTextView;
		TextView  phoneNumberTextView;
	}

}
