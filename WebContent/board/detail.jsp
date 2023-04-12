<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
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
						<textarea id="reply__write__form" class="form-control" placeholder="write a comment.." rows="2"></textarea>
						<br />
						<button onclick="#" class="btn btn-primary full-right"> 댓글쓰기 </button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작 -->
						<ul id="reply-1" class="media-list">
							<!-- 댓글 아이템 -->
							<li id="reply-" class="media">
								<div class="media-body">
									<strong class="text-primary">홍길동</strong>
									<p>
									 	댓글내용	
									</p>
								</div>
								<div class="m-2">
									<i onclick="#" class="material-icons">delete</i>					
								</div>
							</li>
						</ul>
					</div>				
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
 
