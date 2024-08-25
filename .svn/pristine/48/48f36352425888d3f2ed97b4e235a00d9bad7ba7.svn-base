<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change PW</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script>
  $( function() {
	  $("#pw").keyup(function(){
		  let pw=$("#pw").val();
		  let preg=/^[a-zA-Z0-9]*$/;
		  
		  if(!preg.test(pw)){
			  //pattern 미충족시 경고
			  $("#pw").removeClass("valid").addClass("error");
			  $("#checkpw").text("비밀번호는 영문과 숫자만 입력 가능합니다.");
		  }else{
			  if(pw.length<4){
				  //길이 조건 미충족
				  $("#pw").removeClass("valid").addClass("error");
				  $("#checkpw").text("비밀번호는 4자 이상 필수 입력해야 합니다.");
				  $("#ok").prop("disabled", true);
			  }else{
				  $("#pw").removeClass("error").addClass("valid");
				  $("#checkpw").text("");
				  $("#ok").prop("disabled", false);
			  }
		  }//end of if-else
	  });//end of pw-keyup
	  
	  $("#pwd").keyup(function(){
		  let pw=$("#pw").val();
		  let pwd=$("#pwd").val();
		  let preg=/^[a-zA-Z0-9]*$/;
		  
		  if(pw==pwd){
				$("#pwd").removeClass("error").addClass("valid");
			 	$("#checkpwd").text("");
		  }else{
			 $("#pwd").removeClass("valid").addClass("error");
			 $("#checkpwd").text("비밀번호와 동일해야 합니다.");
		  }
	  });//end of pw-keyup
  });
</script>
<style>
.form-group{
padding-top:6px;
padding-bottom:5px;
}
.form-control.error {
  border-color: red;
}
.form-control.valid {
  border-color: initial; /* 기본 테두리 색상으로 변경 */
}
</style>

</head>

<body>
<br>
	<div class="container">
		<h2 style="margin-left:45%"> 비밀번호 변경 <i class="material-icons" style="font-size:30px">person_add</i> </h2>
		<br><br>
		<form action="changepw.do?id=${param.id}" method="post">
		    <div class="form-group">
		      <label for="pw">비밀번호:</label>
		      <input class="form-control" placeholder="비밀번호를 입력하시오." id="pw"
		      name="pw" required autocomplete="off" maxlength="20" pattern="^[a-zA-Z0-9]{4,20}$">
		    </div>
		    <div id="checkpw" class="text-secondary">
				<i class="fa fa-check"></i> 영문, 숫자로 구성된 4자~20자의 비밀번호를 입력하시오.
			</div>
			<br>
		    <div class="form-group">
		      <label for="pwd">비밀번호 확인:</label>
		      <input class="form-control" type="password" placeholder="비밀번호와 동일해야 합니다."
		      required autocomplete="off" maxlength="20"  id="pwd">
		    </div>
		    <div id="checkpwd" class="text-secondary">
				<i class="fa fa-check"></i> 비밀번호와 동일한 입력이어야 합니다.
			</div>
		    <div class="float-right">
			    <button class="btn btn-primary" id="ok">변경</button>
			    <button class="btn btn-secondary" type="reset">초기화</button>
			    <button class="btn btn-danger" onclick="history.back()">취소</button>
		    </div>
		    <br>
	    </form>
	</div>
</body>
</html>