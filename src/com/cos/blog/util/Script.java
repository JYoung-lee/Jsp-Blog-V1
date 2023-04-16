package com.cos.blog.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.board.dto.CommonRespDto;

public class Script {
	public static void back(HttpServletResponse response, String msg) {
		PrintWriter out; 
		try {
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+ msg +"');");
			out.println("history.back();");
			out.println("</script>");
			out.flush(); // 버퍼비우기
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void responseData(HttpServletResponse response, String jsonData) {
		
		PrintWriter out; 
		try {
			out = response.getWriter();
			out.print(jsonData);
			out.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
