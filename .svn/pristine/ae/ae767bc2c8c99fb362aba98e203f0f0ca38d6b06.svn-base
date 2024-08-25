<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<style>
#btndiv>a{
color:gray;
margin:30px;
}
body{
background:#d2d9e9;
}
</style>
<script>
$(function(){
	<% 
	    Boolean loginFailed = (Boolean)session.getAttribute("loginfailed");
	    if (loginFailed != null && loginFailed) {
	        session.removeAttribute("loginfailed");
	%>
	    alert('로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.');
	<% 
	    }
	%>
	
	$("#changebtn").click(function(){
		let id=$("#id").val();
		let email1=$("#email1").val();
		let email2=$("#email2").val();
		let email=email1+email2;
		
		$.ajax({
	        type: 'POST',
	        url: '/ajax/findpw.do',
	        data: { id: id, email: email },
	        success: function(response) {
	            if (response.trim() === "성공") {
	                // "성공"인 경우 pwform.do로 페이지 이동
	                window.location.href = "pwform.do?id="+id;
	            } else if (response.trim() === "재입력") {
	                // "재입력"인 경우 alert 표시
	                alert("적절하지 않은 데이터를 입력하셨습니다. 다시 입력해주세요.");
	            } else {
	                // 기타 경우에 대한 처리
	                console.log("기타 응답:", response);
	            }
	        }
		});
	});//end of click()
});

</script>

</head>

<body>
<div class="container">
	<div class="card mx-auto shadow-lg" style="width:60%;margin-top:50px">
		<div class="card-body" style="background:#d2d9e9">
		<br>
			<div>
				<img src="/upload/member/cloud_btn.png" style="width:60%;margin-left:20%">
			</div>
			<br>
			<br>
			<div style="margin-bottom:50px">
				<form action="login.do" method="post">
					<div class="input-group mb-3 input-group-lg mx-auto" style="width:70%">
						<div class="input-group-prepend">
							<span class="input-group-text">&nbsp;ID&nbsp;</span>
						</div>
						<input class="form-control" placeholder="아이디를 입력하시오." 
						name="id" autocomplete="off">
					</div>
					<div class="input-group mb-3 input-group-lg mx-auto" style="width:70%">
						<div class="input-group-prepend">
							<span class="input-group-text">PW</span>
						</div>
						<input class="form-control" placeholder="비밀번호를 입력하시오." 
						name="pw" type="password" autocomplete="off">
					</div>
					<button class="btn mx-auto btn-block btn-lg" 
					style="background:#e9ecef;width:70%">LOGIN</button>
					<br>
					<div id="btndiv" style="margin-left:15%">
						<a href="idform.do">아이디 찾기</a>
						<a data-toggle="modal" data-target="#pwModal">비밀번호 찾기</a>
						<a href="writeform.do">회원가입</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 모달창 -->
	<div class="modal fade" id="pwModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 찾기</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="pwform.do">
				<!-- Modal body -->
				<div class="modal-body">
					  <div class="form-group">
					      <label for="id">ID:</label>
					      <input class="form-control" placeholder="아이디를 입력하시오." id="id"
					      name="id" required autocomplete="off" maxlength="20" pattern="^[a-zA-Z0-9]{4,20}$">
					  </div>
					  <div class="form-group">
					      <label for="email">E-Mail:</label><br>
					      <input class="form-control" name="email" required style="width:50%;display:inline"
					      autocomplete="off" maxlength="50" pattern="^[a-zA-Z0-9]{4,50}$" id="email1">
					      <select class="form-control" id="email2" name="email" style="width:40%;display:inline">
					      	<option value="@musaic.com">@musaic.com</option>
					      	<option value="@naver.com">@naver.com</option>
					      	<option value="@nate.com">@nate.com</option>
					      	<option value="@gmail.com">@gmail.com</option>
					      </select>
					 </div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="changebtn">확인</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				</div>
				</form>

			</div>
		</div>
	</div>

</body>
</html>