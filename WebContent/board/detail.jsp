<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">

	<c:if test="${sessionScope.principal.id == dto.userid }">
		<a href="/blog/board?cmd=updateForm&id=${dto.id}" class="btn btn-warning">수정</a>
		<button onClick="deleteById(${dto.id})" class="btn btn-danger">삭제</button>
	</c:if>

	<br />
	<br />
	<h6 class="m-2">
		작성자: <i>${dto.username}</i> 조회수: <i>${dto.readcount}</i>
	</h6>
	<h3 class="m-2">
		<b>${dto.title}</b>
	</h3>
	<hr />
	<div class="form-group">
		<div class="m-2">${dto.content}</div>
	</div>
	<hr />
	<div class="row bootstrap snippets" style="display: grid; padding-left:15px;">
		<div class="col-mnd-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
							<textarea id="content" id="reply__write__form" class="form-control" placeholder="write a comment.." rows="2"></textarea>
							<br />
							<button onclick="replySave(${sessionScope.principal.id}, ${dto.id})" class="btn btn-primary full-right"> 댓글쓰기 </button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작 -->
						<ul id="reply__list" class="media-list">
							<!-- 댓글 아이템 -->
							<c:forEach var="reply" items="${replys}">
								<li id="reply-${reply.id}" class="media">
									<div class="media-body">
										<strong class="text-primary">${reply.userId}</strong>
										<p>
										 	${reply.content}	
										</p>
									</div>
									<div class="m-2">
										<c:if test="${sessionScope.principal.id == reply.userId}">
											<i onclick="deleteReply(${reply.id})" class="material-icons">delete</i>
										</c:if>
									</div>
								</li>
							</c:forEach>
						</ul>
						<!-- 댓글 리스트 끝 -->
					</div>				
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/blog/js/boardDetail.js" type="text/javascript"> </script>

</body>
</html>
 
