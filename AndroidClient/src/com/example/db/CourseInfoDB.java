package com.example.db;


import java.util.ArrayList;
import java.util.List;


import com.example.data.CourseInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CourseInfoDB {
	public static final String TAG = "courseInfo_DB";
	/**数据库名称*/
	private Context mContext;
	/** 数据库表名 */
	private static final String TABLE_NAME = "course_info";
	/** 字段（id） */
	private static final String COLUM_COURSE_ID = "courseId";
	/** 字段（课程名） */
	private static final String COLUM_COURSE_NAME = "courseName";
	/** 字段（老师） */
	private static final String COLUM_COURSE_TEACHER = "teacherName";
	/** 字段（上课地点） */
	private static final String COLUM_COURSE_ADDRESS = "coursePlace";
	/** 字段（上课时间（周几））*/
	private static final String COLUM_COURSE_WEEKDAY = "week";
	/** 字段（上课周数（开始））*/
	private static final String COLUM_COURSE_TIME = "courseTime";
	/** 字段（上课周数）*/
	private static final String COLUM_COURSE__WEEK = "weekNum";
	/** 字段（课程序号）*/
	private static final String COLUM_COURSE_NUMBER="courseNum";
	
	
	/** 建表语句 */
	private static final String TABLE_CREATE_SQL = "create table if not exists "
			+ TABLE_NAME + " ("
			+ COLUM_COURSE_ID +" INTEGER,"+" INTEGER PRIMARY KEY,"
			+ COLUM_COURSE_NAME + " VARCHAR(50),"
			+ COLUM_COURSE_TEACHER + " VARCHAR(50),"
			+ COLUM_COURSE_ADDRESS + " VARCHAR(50),"
			+ COLUM_COURSE_WEEKDAY + " INTEGER,"
			+ COLUM_COURSE_TIME + " VARCHAR(50),"
			+ COLUM_COURSE_NUMBER + " VARCHAR(50),"
			+ COLUM_COURSE__WEEK + " VARCHAR(50))";
	
	
	private DBHelper dbHelper = null;
	private SQLiteDatabase mSQLiteDatabase;
	public CourseInfoDB(Context mContext) {
		super();
		this.mContext = mContext;
		open();
	}
	
	/**
	 * 打开数据库
	 * @throws SQLException
	 */
	public void open() throws SQLException{
		
		dbHelper = new DBHelper(mContext, TABLE_CREATE_SQL);
		mSQLiteDatabase = dbHelper.getWritableDatabase();
	}
	/**
	 * 关闭数据库
	 */
	public void close(){
		
		dbHelper.close();
	}
	
	
	/**
	 * 插入课程信息
	 * @param c
	 * @return
	 */
	public boolean insertCourse(CourseInfo c){
		
		ContentValues values = new ContentValues();
		values.put(COLUM_COURSE_ID, c.getCourseId());
		values.put(COLUM_COURSE_NAME , c.getCourseName());
		values.put(COLUM_COURSE_TEACHER, c.getTeacherName());
		values.put(COLUM_COURSE_ADDRESS, c.getCoursePlace());
		values.put(COLUM_COURSE_WEEKDAY, c.getWeek());
		values.put(COLUM_COURSE_TIME, c.getCourseTime());
		values.put(COLUM_COURSE__WEEK, c.getWeekNum());
		values.put(COLUM_COURSE_NUMBER, c.getCourseNum());
		return mSQLiteDatabase.insert(TABLE_NAME, COLUM_COURSE_ID, values) > 0 ? true : false;
	}
	
	
	
	
	
	/*查询课程信息的最大ID**/
	
	public int queryMaxID(){
		String sql="select MAX("+COLUM_COURSE_ID+") AS maxId from "+TABLE_NAME;
		
		Cursor cursor =mSQLiteDatabase.rawQuery(sql, null);
		int maxId=0;
		if(cursor.moveToFirst()){
			maxId= cursor.getInt(0);
		}
		cursor.close();
		return maxId;
	}
	
	
	
	/**查询语句*/
	public List<CourseInfo> queryAll(){
		List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
		
		Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, new String[]{COLUM_COURSE_ID,
				COLUM_COURSE_NAME, COLUM_COURSE_TEACHER,
				COLUM_COURSE_ADDRESS, COLUM_COURSE_WEEKDAY,
				COLUM_COURSE_TIME, COLUM_COURSE__WEEK,
				COLUM_COURSE_NUMBER},null, null, null, null, null);

		if(cursor != null)
		{
			while(cursor.moveToNext())
			{
				CourseInfo c = new CourseInfo();
				c.setCourseId(cursor.getInt(0));
				c.setCourseName(cursor.getString(1));
				c.setTeacherName(cursor.getString(2));
				c.setCoursePlace(cursor.getString(3));
				c.setWeek(cursor.getInt(4));
				c.setCourseTime(cursor.getString(5));
				c.setWeekNum(cursor.getString(6));
				c.setCourseNum(cursor.getString(7));
				courseInfos.add(c);
				Log.i("db_query", "课程名称：" + c.getCourseName());
			}
		}else{
			return null;
		}
		
		return courseInfos;
	}
	
	//删除课程
	public boolean delete(int courseID){
		
		String temp=courseID+"";
		return mSQLiteDatabase.delete(TABLE_NAME,COLUM_COURSE_ID+"="+temp,null)>0 ? true:false;
		
	}
}
