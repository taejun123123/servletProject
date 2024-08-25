<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find ID</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script>
  $( function() {
	  $("#find").click(function(){
		  let name=$("#name").val();
		  let email1=$("#email1").val();
		  let email2=$("#email2").val();
		  let email=email1+email2;
		  console.log("name: "+name+", email: "+email);
 		 $("#content").load("/ajax/findid.do?email="+email+"&name="+name, function(result){}) ;
	  });
  });
</script>
<style>
.form-group{
padding-top:6px;
padding-bottom:5px;
}
</style>

</head>

<body>
<br>
	<div class="container">
		<h2 style="margin-left:45%"> 아이디 찾기 <i class="material-icons" style="font-size:30px">person_add</i> </h2>
		<br><br>
		    <div class="form-group">
		      <label for="name">이름:</label>
		      <input class="form-control" placeholder="이름을 입력하시오." name="name" 
		      required autocomplete="off" maxlength="20" pattern="^[가-힣]{2,10}$" id="name">
		    </div>
			
		    <div class="form-group">
		      <label for="email">E-Mail:</label><br>
		      <input class="form-control" name="email" required style="width:50%;display:inline"
		      autocomplete="off" pattern="^[a-zA-Z0-9]{4,50}$" id="email1" placeholder="E-Mail을 입력하시오.">
		      <select class="form-control" id="email2" name="email" style="width:40%;display:inline">
		      	<option value="@musaic.com">@musaic.com</option>
		      	<option value="@naver.com">@naver.com</option>
		      	<option value="@nate.com">@nate.com</option>
		      	<option value="@gmail.com">@gmail.com</option>
		      </select>
		    </div>
		    <br>
		    
		    <div class="float-right">
			    <button data-toggle="modal" data-target="#idModal"
			    id="find" type="button" class="btn btn-primary">찾기</button>
			    <button class="btn btn-secondary" type="reset">초기화</button>
			    <button class="btn btn-danger" onclick="history.back()">취소</button>
		    </div>
		    <br>
	</div>

	<!-- 모달창 -->
	<div class="modal fade" id="idModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
			
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">조회 결과</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body mx-auto" id="content">
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<a class="btn btn-primary" href="loginform.do">확인</a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>