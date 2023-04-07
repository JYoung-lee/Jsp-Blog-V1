<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	RequestDispatcher dis =
	request.getRequestDispatcher("board/list.jsp");
	dis.forward(request, response); 
	// 톰캣이 생성하는 request와 response를 재사용한다. 다시 접근하는게 아니라 내부적으로 움직인다.
	// response.sendRedirect()는 다시 Controller를 타서 jsp로 접근하는방식 
%>