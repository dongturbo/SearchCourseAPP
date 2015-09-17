package com.example.coursedetail;


import com.example.data.CourseInfo;
import com.example.db.CourseInfoDB;
import com.example.dialog.CourseTimeWheel;
import com.example.dialog.WeekWheel;
import com.example.schedule.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditCourse extends Activity {
	ImageView back;
	AutoCompleteTextView courseName;
	AutoCompleteTextView coursePlace;
	AutoCompleteTextView teacherName;
	TextView courseTime;
	TextView weekNum;
	TextView title;
	RelativeLayout titleRight;
	TextView done;
	
	CourseInfo courseInfo = new CourseInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_course);
		back = (ImageView) findViewById(R.id.title_imgv_left_icon);
		courseName = (AutoCompleteTextView) findViewById(R.id.edt_course_edt_courseName);
		coursePlace = (AutoCompleteTextView) findViewById(R.id.edit_course_edt_classroom);
		teacherName = (AutoCompleteTextView) findViewById(R.id.edit_course_edt_teacher);
		courseTime = (TextView) findViewById(R.id.edit_course_txv_section);
		weekNum = (TextView) findViewById(R.id.edit_course_txv_week);
		title = (TextView) findViewById(R.id.title_txv_title);
		titleRight = (RelativeLayout) findViewById(R.id.title_rlyt_right);
		titleRight.setVisibility(0);
		title.setText("编辑课程");
		done = (TextView) findViewById(R.id.title_txv_right_text);
		done.setText("完成");

		// 从上一个activity接收数据

		Intent intent = this.getIntent();
		final CourseInfo courseinfo = (CourseInfo) intent
				.getSerializableExtra("courseInfo");
		if (courseinfo != null) {
			courseName.setText(courseinfo.getCourseName());
			coursePlace.setText(courseinfo.getCoursePlace());
			teacherName.setText(courseinfo.getTeacherName());
			courseTime.setText(courseinfo.getWeekString()
					+ courseinfo.getCourseTime());
			weekNum.setText(courseinfo.getWeekNum());
		}

		// final SharedPreferences course_Time =
		// getSharedPreferences("course_Time", Activity.MODE_PRIVATE);
		courseTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int currentIndex = 0;
				String[][] terms = null;
				terms = new String[3][];
				terms[0] = new String[] { "周一", "周二", "周三", "周四", "周五", "周六",
						"周日" };
				terms[1] = new String[] { "第1节", "第2节", "第3节", "第4节", "第5节",
						"第6节", "第7节", "第8节", "第9节", "第10节", "第11节", "第12节" };
				terms[2] = new String[] { "到1节", "到2节", "到3节", "到4节", "到5节",
						"到6节", "到7节", "到8节", "到9节", "到10节", "到11节", "到12节" };

				final CourseTimeWheel wheel = new CourseTimeWheel(
						EditCourse.this, R.style.MyDialog, terms, currentIndex);

				wheel.show();
				wheel.setTitle("请选择上课节次");
				wheel.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (wheel.getItemID(1) > wheel.getItemID(2)) {
							Toast toast = Toast.makeText(EditCourse.this,
									"选择信息有误，需重输入", Toast.LENGTH_SHORT);
							toast.show();
						} else {
							courseInfo.setWeek(wheel.getItemID(0) + 1);
							courseInfo.setCourseTime(wheel.getItemID(1) + 1
									+ "-" + (wheel.getItemID(2) + 1) + "节");
							wheel.dismiss();
							courseTime.setText(courseInfo.getWeekString()
									+ courseInfo.getCourseTime());
						}
					}
				});

			}
		});

		// 设置星期数
		weekNum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 打开wheel后显示的位置
				int currentIndex = 0;

				// num表示一学期最大周数
				int num = 16;
				String[][] terms = new String[2][];
				terms[0] = new String[num];
				terms[1] = new String[num];
				for (int i = 1; i <= num; i++) {
					terms[0][i - 1] = "第" + i + "周";
					terms[1][i - 1] = "到" + i + "周";
				}
				final WeekWheel wheel = new WeekWheel(EditCourse.this,
						R.style.MyDialog, terms, currentIndex);
				wheel.show();
				wheel.setTitle("请选择上课周数");
				wheel.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (wheel.getItemID(0) > wheel.getItemID(1)) {
							Toast toast = Toast.makeText(EditCourse.this,
									"选择信息有误，需重输入", Toast.LENGTH_SHORT);
							toast.show();
						} else {
							courseInfo.setWeekNum(wheel.getItemID(0) + 1 + "-"
									+ (wheel.getItemID(1) + 1) + "周");
							wheel.dismiss();
							weekNum.setText(courseInfo.getWeekNum());
						}
					}
				});
			}
		});

		// 为完成按钮设置监听器
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (courseinfo == null) {

					if (courseName.getText().length()==0) {
						Toast toast = Toast.makeText(EditCourse.this,
								"课程名称不能为空", Toast.LENGTH_SHORT);
						toast.show();
					} else if (coursePlace.getText().length()==0) {
						Toast toast = Toast.makeText(EditCourse.this,
								"教室不能为空", Toast.LENGTH_SHORT);
						toast.show();
					} else if (courseInfo.getCourseTime().length()==0) {
						Toast toast = Toast.makeText(EditCourse.this,
								"上课节数不能为空", Toast.LENGTH_SHORT);
						toast.show();
					}else {
						//插入数据
						
						courseInfo.setCourseName(courseName.getText().toString());
						courseInfo.setTeacherName(teacherName.getText().toString());
						courseInfo.setCoursePlace(coursePlace.getText().toString());
						CourseInfoDB courseDB=new CourseInfoDB(EditCourse.this);
						courseInfo.setCourseId(courseDB.queryMaxID()+1);
						if(courseDB.insertCourse(courseInfo)){
							Toast toast=Toast.makeText(EditCourse.this, "添加成功",Toast.LENGTH_SHORT);
							toast.show();
						}
						courseDB.close();
					}

				} else {
					//更新语句利用的是删除再插入，以后再纠正
					CourseInfoDB courseDB=new CourseInfoDB(EditCourse.this);	
					courseInfo.setCourseName(courseName.getText().toString());
					courseInfo.setTeacherName(teacherName.getText().toString());
					courseInfo.setCoursePlace(coursePlace.getText().toString());
					courseInfo.setWeekNum(weekNum.getText().toString());
					//判断课成的上课时间是否有改动
					String temp=courseinfo.getWeekString()+courseinfo.getCourseTime();
					if(courseTime.getText().toString().equals(temp)){
						courseInfo.setWeek(courseinfo.getWeek());
						courseInfo.setCourseTime(courseinfo.getCourseTime());
					}
					courseInfo.setCourseId(courseinfo.getCourseId());
					if(courseDB.delete(courseinfo.getCourseId())&&courseDB.insertCourse(courseInfo)){
						Toast toast = Toast.makeText(EditCourse.this, "数据修改成功",
								Toast.LENGTH_SHORT);
						toast.show();
					}
					
					
					
					
				}

			}
		});

		// 监听返回按钮
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditCourse.this.finish();

			}
		});
	}

}
