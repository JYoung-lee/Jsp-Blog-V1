<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/blog/user?cmd=join" method="post" onsubmit="return valid()">
	  <div class="d-flex justify-content-end">
	  	<button type="button" class="btn btn-info" onclick="idCheck()"> 중복체크 </button>
	  </div>
	  <div class="form-group">
	    <input type="text" name="username" class="form-control" placeholder="Enter Username" required/>
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
	let isChecking = true;

	function valid(){
		return false;
	}
	
	function idCheck(){
		//DB에서 확인해서 정상이면 isChecking = true;
		$.ajax(function(){
			
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