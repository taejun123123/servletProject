<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jquery-datetimepicker@2.5.20/build/jquery.datetimepicker.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery-datetimepicker@2.5.20/build/jquery.datetimepicker.full.min.js"></script>
<style type="text/css">
.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}
</style>
<script type="text/javascript">

$(function(){
    // 현재 날짜를 기준으로 설정
    let now = new Date();
    let startYear = now.getFullYear();
    let yearRange = startYear + ":" + (startYear + 10);

    // 날짜와 시간 선택기 초기화
    $(".datetimepicker").datetimepicker({
        format: 'Y-m-d H:i',
        minDate: now,
        step: 15, // 시간 단위 (30분 단위로 설정)
        timepicker: true, // 시간 선택 활성화
        datepicker: true // 날짜 선택 활성화
    });

    // 시작 날짜와 종료 날짜 설정
    $("#startDate").datetimepicker({
        onClose: function( selectedDate ) {
            if($(this).val() != "")
                $( "#endDate" ).datetimepicker("option", "minDate", selectedDate );
        }
    });
    
    $("#endDate").datetimepicker({
        onClose: function( selectedDate ) {
            if($(this).val() != "")
                $( "#startDate" ).datetimepicker("option", "maxDate", selectedDate );
        }
    });

    // 발표일 설정 (날짜만 선택 가능)
    $("#endDate").on('change', function() {
        let endDate = $(this).val();
        if(endDate != "") {
            $( "#anocDate" ).datepicker("option", "minDate", endDate );
        }
    });

    $("#anocDate").datepicker({
        dateFormat: 'yy-mm-dd',
        onClose: function( selectedDate ) {
            if(selectedDate != "") {
                $(this).datepicker("option", "minDate", $("#endDate").val());
            }
        }
    });
});
</script>
</head>
<body>

<div class="container">
    <h2>이벤트 등록</h2>
    <form action="write.do" method="post" enctype="multipart/form-data">
        <input name="perPageNum" value="${param.perPageNum }" type="hidden">
        
        <div class="form-group">
            <label for="title">제목</label> 
            <input id="title" name="title" required class="form-control">
        </div>

        <div class="form-group">
            <label for="imageFile">첨부 이미지</label> 
            <input id="imageFile" name="imageFile" required class="form-control" type="file">
        </div>
        
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" required rows="7" placeholder="첫글자는 공백문자나 줄바꿈을 입력할 수 없습니다."></textarea>
        </div>
        
        <div class="form-group">
            <label for="startDate">게시일</label> 
            <input id="startDate" name="startDate" required autocomplete="off" class="form-control datetimepicker">
        </div>
        
        <div class="form-group">
            <label for="endDate">종료일</label> 
            <input id="endDate" name="endDate" required autocomplete="off" class="form-control datetimepicker">
        </div>
        
        <div class="form-group">
            <label for="anocDate">발표일</label> 
            <input id="anocDate" name="anocDate" required autocomplete="off" class="form-control">
        </div>

        <button class="btn btn-secondary">등록</button>
        <button type="reset" class="btn btn-secondary">다시입력</button>
        <button type="button" onclick="history.back();" class="btn btn-danger">취소</button>
    </form>
</div>
</body>
</html>
