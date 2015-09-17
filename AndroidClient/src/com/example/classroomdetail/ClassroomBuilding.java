package com.example.classroomdetail;

import com.example.adatper.ClassBuildingAdapter;
import com.example.schedule.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ClassroomBuilding extends Activity {

	ListView listView;
	ImageView imageView;
	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classroom_building);
		imageView = (ImageView) findViewById(R.id.title_imgv_left_icon);
		title = (TextView) findViewById(R.id.title_txv_title);
		title.setText("实时查询");
		listView = (ListView) findViewById(R.id.classroom_building_Info);
		final String[][] str = new String[2][];
		str[0] = new String[7];
		str[1] = new String[7];
		str[0][0] = "第一教学楼";
		str[1][0] = "一教";
		str[0][1] = "第二教学楼";
		str[1][1] = "二教";
		str[0][2] = "第三教学楼";
		str[1][2] = "三教";
		str[0][3] = "第四教学楼";
		str[1][3] = "四教";
		str[0][4] = "综合教学楼";
		str[1][4] = "阶";
		str[0][5] = "土木教学楼";
		str[1][5] = "土";
		str[0][6] = "机械教学楼";
		str[1][6] = "机";

		// 设置适配器

		// 返回按钮监听
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClassroomBuilding.this.finish();

			}
		});

		ClassBuildingAdapter adapter = new ClassBuildingAdapter(
				ClassroomBuilding.this, str, R.layout.classroom_item,
				R.id.applist_item_text);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			//arg2是item的条目数
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ClassroomBuilding.this, RealCourseInfo.class);
				Bundle bundle=new Bundle();
				bundle.putString("LongName", str[0][arg2]);
				bundle.putString("ShortName", str[1][arg2]);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
	}

}
