package com.dong.main;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dong.util.DBUtil;



public class update {
	
	private String sql;
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Menu> getMenuList(){
		//数据库查询语句
		//String sql="select course_id,课程名称,课程号,主讲教师姓名,节次,星期,周次分布,教室 from course_table";
		DBUtil util=new DBUtil();
		//获得连接		
		Connection conn=util.openConnection();
		try{
			Statement pstmt=conn.createStatement();
			ResultSet rs=pstmt.executeQuery(sql);
			List<Menu> list=new ArrayList<Menu>();
			//rs.next();
			//获取数据库课程表信息
			while(rs.next()){
				int courseId=rs.getInt(1);
				String courseName=rs.getString(2);
				String courseNum=rs.getString(3);
				String teacherName=rs.getString(4);
				String courseTime=rs.getString(5);
				int week=rs.getInt(6);
				String weekNum=rs.getString(7);
				String coursePlace=rs.getString(8);
								
				Menu m=new Menu();
				
				m.setCourseId(courseId);
				m.setCourseName(courseName);
				m.setCourseNum(courseNum);
				m.setCoursePlace(coursePlace);
				m.setCourseTime(courseTime);
				m.setTeacherName(teacherName);
				m.setWeek(week);
				m.setWeekNum(weekNum);	
				
				
				list.add(m);				
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			if(conn!=null){
				util.closeConn(conn);
			}
			
		}
		return null;
	}
}
