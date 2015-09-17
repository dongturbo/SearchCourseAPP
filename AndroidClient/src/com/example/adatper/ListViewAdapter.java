package com.example.adatper;

import java.util.List;

import com.example.data.CourseInfo;
import com.example.db.CourseInfoDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private List<CourseInfo> list;  
	private int layoutID; 
	private int ItemIDs[];  
	private Context context;
	
	public ListViewAdapter(Context context, List<CourseInfo> list, 
			int layoutID,  int ItemIDs[]) {           
		this.inflater = LayoutInflater.from(context);           
		this.list = list;           
		this.layoutID = layoutID;                       
		this.ItemIDs = ItemIDs;  
		this.context=context;
		} 
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView,ViewGroup parent){
		final int p=position;			
		convertView = inflater.inflate(layoutID, null);
		
		TextView tx1=(TextView)convertView.findViewById(ItemIDs[0]);
		TextView tx2=(TextView)convertView.findViewById(ItemIDs[1]);
		TextView tx3=(TextView)convertView.findViewById(ItemIDs[2]);
		TextView tx4=(TextView)convertView.findViewById(ItemIDs[3]);
		TextView tx5=(TextView)convertView.findViewById(ItemIDs[4]);
		tx1.setText(list.get(position).getCourseName());
		tx2.setText(list.get(position).getTeacherName());
		tx3.setText(list.get(position).getCoursePlace());
		tx4.setText(list.get(position).getWeekString()+list.get(position).getCourseTime());
		tx5.setText(list.get(position).getWeekNum());
		Button addCourse=(Button)convertView.findViewById(ItemIDs[5]);
		addCourse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CourseInfoDB courseDB=new CourseInfoDB(context);
				
				if(courseDB.insertCourse(list.get(p))){
					Toast toast=Toast.makeText(context, "Ìí¼Ó³É¹¦",Toast.LENGTH_SHORT);
					toast.show();
				}
				courseDB.close();
				
				
			}
		});
		
        
		return convertView;
	}
	

}


