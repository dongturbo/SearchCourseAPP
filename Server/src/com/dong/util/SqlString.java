package com.dong.util;

import com.dong.main.GetTime;

public class SqlString {
	private String sql;	
	public String getSql(String temp,String temp1){		
		if(temp1==null){
			char c=temp.charAt(0);
			if(c>='0'&&c<='9'){
				sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `教室` like '%"+temp+"%'";
			}else if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
				sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `课程号` like '%"+temp+"%'";
			}else{
				sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where concat(`课程名称`,`主讲教师姓名`,`教室`) like  '%"+temp+"%'"; 
			}
		}else{
			if(temp1.length()<=3){
				char a=temp1.charAt(0);
				String b=temp1.subSequence(1,temp1.length()).toString();
				//获取实时时间来查询课程
				if(a=='8'){
					System.out.println("dongbo");
					sql=GetTime.timer()+" and `教室` like '"+temp+"%' and  `教室`  not like '机房%'";
					return sql;
				}else if(a=='9'){
					sql=GetTime.getWeek()+" and `教室` like '"+temp+"%' and  `教室`  not like '机房%'";
					return sql;
				}
				
				
				if(b.equals("1")||b.equals("2")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '1-2%' or `节次` like '1-4%' )";
				}else if(b.equals("3")||b.equals("4")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '3-4%' or `节次` like '1-4%' )";
				}else if(b.equals("5")||b.equals("6")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '5-6%' or `节次` like '5-8%'or `节次` like '5-7%' )";
				}else if(b.equals("7")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '7-8%' or `节次` like '5-8%'or `节次` like '5-7%' )";
				}else if(b.equals("8")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '7-8%' or `节次` like '5-8%' )";
				}else if(b.equals("9")||b.equals("10")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and (`节次` like '9-10%' or `节次` like '9-11%' )";
				}else if(b.equals("11")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and `节次` like '9-11%' )";
				}else if(b.equals("12")){
					sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table where `星期` ='"+a+"' and `节次` like '9-12%' )";
				}
			}
		}
		return sql;
	}
}
