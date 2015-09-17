package com.example.adatper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassBuildingAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private String item[][];  
	private int layoutID; 
	private int ItemIDs;  
	//private Context context;
	
	public ClassBuildingAdapter(Context context, String item[][], 
			int layoutID,  int ItemIDs){
		this.inflater = LayoutInflater.from(context);           
		this.item = item;           
		this.layoutID = layoutID;                       
		this.ItemIDs = ItemIDs;  
		//this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item[0].length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(layoutID, null);
		TextView tx1=(TextView)convertView.findViewById(ItemIDs);
		tx1.setText(item[0][position]);
		return convertView;
	}

}
