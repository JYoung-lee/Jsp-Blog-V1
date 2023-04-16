
function replySave(userId, boardId){
	let data = {
		userId: userId, 
		boardId: boardId,	
		content: $('#content').val()
	};
	
	$.ajax({
		url: "/blog/reply?cmd=save",
		type: "post",
		contentType:"application/json; charset=utf-8",
		data:JSON.stringify(data),
		dataTye: "json"
	}).done(function(result){
		var result = JSON.parse(result);
		if(result.statusCode == 1){
			addReply(result.data);	
		}else{
			
		}
		
		
	});							
}

//댓글 추가
function addReply(data){
	$('#reply__list').prepend(`<li id='reply-${data.id}' class='media'>
							 		<div class='media-body'>
							 			<strong class='text-primary'>${data.userId}</strong>
							 			<p>${data.content}</p>	
							 		</div>	
							 		<div class='m-2'> <i onclick='deleteReply(${data.id})' class='material-icons'> delete</i> </div>
						 		</li>`);
	$("#content").val("");

}

//댓글 삭제 
function deleteReply(boardData){
	// 세션의 유저 id와 reply의 userId를 비교하여 삭제
	alert(boardData);
	
}

//게시판 삭제
function deleteById(boardId){
	//요청과 응답을 json
	var data = {
		boardId: boardId	
	}
	
	$.ajax({
		type:"post",
		url:"/blog/board?cmd=delete",
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataTye:"json"
	}).done(function(result){
		var result = JSON.parse(result); 
		
		if(result.status == "ok"){
			location.href="index.jsp";
		}else {
			alert("삭제에 실패하였습니다.");
		}
	});
}
