<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 등록</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
    // 날짜 입력 설정 - datepicker : 전체 날짜 입력 형태
    let now = new Date();
    let startYear = now.getFullYear();
    let yearRange = startYear +":" + (startYear + 10) ;
    $(".datepicker").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        changeYear: true,
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        yearRange: yearRange,
    });

    $("#startDate").datepicker("option", {
        "minDate" : now,
        onClose : function( selectedDate ) {
            if($(this).val() != "")
                $( "#endDate" ).datepicker( "option", "minDate", selectedDate );
        }
    });

    $("#endDate").datepicker("option", {
        "minDate" : now,
        onClose : function( selectedDate ) {
            if($(this).val() != "")
                $( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    // 제출 버튼 클릭 시 모달 띄우기
    $("#submitButton").click(function() {
        $('#writeModal').modal('show');
    });
    
    // 확인 버튼 클릭 시 폼 제출
    $("#confirmSubmit").click(function() {
        $("form").submit();
    });
});
</script>
</head>
<body>
<div class="container">
    <h1>공지 등록</h1>
    <form action="write.do" method="post">
        <input name="perPageNum" value="${param.perPageNum }" type="hidden">
        <table class="table">
            <tr>
                <th>제목</th>
                <td>
                    <input id="title" name="title" required 
                        class="form-control" maxlength="100"
                        placeholder="제목 입력 : 3자 이상 100자 이내"
                    >
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea class="form-control" id="content" name="content"  required
                    rows="7" placeholder="첫글자는 공백문자나 줄바꿈을 입력할 수 없습니다."></textarea>
                </td>
            </tr>
            <tr>
                <th>게시일</th>
                <td>
                    <input id="startDate" name="startDate" required autocomplete="off"
                        class="form-control datepicker" 
                    >
                </td>
            </tr>
            <tr>
                <th>종료일</th>
                <td>
                    <input id="endDate" name="endDate" required autocomplete="off"
                        class="form-control datepicker" 
                    >
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" id="submitButton" class="btn btn-primary">등록</button>
                    <button type="reset" class="btn btn-secondary">다시입력</button>
                    <button type="button" onclick="history.back();" class="btn btn-warning">취소</button>
                </td>
            </tr>
        </table>
    </form>
</div>

<!-- 등록 확인 모달 -->
<div class="modal fade" id="writeModal" tabindex="-1" role="dialog" aria-labelledby="writeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="writeModalLabel">등록 확인</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                공지를 등록합니다.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" id="confirmSubmit" class="btn btn-success">등록</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
