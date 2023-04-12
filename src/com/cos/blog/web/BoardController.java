package com.cos.blog.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;

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
			
			request.setAttribute("dto", detailArticle);
			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response); 
		}
		
		
		
		
		
		
	}
}
