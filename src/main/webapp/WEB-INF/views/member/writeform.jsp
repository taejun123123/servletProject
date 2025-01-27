<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIGN UP</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script>
  $( function() {
	  $( "#datepicker" ).datepicker({
		  minDate: "-100Y", maxDate: "OD", dateFormat:"yy/mm/dd", 
			changeMonth: true, changeYear: true, yearRange: "-100:+0",
			dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
			monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	    });
	  
	  $("#id").keyup(function(){
		  let id=$(this).val();
		  let ireg=/^[a-zA-Z0-9]*$/;
		  if(!ireg.test(id)){
			  //pattern 미충족시 경고
			  $(this).removeClass("valid").addClass("error");
			  $("#checkid").text("아이디는 영문과 숫자만 입력 가능합니다.");
		  }else{
			  //길이 -> 중복체크
			  if(id.length>=4){
				  //길이 조건 충족시 중복 확인
				  $("#checkid").load("/ajax/idCheck.do?id="+id, function(result){
					  if(result.indexOf("이미")>=0){
						  $("#id").removeClass("valid").addClass("error");
						  $("#checkid").removeClass("text-secondary text-success").addClass("text-danger");
						  $("#ok").prop("disabled", true);
					  }else{
						  $("#id").removeClass("error").addClass("valid");
						  $("#checkid").removeClass("text-secondary text-danger").addClass("text-success");
						  $("#ok").prop("disabled", false);
					  }
				  });
			  }else{
				  //길이 조건 미충족
				  $("#id").removeClass("valid").addClass("error");
				  $("#checkid").text("4자~20자의 아이디를 입력하시오.");
				  $("#checkid").removeClass("text-danger text-success").addClass("text-secondary");
			  }//end of inner if-else
		  }//end of outter if-else
	  });//end of id-keyup
	  
	  
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
			  }else{
				  $("#pw").removeClass("error").addClass("valid");
				  $("#checkpw").text("");
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
			 	$("#ok").prop("disabled", false);
		  }else{
			 $("#pwd").removeClass("valid").addClass("error");
			 $("#checkpwd").text("비밀번호와 동일해야 합니다.");
			 $("#ok").prop("disabled", true);
		  }
	  });//end of pw-keyup
	  
	  
	  
	  $("#name").keyup(function(){
		  let name=$(this).val();
		  let nreg=/^[가-힣]*$/;
		  
		  if(!nreg.test(name)){
			  //패턴 부적합 경고
			  $("#name").removeClass("valid").addClass("error");
			  $("#checkname").text("한글 이름만 입력 가능합니다.");
		  }else{
			  //길이체크
			  if(name.length<2){
				  //길이 조건 미충족
				  $("#name").removeClass("valid").addClass("error");
				  $("#checkname").text("이름은 2자 이상 필수 입력해야 합니다.");
			  }else{
				  $("#name").removeClass("error");
				  $("#checkname").text("");
			  }//end of inner if-else
		  }//end of outter if-else
	  });//end of name-keyup
	  
	  
	  $("#email1, #email2").on("keyup change", function(){
		  let email1=$("#email1").val();
		  let email2=$("#email2").val();
		  let email=email1+email2;
		  let ereg=/^[a-zA-Z0-9]*$/;
		  
		  if(!ereg.test(email1)){
			  //pattern에 맞지 않으면 일단 경고
			  $("#email1").removeClass("valid").addClass("error");
			  $("#checkemail").text("이메일은 영어만 입력 가능합니다.");
		  }else{
			  //길이 체크
			  if(email1.length<4){
				  //길이 조건 미충족
				  $("#email1").removeClass("valid").addClass("error");
				  $("#checkemail").text("이메일은 4자 이상 필수 입력해야 합니다.");
			  }else{
				  //길이 조건 충족시 중복 확인
				  $("#checkemail").load("/ajax/emailCheck.do?email="+email, function(result){
					  if(result.indexOf("이미")>-1){ //중복 이메일인 경우
						  $("#email1").removeClass("valid").addClass("error");
						  $("#checkemail").removeClass("text-secondary text-success").addClass("text-danger");
						  $("#ok").prop("disabled", true);
					  }else{ //중복 이메일이 아닌 경우
						  $("#email1").removeClass("error").addClass("valid");
						  $("#checkemail").removeClass("text-secondary text-danger").addClass("text-success");
						  $("#ok").prop("disabled", false);
					  }
				  });
			  }//end of if-else
		  }//end of outter if-else
	  });//end of email-keyup
	  
	  $("#tel1, #tel2, #tel3").keyup(function(){
		  let tel1=$("#tel1").val();
		  let tel2=$("#tel2").val();
		  let tel3=$("#tel3").val();
		  let tel=tel1+tel2+tel3;
		  
		  let treg=/^[0-9]*$/;
		  
		  if(!treg.test(tel)){//숫자 외 입력시
			  $("#checktel").removeClass("text-secondary").addClass("text-danger");
			  $("#checktel").text("전화번호는 숫자만 입력 가능합니다.");
		  }else{
			  if(tel.length==0||tel.length==11){
				  $("#checktel").text("");
			  }else{
				  $("#checktel").removeClass("text-secondary").addClass("text-danger");
				  $("#checktel").text("형식( (예) [ 000-0000-0000 ] ) 미충족시 입력하지 않은 것으로 간주됩니다.");
			  }
		  }
	  });
	  
	  $("#adrsbtn").click(function(){
			 let adrs1=$(".adrs1").val(); 
			 let adrs2=$(".adrs2").val();
			 let address=adrs1+" "+adrs2;
			 
			 $("#adrs_input").val(address);
		});
		  
	  $(".modalbtn").click(function(){
			 $("#myModal").find("input").val(""); 
	  });
	  
  });
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

