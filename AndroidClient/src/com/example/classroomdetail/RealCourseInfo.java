package com.example.classroomdetail;

import java.util.ArrayList;
import java.util.List;

import com.example.adatper.RealCourseInfoAdapter;
import com.example.data.CourseInfo;
import com.example.schedule.R;
import com.example.util.CheckedWifi;
import com.example.util.GetCourseInfo;
import com.example.util.GetStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RealCourseInfo extends Activity {

	ListView listView;
	TextView title;
	ImageView imageView;
	EditText ediText;
	String temp;//搜索关键字
	
	//当前周数
	int curWeek;
	List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.real_coures_info);
		title = (TextView) findViewById(R.id.title_txv_title);
		listView = (ListView) findViewById(R.id.course_Info);
		imageView=(ImageView) findViewById(R.id.title_imgv_left_icon);
		ediText=(EditText)findViewById(R.id.select_course_edtx_course_name);
		ediText.clearFocus();
		Intent intent = getIntent();
		title.setText(intent.getStringExtra("LongName"));
		temp=intent.getStringExtra("ShortName");

		
		final SharedPreferences courseSettings = getSharedPreferences(
				"course_setting", Activity.MODE_PRIVATE);
		//获取当前学期
		String currentWeek=courseSettings.getString("currentWeek", "1");
		curWeek=Integer.parseInt(currentWeek);
		
		//查找课程的函数
		getInfo(temp, "8",curWeek);
		
		
		
		
		// 返回按钮监听
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RealCourseInfo.this.finish();

			}
		});

	}
	
	public void getInfo(String temp,String temp1,int curWeek) {

		// 服务器地址
		try {

			if (CheckedWifi.CheckNetworkState(RealCourseInfo.this)) {
				courseInfos = GetCourseInfo.getCourseInfo(new GetStream()
						.getStream(temp,temp1));
				
				//判断当前周是否已没有课
				List<CourseInfo> courseinfos =new ArrayList<CourseInfo>();
				for(int i=0;i<courseInfos.size();i++){
					
					String a=courseInfos.get(i).getWeekNum().subSequence(0, 1).toString();
					String b=courseInfos.get(i).getWeekNum().subSequence(2, 3).toString();
					char e=courseInfos.get(i).getWeekNum().charAt(3);
					if(e>='0'&&e<='9'){
						b=b+e;
					}
					int c=Integer.parseInt(a);
					int d=Integer.parseInt(b);
					if(curWeek>d||curWeek<c){						
						courseinfos.add(courseInfos.get(i));
					}
				
				}
				courseInfos.removeAll(courseinfos);
				RealCourseInfoAdapter adapter = new RealCourseInfoAdapter(
						RealCourseInfo.this, courseInfos, R.layout.real_course_item,
						new int[] { R.id.select_course_txv_course_name,
								R.id.select_course_txv_teacherName,
								R.id.select_course_txv_classroom,
								R.id.select_course_txv_section,
								R.id.select_course_txv_week });
				listView.setAdapter(adapter);
			} else {
				Toast toast = Toast.makeText(RealCourseInfo.this, "当前无WIFI连接",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
