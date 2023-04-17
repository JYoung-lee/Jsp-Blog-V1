package com.cos.blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.dto.DeleteReqDto;
import com.cos.blog.domain.board.dto.DeleteRespDto;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

//http://localhost:8090/blog/board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
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
		BoardService boardService = new BoardService();
		ReplyService replyService = new ReplyService(); 
	
		HttpSession session = request.getSession();
		if(cmd.equals("saveForm")) {
			User principal = (User)session.getAttribute("principal");
			if(principal != null) {
				RequestDispatcher dis = request.getRequestDispatcher("board/saveForm.jsp");
				dis.forward(request, response); 
			}else {
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response); 
			}
		}else if(cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			SaveReqDto dto = new SaveReqDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setUserId(userId);
			
			
			int result = boardService.boardWirte(dto);
			if(result == 1) {// 정상
				response.sendRedirect("index.jsp");
			}else {// 실패
				Script.back(response, "글쓰기 실패");
			}
			
		}else if(cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<Board> boards = boardService.getBoardList(page);
			int articleCount = boardService.getArticleCount();
			int lastPage = (articleCount - 1) / 4;
			//총 페이징 하는 방법 : 총 데이터 갯수(total) -1 / 보여지는 페이지 글수 ->0부터 시작일경우 2/4 = 0, 3/4 = 0, 4/4 = 1, 9/4 = 2 (0page, 1page, 2page)
			
			double currentPosition = (double)page/(lastPage)*100;
			/*
				0page / 3page * 100 = 0;
				1page / 3page * 100 = 33.3
				2page / 3page * 100 = 66.6
				3page / 3page * 100 = 100
			*/
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			request.setAttribute("boards", boards);
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		}else if(cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailRespDto detailArticle = boardService.getDetailArticle(id); //board테이블+user테이블 = 조인된 데이터
			List<Reply> replys = replyService.replyListById(id);
			
			if(detailArticle == null) {
				Script.back(response, "상세보기에 실패하였습니다.");
			}else {
				request.setAttribute("dto", detailArticle);
				request.setAttribute("replys", replys);
				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
				
			}
			
		}else if(cmd.equals("delete")) {	
			BufferedReader br = request.getReader();
			String data = br.readLine();
			
			//1. json data -> 자바 오브젝트로 변환
			Gson gson = new Gson();
			DeleteReqDto dto = gson.fromJson(data, DeleteReqDto.class);
			
			//2. DB에서 id값으로 글 삭제
			int result = boardService.deleteById(dto);
			DeleteRespDto respDto = new DeleteRespDto();
			if(result == 1) {
				respDto.setStatus("ok");
			}else {
				respDto.setStatus("fail");
			}
			
			//3. 응답할 json 데이터 생성
			String respData = gson.toJson(respDto);
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
			
		}else if(cmd.equals("updateForm")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailRespDto dto = boardService.getDetailArticle(id);
			request.setAttribute("dto", dto);
			
			RequestDispatcher dis = request.getRequestDispatcher("board/updateForm.jsp");
			dis.forward(request, response);
			
		}else if(cmd.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			UpdateReqDto dto = new UpdateReqDto();
					
			dto.setTitle(title);
			dto.setContent(content);
			dto.setId(id);
			
			int result = boardService.updateById(dto);
			
			if(result == 1) {
				response.sendRedirect("/blog/board?cmd=detail&id="+id);
			}else {
				Script.back(response, "글 수정에 실패하였습니다.");
			}
			
		}else if(cmd.equals("search")) {
			String keyword = request.getParameter("keyword");
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<Board> boards = boardService.findByKeyword(keyword , page);
			request.setAttribute("boards", boards);
			
			int articleCount = boardService.getArticleCount(keyword);
			int lastPage = (articleCount - 1) / 4;
			double currentPosition = (double)page/(lastPage)*100;
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);
			request.setAttribute("boards", boards);
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
			
		}
		
		
		
		
		
		
	}
}
