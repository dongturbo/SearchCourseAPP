package com.example.adatper;


import com.example.schedule.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DayNowAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private String[] list;  
	private int layoutID; 
	private int ItemIDs;  
	private Context context;
	private int Id;
	private int aveWidth;
	
	
		
	public DayNowAdapter(Context context, String[] list, int layoutID,
			int itemIDs ,int Id,int aveWidth) {
		
		this.inflater = LayoutInflater.from(context); 
		this.list = list;
		this.layoutID = layoutID;
		ItemIDs = itemIDs;
		this.context = context;
		this.Id=Id;
		this.aveWidth=aveWidth;
	}




	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//final int p=position;			
		convertView = inflater.inflate(layoutID, null);
		TextView textView=(TextView)convertView.findViewById(ItemIDs);
		textView.setText(list[position]);
		textView.setWidth(aveWidth);
		if(position==Id){
			textView.setBackgroundResource(R.color.light_grey);
		}
		
		
		
		return convertView;
	}

}
