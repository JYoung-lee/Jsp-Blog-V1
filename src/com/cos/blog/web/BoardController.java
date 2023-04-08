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
			request.setAttribute("boards", boards);
			
			// 풀이 
			// 계산 (전체 데이터랑 한페이지 갯수 - 총 페이지 나와야되는 계산) 3page 라면 page의 맥스값은 2
			// page == 2가 되는 순간 isEnd = true
			// request.setAttribute("isEnd",true); 값을 가져가면된다.
			
			//1 2 3..... 총 페이징 하는 방법 : 총 데이터 갯수(total) / 보여주고싶은 갯수 (페이지 글수) = 값 (올림)  
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
			
		}else if(cmd.equals("detail")) {
			String id = request.getParameter("id");
			
			System.out.println(id);
			System.out.println("여기들어와?");
			
		}
		
	}
	
}
