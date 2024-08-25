<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 수정폼</title>

<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script type="text/javascript">

$(function() {

	
	
	 let now = new Date();
	 console.log(now);
	 let startYear = now.getFullYear();
	 let yearRange = (startYear - 100) +":" + startYear ;
	
	// 날짜 입력 설정 - datepicker
	$(".datepicker").datepicker({
		// 입력란의 데이터 포맷
		dateFormat: "yy-mm-dd",
		// 월 선택 입력 추가
		changeMonth: true,
		// 년 선택 입력 추가
		changeYear: true,
		// 월 선택할 때의 이름 - 영어가 기본
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		// 달력의 요일 표시
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
// 		// 오늘까지만의 날짜를 선택 가능
// 		maxDate : now,
		// 선택 년도의 범위 - 0살 ~ 100살 
		yearRange : yearRange,
		
	});
	
	let status1 = $("#status1").val(); 
	console.log(status1);
	if(status1 == '발매'){
		$("#open").prop("checked", true);
	}else
		$("#close").prop("checked", true);
});


</script>

</head>
<body>
<div class= "container" >
<h2>앨범 수정폼</h2>
<form action="update.do" method="post" enctype="multipart/form-data">
<input name = "perPageNum" value = "${param.perPageNum}" type = "hidden">
<input name = "status1" value = "${vo.status}" type = "hidden" id  = "status1">
	 
	 <div class="form-group">
   		<label for="albumNo">앨범 번호</label>
    	<input id = "albumNo" name = "albumNo" required class = "form-control" value = "${vo.albumNo }" readonly="readonly">
  	</div>
	 <div class="form-group">
   		<label for="title">앨범 제목</label>
    	<input id = "title" name = "title" required class = "form-control" value = "${vo.title }"
		maxlength="100" pattern="^[^ .].{0,99}" title = "맨앞에 공백문자 불가 1~100자 입력"
		placeholder="제목 입력">
  	</div>
  	
  	<div class="form-group">
		<label for="release_date">발매일</label>
		<input id = "release_date" name = "release_date"  class = "form-control datepicker" autocomplete="off"  
		placeholder="yyyy-mm-dd" value = "${vo.release_date }">
	</div>
  
  	<div class="form-group">
   		<label for="artist">아티스트</label>
    	<input id = "artist" name = "artist" required class = "form-control" value = "${vo.artist }"
		maxlength="100" pattern="^[^ .].{0,99}" title = "맨앞에 공백문자 불가 1~100자 입력"
		placeholder="아티스트 입력">
  	 </div>
	 <div class="form-group">
	   	<label for="genre">장르</label>
	    <input id = "genre" name = "genre" required class = "form-control" value = "${vo.genre }"
		maxlength="50" pattern="^[^ .].{0,49}" title = "맨앞에 공백문자 불가 1~50자 입력"
		placeholder="장르 입력">
	  </div>
  
	 <div class="form-group">
   		<label for="info">앨범 설명</label>
    	<textarea class = "form-control" rows="7" id = "info"name = "info" 
		placeholder = "첫글자는 공백문자나 줄바꿈을 입력할 수 없습니다." required>${vo.info }</textarea>
  	</div>
  	
  	<div class="form-group">
	   	<label for="price">앨범 가격</label>
	    <input id = "price" name = "price" required class = "form-control" value = "${vo.price }"
		maxlength="20" pattern="^[^ .].{0,19}" title = "맨앞에 공백문자 불가 1~20자 입력"
		placeholder="가격 입력">
	  </div>
  	<div class="form-group">
		<div><label for="radio">앨범 상태</label></div>
		<div class="form-check-inline">
			<label class="form-check-label"> 
			<input type="radio" class="form-check-input" name="status" id ="open" value="발매">발매
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label"> 
			<input type="radio" class="form-check-input" name="status" id ="close" value="미공개">미공개
			</label>
		</div>
 		</div>
 

	
	<button type="submit" class= "btn btn-primary">등록</button>
	<button type="reset" class= "btn btn-info">다시입력</button>
	<button type="button" class= "btn btn-secondary" onclick="history.back();">취소</button>

</form>
</div>
</body>
</html>