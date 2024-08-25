<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>New Song List</title>
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}

button:hover {
	cursor: pointer;
}

#word {
	width: 200px; /* 원하는 너비로 조정 */
}

h1 {
	text-align: center;
	padding-bottom: 20px;
}

.pageNav {
	display: flex;
	justify-content: center; /* 페이지 네이션 가운데 정렬 */
	margin-top: 20px; /* 필요한 경우 추가적인 상단 여백 */
}

img {
	width: 40px;
	height: 40px;
	margin-right: 10px;
}
</style>
<script>
	// 로그인 상태를 나타내는 전역 변수
	var isLoggedIn = ${login != null ? "true" : "false"};
</script>
<script type="text/javascript">
	$(function() {
		// perPageNum 처리
		$("#perPageNum").change(function() {
			$("#searchForm").submit();
		});

		// 검색 데이터 세팅
		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum").val("${(empty pageObject.perPageNum)?'6':pageObject.perPageNum}");

	});
</script>
</head>
<body>
	<div class="container">
		<br> <br>
		<h1>수록곡 선택</h1>
		<br> <br>
		<form action="includeForm.do" id="searchForm">
			<!-- 검색을 하면 무조건 1페이지로 처음에 나오게 설정 -->
			<input name="no" value="${albumNo }" type="hidden">
			<input name="page" value="1" type="hidden">
			<div class="row">
				<div class="col-md-5">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<select name="key" id="key" class="form-control">
								<option value="t">제목</option>
								<option value="s">가수</option>
								<option value="l">가사</option>
							</select>
						</div>
						<input type="text" class="form-control" placeholder="검색" id="word" name="word" value="${pageObject.word}">
						<div class="input-group-append">
							<button class="btn btn-outline-dark">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
				<!-- col-md-8의 끝 : 검색 -->
				<div class="col-md-7 d-flex justify-content-end">
					<!-- 너비를 조정하기 위한 div 추가. float-right : 오른쪽 정렬 -->
					<div style="width: 200px;" class="float-right">
						<div class="input-group mb-4">
							<div class="input-group-prepend">
								<span class="input-group-text">Rows/Page</span>
							</div>
							<select id="perPageNum" name="perPageNum" class="form-control">
								<option value="10" ${pageObject.perPageNum == 10 ? 'selected' : ''}>10</option>
								<option value="15" ${pageObject.perPageNum == 15 ? 'selected' : ''}>15</option>
								<option value="20" ${pageObject.perPageNum == 20 ? 'selected' : ''}>20</option>
								<option value="25" ${pageObject.perPageNum == 25 ? 'selected' : ''}>25</option>
							</select>
						</div>
					</div>
				</div>
				<!-- col-md-4의 끝 : 한페이지당 표시 데이터 개수 -->
			</div>
		</form>
		<form action="include.do" method="post">
			<input type="hidden" name="albumNo" value="${albumNo }">
			<table class="table">
			<thead>
				<!-- 게시판 데이터의 제목 -->
				<tr>
					<th>체크</th>
					<th>번호</th>
					<th>제목</th>
					<th>가수명</th>
					<th>상태</th>
				</tr>
				</thead>
				<!-- 실제 데이터 표시 -->
				<c:forEach items="${newList}" var="vo">
					
						
							<tr class="dataRow" data-music-no="${vo.musicNo}">
								
								<td> <div class="form-check"> <label class="form-check-label"> 
								<input type="checkbox" class="form-check-input" value="${vo.musicNo}" name= "musicNo" ></label></div>
								</td>
								<td class="no">&nbsp;&nbsp;${vo.musicNo}</td>
								<td><img src="${vo.image}" alt="${vo.musicTitle}" /> ${vo.musicTitle}</td>
								<td>${vo.singer}</td>
								<td>${vo.musicStatus}</td>
							</tr>
						

					
				</c:forEach>
				</tbody>

			
					<!-- 수록곡 등록 버튼 --> 
					<tr>
						<td colspan="5">
							<button type="submit" class="btn btn-dark">수록곡 등록</button>
						</td>
					</tr>
			</table>
		</form>
		
		<div class="pageNav">
			<pageNav:pageNav listURI="includeForm.do" pageObject="${pageObject }" query="&no=${albumNo }"/>
		</div>
	</div>
	<!-- 페이지 네이션 처리 -->




</body>
</html>