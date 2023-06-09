<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
	<div class="m-2">
		<form class="form-inline d-flex justify-content-end" action="/blog/board">
			<input type="hidden" name="cmd" value="search" />
			<input type="hidden" name="page" value="0" />
			<input type="text" name="keyword" class="form-control mr-sm-2" placeholder="Search">			
			<button class="btn btn-primary m-1">검색</button>
		</form>
	</div>
	<div class="progress col-md-12 m-2">
		<div class="progress-bar" style="width: ${currentPosition}%"></div>
	</div>
	<!-- JSTL forEach문, EL을 써서 게시글 뿌리기 -->
	 	<c:forEach var="board" items="${boards}">  
			<div class="card col-md-12 m-2">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<a href="/blog/board?cmd=detail&id=${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>	
		</c:forEach>
	<br />
	<!--  disabled 클릭x-->
<%-- 	<c:choose> -> when, otherwise 사용해도되고 삼항연산자사용해서 넣어줘도 가능하다. --%>
	<ul class="pagination justify-content-center">
		
		
		<c:choose>
			<c:when test="${empty param.keyword }">
				<c:set var="pagePrev" value="/blog/board?cmd=list&page=${param.page-1}"></c:set>
				<c:set var="pageNext" value="/blog/board?cmd=list&page=${param.page+1}"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="pagePrev" value="/blog/board?cmd=list&page=${param.page-1}&keyword=${param.keyword}"></c:set>
				<c:set var="pageNext" value="/blog/board?cmd=list&page=${param.page+1}&keyword=${param.keyword}"></c:set>
			</c:otherwise>
		</c:choose>
		
		<li class="page-item ${param.page == 0 ? 'disabled' : '' }"><a class="page-link" href="${pagePrev}">Previous</a></li>
		
		<c:choose>
			<c:when test="${lastPage == param.page}">
				<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${pageNext}">Next</a></li>	
			</c:otherwise>		
		</c:choose>
	</ul>
	<h2>last : ${lastPage}</h2>
	<h2>param : ${param.page}</h2>
</div>
</body>
</html>
 
