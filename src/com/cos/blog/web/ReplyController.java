package com.cos.blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.dto.CommonRespDto;
import com.cos.blog.domain.reply.dto.SavaReqDto;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

//http://localhost:8090/blog/reply
@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		ReplyService replyService = new ReplyService();
		//http://localhost:8080/blog/reply?cmd=save
		
		HttpSession session = request.getSession();
		if(cmd.equals("save")) {
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			SavaReqDto dto = gson.fromJson(reqData, SavaReqDto.class);
			int result = replyService.saveReply(dto);
			CommonRespDto<String> commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result);
			String responData = gson.toJson(commonRespDto);
			
			Script.responseData(response, responData);
		}
	}
	
}
