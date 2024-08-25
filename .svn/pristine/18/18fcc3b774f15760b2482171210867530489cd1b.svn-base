<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
.dataRow:hover{
	background: #ddd;
	cursor: pointer;
}
.pageNav {
	display: flex;
	justify-content: center; /* 페이지 네이션 가운데 정렬 */
	margin-top: 20px; /* 필요한 경우 추가적인 상단 여백 */
}
.search-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.search-form {
	flex: 1;
	max-width: 500px; /* 검색창 길이 조정 */
}

.rows-page-container {
	width: 200px;
	text-align: right;
}
 h1 {
        text-align: center; /* 제목 가운데 정렬 */
        margin-top: 20px; /* 제목 상단 여백 추가 */
        margin-bottom: 40px; /* 제목 하단 여백 추가 */
    }
</style>

<script type="text/javascript">
$(function(){
	 // 라디오 버튼 상태 설정
   let period = "${param.period}";
   if (period) {
      $("input[name='optionList'][value='" + period + "']").prop('checked', true);
   }
	// 이벤트 처리
   $(".dataRow").click(function(){
      let no = $(this).find(".no").text();
      console.log("no = " + no);
      location="view.do?no=" + no + "&inc=1&${pageObject.pageQuery}";
   });
	
	$("#perPageNum").change(function(){
		$("#searchForm").submit();
	});
	
	$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
	$("#perPageNum").val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");

	$(".noticeOption").change(function(){
		if(this.optionList[0].checked) {
			location = "/notice/list.do?period=pre";
		}
		else if(this.optionList[1].checked) {
			location = "/notice/list.do?period=old";
		}
		else if(this.optionList[2].checked) {
			location = "/notice/list.do?period=res";
		}
		else if(this.optionList[3].checked) {
			location = "/notice/list.do?period=all";
		}
	});
});
</script>

</head>
<body>
<div class="container">
	<h1>Notice</h1>
	
		<!-- 검색란의 시작 -->
		<form action="list.do" id="searchForm" class="search-container">
			<input name="page" value="1" type="hidden">
			<div class="search-form">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<select name="key" id="key" class="form-control">
							<option value="t">제목</option>
							<option value="c">내용</option>
							<option value="tc">제목/내용</option>
						</select>
					</div>
					<input type="text" class="form-control" placeholder="검색" id="word"
						name="word" value="${pageObject.word }">
					<div class="input-group-append">
						<button class="btn btn-outline-primary">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="rows-page-container">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">Rows/Page</span>
					</div>
					<select id="perPageNum" name="perPageNum" class="form-control">
						<option>10</option>
						<option>15</option>
						<option>20</option>
						<option>25</option>
					</select>
				</div>
			</div>
		</form>
  <table class="table">
  		    <c:if test="${!empty login && login.gradeNo == 9 }">
  	<tr>
  		  <td colspan="4">
            <form class ="noticeOption">
              <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" class="custom-control-input" id="pre" name="optionList" value="pre">
                <label class="custom-control-label" for="pre">현재 공지</label>
              </div>
              <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" class="custom-control-input" id="old" name="optionList" value="old">
                <label class="custom-control-label" for="old">이전 공지</label>
              </div>
              <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" class="custom-control-input" id="res" name="optionList" value="res">
                <label class="custom-control-label" for="res">예정 공지</label>
              </div>
              <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" class="custom-control-input" id="all" name="optionList" value="all">
                <label class="custom-control-label" for="all">모든 공지</label>
              </div>
            </form>
            </td>
  	</tr>
  		  </c:if>
  	<tr>
  		<th>번호</th>
  		<th>제목</th>
  		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;게시일&nbsp;&nbsp;~&nbsp;&nbsp;종료일</th>
  		<th>작성일</th>
  	</tr>
  	<c:forEach items="${list }" var="vo">
  		<tr class="dataRow">
  			<td class="no">${vo.no }</td>
  			<td>${vo.title }</td>
  			<td>${vo.startDate }&nbsp; ~ &nbsp;${vo.endDate }</td>
  			<td>${vo.writeDate }</td>
  		</tr>
  	</c:forEach>
	<c:if test="${!empty login && login.gradeNo == 9 }">
	<tr>
		<td colspan="4">
			<a href="writeForm.do?perPageNum=${pageObject.perPageNum }" class="btn btn-primary">등록</a>
		</td>
	</tr>
	</c:if>
  </table>
  <div class="pageNav"><pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav></div>
</div>
</body>
</html>