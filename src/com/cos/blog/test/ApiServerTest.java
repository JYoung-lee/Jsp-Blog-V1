package com.cos.blog.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class ApiServerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ApiServerTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mime = request.getContentType();
		// System.out.println(mime); // MIME TYPE 확인
		request.setCharacterEncoding("UTF-8");
		
		if(mime.equals("application/json")) {
			BufferedReader br = request.getReader();
			String input;
			StringBuffer buffer = new StringBuffer();
			while((input = br.readLine()) != null) {
				buffer.append(input);
			}
			System.out.println(buffer.toString());
		}else {
			String food = request.getParameter("food");
			String method = request.getParameter("method");
			System.out.println(food);
			System.out.println(method);
		}
		
		//요청시 = Content-Type 중요 x
		//응답시 = Content-Type을 알려줘야 서버에서 정확하게 읽고 실행한
		//정상저리시 DB에 inset하고 끝
	
		// PrintWriter out = response.getWriter();
		// out.flush();
	}

}
