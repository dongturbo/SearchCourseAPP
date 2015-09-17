package com.example.main;

import com.example.adatper.DayNowAdapter;
import com.example.schedule.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Classroom extends Activity {

	RelativeLayout topLayout;
	RelativeLayout bottomLayout;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		topLayout=(RelativeLayout)findViewById(R.id.week_course_rlyt_day_layout);
		bottomLayout=(RelativeLayout)findViewById(R.id.course_top);
		
		
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		///int height = dm.heightPixels;
		// 平均宽度
		int aveWidth = (width - 30) / 7;
		//int aveHeight = (height - 30) / 10;
		
		String[] mWeek = { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
		DayNowAdapter adapter1 = new DayNowAdapter(Classroom.this, mWeek,
				R.layout.item_gridview, R.id.week_day, 1, (int) aveWidth);
		
		TextView tx = new TextView(Classroom.this);
		tx.setClickable(false);
		tx.setFocusable(false);
		tx.setFocusableInTouchMode(false);
		tx.setSelected(false);
		tx.setId(1);
		tx.setText("dong");
		RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
				aveWidth - 1, RelativeLayout.LayoutParams.MATCH_PARENT);
		tx.setBackgroundResource(R.color.light_grey);
		RelativeLayout.LayoutParams gv = new RelativeLayout.LayoutParams(
				width - 44,RelativeLayout.LayoutParams.MATCH_PARENT);
		
		
		gv.addRule(RelativeLayout.RIGHT_OF,1);
		tx.setGravity(Gravity.CENTER);
		rp.width = 44;
		tx.setLayoutParams(rp);
		topLayout.addView(tx);
		GridView gv1=new GridView(Classroom.this);
		gv1.setBackgroundResource(R.color.grey);
		gv1.setAdapter(adapter1);
		gv1.setNumColumns(7);  
		gv1.setLayoutParams(gv);
		topLayout.addView(gv1);
		
	}
	
}
