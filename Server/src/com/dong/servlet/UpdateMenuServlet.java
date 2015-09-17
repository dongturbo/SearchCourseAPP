package com.dong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.main.Menu;
import com.dong.main.update;
import com.dong.util.SqlString;

public class UpdateMenuServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;  
    // 构造方法  
    public UpdateMenuServlet() {  
        super();  
    }  
    // 销毁方法  
    @Override
	public void destroy() {  
        super.destroy();  
    }  
    
 // 响应Get请求  
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	    	
    	String sql=request.getParameter("key");
    	String sql1=request.getParameter("key1");
    	
        response.setContentType("text/xml");  
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();  
        // 实例化  
        update dao = new update();
        dao.setSql(new SqlString().getSql(sql,sql1));
        // 获取课程信息  
        List<Menu> list = dao.getMenuList();  
          
        // 拼XML格式数据  
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
        // 根节点  
        out.println("<courselist>");  
            for (int i = 0; i <list.size(); i++) {  
                Menu m = list.get(i);  
                out.println("<course>");  
                    // 课程id  
                    out.print("<courseId>");  
                        out.print(m.getCourseId());  
                    out.println("</courseId>");  
                    // 课程名称 
                    out.print("<courseName>");  
                        out.print(m.getCourseName());  
                    out.println("</courseName>");  
                    // 课程号 
                    out.print("<courseNum>");  
                        out.print(m.getCourseNum());  
                    out.println("</courseNum>");  
                    // 老师 
                    out.print("<teacherName>");  
                        out.print(m.getTeacherName());  
                    out.println("</teacherName>");  
                    // 哪几节课 
                    out.print("<courseTime>");  
                        out.print(m.getCourseTime());  
                    out.println("</courseTime>");  
                    // 周几上课 
                    out.print("<week>");  
                        out.print(m.getWeek());  
                    out.println("</week>");  
                    //哪几周上课 
                    out.print("<weekNum>");  
                        out.print(m.getWeekNum());  
                    out.println("</weekNum>"); 
                    //上课地点
                    out.print("<coursePlace>");  
                    out.print(m.getCoursePlace());  
                    out.println("</coursePlace>"); 
                out.println("</course>");  
            }  
        out.println("</courselist>");  
        out.flush();  
        out.close();  
    }  
    // 响应Post请求  
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request,response);  
    }  
    // 初始化方法  
    @Override
	public void init() throws ServletException {  
    }  
}  

