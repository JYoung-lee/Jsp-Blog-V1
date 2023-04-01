<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/blog/user?cmd=join" method="post" onsubmit="return valid()">
	  <div class="d-flex justify-content-end">
	  	<button type="button" class="btn btn-info" onclick="usernameCheck()"> 중복체크 </button>
	  </div>
	  <div class="form-group">
	    <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username" required/>
	  </div>
	  <div class="form-group">
	    <input type="password" name="password" class="form-control" placeholder="Enter Password" required/>
	  </div> 
	  <div class="form-group">
	    <input type="email" name="email" class="form-control" placeholder="Enter Email" required/>
	  </div>	  
	  <div class="d-flex justify-content-end">
	  	<button type="button" class="btn btn-info" onclick="goPopup()"> 주소검색 </button>
	  </div>
	  <div class="form-group">
	    <input type="text" name="address" id="address" class="form-control" placeholder="Enter Adderss" required readonly/>
	  </div>
	  <br/>	  
	  <button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>

<script>
	let isChecking = false;

	function valid(){
		return isChecking;
	}
	
	function usernameCheck(){
		let username = $("#username").val();
		
		//DB에서 확인해서 정상이면 isChecking = true;
		$.ajax({
			type:"POST",							// 요청 타입
			url:"/blog/user?cmd=usernameCheck",	// URL
			data:username,							// 요청 데이터
			contentType:"text/plain; charset=utf-8", //MIME 타입
			dateType:"text"						  //응답받을 데이터 타입을 적으면 자바스크립트 오브젝트로 파싱해줌
		}).done(function(data){ //컨트롤러 연결이후 return 받는다.
			if(data === 'ok'){
				alert("유저네임이 중복되었습니다.");
			}else {
				isChecking = true;
				alert("해당 유저네임을 사용할 수 있습니다.");
			}
		});
		
	};
	
	// 주소검색 팝업open
	function goPopup(){
		var pop = window.open("/blog/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	};
	
	// result 값 넣어주기
	function jusoCallBack(roadFullAddr){
		let addressEI =	document.querySelector("#address");
		addressEI.value = roadFullAddr;
	};
</script>

</body>
</html>