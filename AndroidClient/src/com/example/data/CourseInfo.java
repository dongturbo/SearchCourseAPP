package com.example.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CourseInfo implements Serializable{
	private String courseName;  //课程名称
	private int courseId;   	//课程ID 
	private String courseNum;		//课程序号
	private String teacherName;	//教师姓名
	private String courseTime; 		//哪几节上课
	private int week;		//周几上课
	private String  weekNum;  	//哪几周有课，上到多少周
	private String  coursePlace;	//上课地点
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public int getWeek() {	
		return week;
	}
	public String getWeekString(){
		//String temp=null;
		switch(week){
		case 1:
			return "周一";
		case 2:
			return "周二";
		case 3:
			return "周三";
		case 4:
			return "周四";
		case 5:
			return "周五";
		case 6:
			return "周六";
		case 7:
			return "周日";
			default:
				return null;
		}
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(String weekNum) {
		this.weekNum = weekNum;
	}
	public String getCoursePlace() {
		return coursePlace;
	}
	public void setCoursePlace(String coursePlace) {
		this.coursePlace = coursePlace;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		super.toString();
		return "课程名称:"+courseName+",教师姓名"+teacherName+",上课地点"+coursePlace;
	}
	
}
