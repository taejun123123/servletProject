<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글수정</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jquery-datetimepicker@2.5.20/build/jquery.datetimepicker.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery-datetimepicker@2.5.20/build/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript">

	$(function(){
	    // 현재 날짜를 기준으로 설정
	    let now = new Date();
	    let startYear = now.getFullYear();
	    let yearRange = startYear +":" + (startYear + 10);

	    // 날짜와 시간 선택기 초기화
	    $(".datetimepicker").datetimepicker({
	        format: 'Y-m-d H:i',
	        minDate: now,
	        step: 30, // 시간 단위 (30분 단위로 설정)
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

    .form-row {
        display: flex;
        flex-wrap: wrap;
        margin-bottom: 15px;
    }

    .form-row .form-label {
        width: 20%;
        padding: 8px;
        box-sizing: border-box;
    }

    .form-row .form-field {
        width: 80%;
        padding: 8px;
        box-sizing: border-box;
    }
</style>
</head>
<body>
	<div class="container">
		<form action="update.do" method="post">
			<input type="hidden" name="page" value="${param.page }">
			<input type="hidden" name="perPageNum" value="${param.perPageNum }">
			<input type="hidden" name="key" value="${param.key }">
			<input type="hidden" name="word" value="${param.word }">
			<h1>공지사항 글수정</h1>
			
			<div class="form-row">
				<div class="form-label"><label for="no">번호</label></div>
				<div class="form-field"><input id="no" name="no" readonly value="${vo.no }"
					class="form-control"></div>
			</div>

			<div class="form-row">
				<div class="form-label"><label for="title">제목</label></div>
				<div class="form-field">
					<input id="title" name="title" required 
						class="form-control" maxlength="100"
						value="${vo.title }">
				</div>
			</div>

			<div class="form-row">
				<div class="form-label"><label for="title">내용</label></div>
				<div class="form-field">
					<textarea id="content" name="content" required 
						class="form-control" rows="7">${vo.content }</textarea>
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-label"><label for="startDate">게시일</label></div>
				<div class="form-field"><input id="startDate" name="startDate" required autocomplete="off"
					class="form-control datetimepicker" value="${vo.startDate }"></div>
			</div>

			<div class="form-row">
				<div class="form-label"><label for="endDate">종료일</label></div>
				<div class="form-field"><input id="endDate" name="endDate" required autocomplete="off"
					class="form-control datetimepicker" value="${vo.endDate }"></div>
			</div>
			
			<div class="form-row">
				<div class="form-label"><label for="anocDate">발표일</label></div>
				<div class="form-field"><input id="anocDate" name="anocDate" required autocomplete="off"
					class="form-control datepicker" value="${vo.anocDate }"></div>
			</div>

			<div class="form-row">
				<div class="form-field" style="width: 100%;">
					<button class="btn btn-primary">수정</button>
					<button type="reset" class="btn btn-secondary">다시입력</button>
					<button type="button" onclick="history.back();" class="btn btn-warning">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
