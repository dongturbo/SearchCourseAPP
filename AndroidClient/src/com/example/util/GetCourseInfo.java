package com.example.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;



import android.util.Xml;

import com.example.data.CourseInfo;

public class GetCourseInfo { 
	public static List<CourseInfo> getCourseInfo(InputStream inStream)throws Exception{
		XmlPullParser parser=Xml.newPullParser();
		//³õÊ¼»¯½âÎöÆ÷
		parser.setInput(inStream,"utf-8");
		int type=parser.getEventType();
		List<CourseInfo> courseInfos=null;
		CourseInfo courseInfo=null;
		while(type!=XmlPullParser.END_DOCUMENT){
			switch(type){
			case XmlPullParser.START_TAG:
				if("courselist".equals(parser.getName())){
					courseInfos=new ArrayList<CourseInfo>();
				}else if("course".equals(parser.getName())){
					courseInfo=new CourseInfo();
				}else if("courseId".equals(parser.getName())){
					String temp=parser.nextText();
					int courseId=Integer.parseInt(temp);
					courseInfo.setCourseId(courseId);
				}else if("courseName".equals(parser.getName())){
					String courseName=parser.nextText();
					courseInfo.setCourseName(courseName);
				}else if("courseNum".equals(parser.getName())){
					String courseNum=parser.nextText();
					courseInfo.setCourseNum(courseNum);
				}else if("teacherName".equals(parser.getName())){
					String teacherName=parser.nextText();
					courseInfo.setTeacherName(teacherName);
				}else if("courseTime".equals(parser.getName())){
					String courseTime=parser.nextText();
					courseInfo.setCourseTime(courseTime);
				}else if("week".equals(parser.getName())){
					String  temp=parser.nextText();
					int week=Integer.parseInt(temp);
					courseInfo.setWeek(week);
				}else if("weekNum".equals(parser.getName())){
					String weekNum=parser.nextText();
					courseInfo.setWeekNum(weekNum);	
				}else if("coursePlace".equals(parser.getName())){
					String coursePlace=parser.nextText();
					courseInfo.setCoursePlace(coursePlace);
				}
				break;
			case XmlPullParser.END_TAG:
				if("course".equals(parser.getName())){
					courseInfos.add(courseInfo);
					courseInfo=null;
				}
				break;
			}
			type=parser.next();
		}
		inStream.close();
		return courseInfos;	
	}
	
} 
