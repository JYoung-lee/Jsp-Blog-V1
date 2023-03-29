<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<button onclick="ajax1()"> ajax Version 1 </button><p id="ajax1"></p> <br/><br/>
	<button onclick="ajax2()"> ajax Version 2 </button><p id="ajax2"></p> <br/><br/>
	<button onclick="ajax3()"> ajax Version 3 </button><p id="ajax3"></p>
<script>
	// Controller Address
	const address = "http://localhost:8090/blog/ajax";
	
	//ajax방법 1 - JavaScript XML객체
	function ajax1(){
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {	// 콜백
			let result = this.responseText;
			if( result === "1"){
				document.getElementById("ajax1").innerHTML = "ajax 성공";	 
			}else {
				document.getElementById("ajax1").innerHTML = "ajax 실패";
			}
		  }
		xhttp.open("GET", address, true); // 컨트롤러 
		xhttp.send();
	}

	// ajax방법 2 - Jquery .done()
	function ajax2(){
		$.ajax(address).done(function(result){ // 방향 Controller > done 콜백
			if(result === "1"){
				document.getElementById("ajax2").innerHTML = "ajax 성공";	 
			}else {
				document.getElementById("ajax2").innerHTML = "ajax 실패";
			}
		});
	}
	
	// ajax방법 3 - JavaScript .fetch()
	function ajax3(){
		fetch(address).then(function(result){ // 방향 Controller > then 콜백 
			return result.text();
		}).then(function(result){
			//콜백 이후 영역
			if(result === "1"){
				document.getElementById("ajax3").innerHTML = "ajax 성공";	 
			}else {
				document.getElementById("ajax3").innerHTML = "ajax 실패";
			}
		});
			
		
	}
</script>	
	
</body>
</html>