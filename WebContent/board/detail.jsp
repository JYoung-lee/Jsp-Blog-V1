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

<script>
	function deleteById(boardId){
		//요청과 응답을 json
		let data = {
			boardId: boardId	
		}
		
		$.ajax({
			type:"post",
			url:"/blog/board?cmd=delete",
			data:JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataTye:"json"
		}).done(function(result){
			let result = JSON.parse(result);
			
			if(result.status == "ok"){
				location.href="index.jsp";
			}else {
				alert("삭제에 실패하였습니다.");
			}
		});
	}
	
</script>

</body>
</html>
 