<style>
.form-group{
padding-top:6px;
margin-bottom:7px;
}
#teldiv>input{
width:20%;
display:inline;
}
.form-control.error {
  border-color: red;
}
.form-control.valid {
  border-color: initial; /* 기본 테두리 색상으로 변경 */
}
#addressdiv>input{
width:40%;
display:inline;
}
</style>

</head>

<body>
<br>
	<div class="container">
		<h2 style="margin-left:45%"> Sign Up <i class="material-icons" style="font-size:30px">person_add</i> </h2>
		<br><br>
		<form action="write.do" method="post">
		    <div class="form-group">
		      <label for="id">ID:</label>
		      <input class="form-control" placeholder="아이디를 입력하시오." id="id" name="id" 
		      required autocomplete="off" maxlength="20" pattern="^[a-zA-Z0-9]{4,20}$">
		    </div>
		    <div id="checkid" class="text-secondary">
		    	<i class="fa fa-check"></i> 영문, 숫자로 구성된 4자~20자의 아이디를 입력하시오.
		    </div>
		    <hr>
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
		    <hr>
		    <div class="form-group">
		      <label for="name">성명:</label>
		      <input class="form-control" placeholder="2자~10자의 한글 이름을 입력하시오." name="name" id="name"
		      required autocomplete="off" maxlength="30" pattern="^[가-힣]{2,10}$" title="한글만 입력 가능합니다.">
		    </div>
		    <div id="checkname" class="text-secondary">
				<i class="fa fa-check"></i> 2자~10자의 한글 이름을 입력하시오.
			</div>
		    <hr>
		    <div class="form-group">
		      <label for="birth">생일:</label>
		      <input id="datepicker" name="birth" autocomplete="off" required>
		    </div>
		    <hr>
		    <div class="form-group">
		      <label for="gender">성별:</label>
		      <div class="form-check-inline">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="gender" value="남성" checked>남성
				  </label>
			  </div>
			  <div class="form-check-inline">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="gender" value="여성">여성
				  </label>
			  </div>
		    </div>
		    <hr>
		    <div class="form-group">
		      <label for="tel">연락처:</label><br>
		      <div id="teldiv">
			      <input class="form-control" id="tel1" name="tel" autocomplete="off" maxlength="3" pattern="^[0-9]{3}$"> -
			      <input class="form-control" id="tel2" name="tel" autocomplete="off" maxlength="4" pattern="^[0-9]{4}$"> -
			      <input class="form-control" id="tel3" name="tel" autocomplete="off" maxlength="4" pattern="^[0-9]{4}$">
		      </div>
		    </div>
		    <div id="checktel" class="text-secondary"></div>
		    <hr>
		    <div class="form-group">
		      <label for="email">email:</label><br>
		      <input class="form-control" name="email" required style="width:50%;display:inline"
		      autocomplete="off" maxlength="50" pattern="^[a-zA-Z0-9]{4,50}$" id="email1">
		      <select class="form-control" id="email2" name="email" style="width:40%;display:inline">
		      	<option value="@musaic.com">@musaic.com</option>
		      	<option value="@naver.com">@naver.com</option>
		      	<option value="@nate.com">@nate.com</option>
		      	<option value="@gmail.com">@gmail.com</option>
		      </select>
		    </div>
		    <div id="checkemail" class="text-secondary">
				<i class="fa fa-check"></i> 50자 이내의 이메일을 입력하시오.
			</div>
		    <hr>
		    <div class="form-group">
		      <label for="address">주소:</label><br>
		      <input class="form-control" value="${vo.address }" autocomplete="off"
		      data-toggle="modal" data-target="#myModal" name="address"
		      style="width:60%;display:inline" id="adrs_input">
		    </div>
		    <br>
		    <div class="float-right">
			    <button class="btn btn-primary" id="ok">가입</button>
			    <button class="btn btn-secondary" type="reset">초기화</button>
			    <button class="btn btn-danger" onclick="history.back()">취소</button>
		    </div>
		    <br>
	    </form>
	</div>

  <!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">주소 찾기</h4>
          <button type="button" class="close modalbtn" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="form-group" id="addressdiv">
		      <input class="form-control" id="sample6_postcode" placeholder="우편번호">
			  <button type="button" class="btn btn-outline-secondary" onclick="sample6_execDaumPostcode()">우편번호 찾기</button><br>
			  <input class="form-control adrs1" name="address" id="sample6_address" placeholder="주소"><br>
			  <input class="form-control adrs2" name="address" id="sample6_detailAddress" placeholder="상세주소">
			  <input class="form-control" id="sample6_extraAddress" placeholder="참고항목">
		  </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button class="btn btn-primary modalbtn" id="adrsbtn" data-dismiss="modal">적용</button>
          <button type="button" class="btn btn-secondary modalbtn" data-dismiss="modal">닫기</button>
        </div>
        
      </div>
    </div>
  </div>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>


</body>
</html>