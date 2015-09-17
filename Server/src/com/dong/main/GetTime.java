package com.dong.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
//获取当天时间
public class GetTime {
	
	
	public static String timer(){
		String str="";
		int d,c,i;
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("HH");
	    i=calendar.get(Calendar.DAY_OF_WEEK)-1;
	    if(calendar.get(Calendar.DAY_OF_WEEK)==1){
	    	i=7;
	    }
	    c =Integer.parseInt( df.format(calendar.getTime()));	    
	    df = new SimpleDateFormat("m");
	    d = Integer.parseInt(df.format(calendar.getTime()));
	    if(c>0&&c<12){
	    	System.out.println("上午");
	    	str="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+i+"' and (`节次` like '1-2%' or `节次` like '1-4%' or `节次` like '3-4%')";
	    }else if(c>=12&&c<18||c==18&&d<30){
	    	System.out.println("下午");
	    	str="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+i+"' and (`节次` like '5-6%' or `节次` like '5-7%' or `节次` like '5-8%' or `节次` like '7-8%')";
	    }else {
	    	System.out.println("晚上");
	    	str="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+i+"' and (`节次` like '9-10%' or `节次` like '9-11%' or `节次` like '9-12%')";
	    }
	    System.out.println(str);
	    return str;
	}
	public static String getWeek(){
		String str="";
		Calendar calendar = Calendar.getInstance();
		int i=calendar.get(Calendar.DAY_OF_WEEK)-1;
	    if(calendar.get(Calendar.DAY_OF_WEEK)==1){
	    	i=7;
	    }
	    str="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+i+"'";
		return str;
	}
}
