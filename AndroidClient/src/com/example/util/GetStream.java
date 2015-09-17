package com.example.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetStream {
	private String  urlStr="http://192.168.65.158:8080/Stdu_course/UpdateMenuServlet";
	public  InputStream getStream(String temp,String temp1){
		StringBuilder str=new StringBuilder(urlStr);
		try{
			//设置发送的数据，即利用GET方式发送数据到服务器端
			//temp1="11";
			str.append("?key=");
			str.append(URLEncoder.encode(temp, "UTF-8"));
			if(temp1!=null){
				str.append("&key1=");
				str.append(URLEncoder.encode(temp1, "UTF-8"));
			}
			URL url=new URL(str.toString());
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			InputStream in=conn.getInputStream();
			return in;
		}catch (Exception e) {  
            e.printStackTrace();             
        } 
		return null;
	}
}
