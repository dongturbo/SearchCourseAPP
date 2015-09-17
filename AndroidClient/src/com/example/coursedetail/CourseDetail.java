package com.example.coursedetail;

import com.example.data.CourseInfo;
import com.example.db.CourseInfoDB;
import com.example.dialog.ConfirmDialog;
import com.example.schedule.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CourseDetail extends Activity {
	TextView courseName;
	TextView coursePlace;
	TextView teacherName;
	TextView courseTime;
	TextView weekNum;
	ImageView back;
	TextView title;
	TextView editCourse;
	RelativeLayout titleRight;
	TextView deleteCourse;
	static final int DIALOG_PAUSED_ID = 0;
	CourseInfo courseinfo;
	public boolean bool=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_course);
		courseName = (TextView) findViewById(R.id.course_info_txv_courseName);
		coursePlace = (TextView) findViewById(R.id.course_info_txv_classroom);
		teacherName = (TextView) findViewById(R.id.course_info_txv_teacherName);
		courseTime = (TextView) findViewById(R.id.course_info_txv_section);
		weekNum = (TextView) findViewById(R.id.course_info_txv_week);
		back = (ImageView) findViewById(R.id.title_imgv_left_icon);
		title = (TextView) findViewById(R.id.title_txv_title);
		deleteCourse = (TextView) findViewById(R.id.setting_txv_logout_tip);

		titleRight = (RelativeLayout) findViewById(R.id.title_rlyt_right);
		titleRight.setVisibility(0);
		editCourse = (TextView) findViewById(R.id.title_txv_right_text);
		editCourse.setText("编辑信息");
		Intent intent = this.getIntent();

		courseinfo = (CourseInfo) intent.getSerializableExtra("courseInfo");

		title.setText(courseinfo.getCourseName());
		title.setSingleLine();
		title.setEllipsize(TextUtils.TruncateAt.valueOf("END"));

		courseName.setText(courseinfo.getCourseName());
		coursePlace.setText(courseinfo.getCoursePlace());
		teacherName.setText(courseinfo.getTeacherName());
		courseTime.setText(courseinfo.getWeekString()
				+ courseinfo.getCourseTime());
		weekNum.setText(courseinfo.getWeekNum());

		
		
		
		
		
		//课成编辑
		editCourse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();

				intent1.setClass(CourseDetail.this, EditCourse.class);
				intent1.putExtra("courseInfo", courseinfo);
				startActivity(intent1);

			}
		});
		
		
		
		
		
		
		
		// 设置删除课程按钮
		deleteCourse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ConfirmDialog dlg=new ConfirmDialog(CourseDetail.this,R.style.MyDialog);
				dlg.show();
				dlg.setNegativeButton("取消", new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg.cancel();
					}
				});
				dlg.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CourseInfoDB courseDB = new CourseInfoDB(CourseDetail.this);
						if (courseDB.delete(courseinfo.getCourseId())) {
							Context context = getApplicationContext();
							Toast toast = Toast.makeText(context, "删除成功",
									Toast.LENGTH_SHORT);
							toast.show();
							CourseDetail.this.finish();
						}
					}
				});
				
				
			}
		});

		// 设置返回按钮
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CourseDetail.this.finish();

			}
		});
		
		
	}

	
	
}
