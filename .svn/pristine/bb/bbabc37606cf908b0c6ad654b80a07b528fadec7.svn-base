<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER LIST</title>
<script>
$(function(){
	$(".gradeno, .status").on("mouseover", function(){
		$(".datarow").off("click");
	})
	.on("mouseout", function(){
		$(".datarow").on("click", function(){
			let id=$(this).data("id");
			location="view.do?id="+id;
		});
	});
	
	$(".datarow").on("change", ".gradeno, .status", function(){
		//기존 값과 같은 값으로 설정시 적용 버튼 비활성화
		let change=$(this).val();
		let origin=$(this).data("data");
		
		if(origin!=change)
			$(this).find(".btn").prop("disabled", false);
		else $(this).find(".btn").prop("disabled", false);
	});
	
	//검색데이터 세팅
	$("#key").val("${(empty po.key)?'id':po.key}");
	$("#word").val("${(empty po.word)?'':po.word}");
	$("#perPageNum").val("${(empty po.perPageNum)?'10':po.perPageNum}");
	
	//Rows/Page 변경 이벤트 처리
	$("#perPageNum").change(function(){
		$("#search").submit();
	});
});
</script>
</head>

<body>
	<div class="container">
	<br>
		<h2> Member List <i class="fa fa-address-book-o" style="font-size:32px"></i></h2>
		<br><br>
		<form id="search" action="list.do?${po.pageQuery }">
			<div class="row">
				<div class="col-lg-6"> <!-- 검색폼 -->
					<div class="input-group mb-3">
						<div class="input-group-append">
							<select class="form-control" name="key" id="key">
								<option value="id">ID</option>
								<option value="name">성명</option>
								<option value="email">E-Mail</option>
							</select>
						</div>
						<input name="word" id="word" class="form-control" placeholder="검색어를 입력하시오.">
						<div class="input-group-append">
							<button class="btn btn-outline-primary">검색</button>
						</div>
					</div>
				</div>
				<div class="col-lg-3"></div> <!-- 간격 띄우기 -->
				<div class="col-lg-3"> <!-- per page row 설정 -->
					<div class="input-group mb-3">
						<div class="input-group-append">
							<span class="input-group-text">Rows/page</span>
						</div>
						<select name="perPageNum" class="form-control" id="perPageNum">
							<option>10</option>
							<option>15</option>
							<option>20</option>
						</select>
					</div>
				</div>
			</div>
		</form>
		
		<table class="table table-hover">
		    <thead>
		      <tr>
		        <th>ID</th>
		        <th>성명</th>
		        <th>E-Mail</th>
		        <th>등급</th>
		        <th>상태</th>
		      </tr>
		    </thead>
		    <tbody>
			    <c:forEach items="${list }" var="vo">
			      <tr class="datarow id" data-id="${vo.id}">
			        <td>${vo.id}</td>
			        <td>${vo.name}</td>
			        <td>${vo.email}</td>
			        <td class="gradeno">
			        <form action="changeGrade.do?id=${vo.id}&${po.pageQuery }" method="post">
						<div class="form-group">
							<select class="form-control" name="gradeno" data-data="${vo.gradeNo }" style="width:40%;display:inline">
								<option value="1" ${(vo.gradeNo==1)?'selected':'' }>일반회원</option>
								<option value="9" ${(vo.gradeNo==9)?'selected':'' }>관리자</option>
							</select>
							<button class="btn btn-primary" disabled>적용</button>
						</div>
					</form>
			        </td>
			        <td class="status">
			        <form action="changeStatus.do?id=${vo.id}&${po.pageQuery }" method="post">
						<div class="form-group">
							<select class="form-control" name="status" data-data="${vo.status }" style="width:40%;display:inline">
								<option value="정상" ${vo.status.equals('정상') ? 'selected' : ''}>정상</option>
								<option value="휴면" ${vo.status.equals('휴면') ? 'selected' : ''}>휴면</option>
								<option value="탈퇴" ${vo.status.equals('탈퇴') ? 'selected' : ''}>탈퇴</option>
								<option value="강퇴" ${vo.status.equals('강퇴') ? 'selected' : ''}>강퇴</option>
							</select>
							<button class="btn btn-primary" disabled>적용</button>
						</div>
					</form>
			        </td>
			      </tr>
			    </c:forEach>
			</tbody>
		</table>
		<br>
		<div>
		<a href="writeform.do" class="btn btn-success float-right">등록</a>
		</div>
		<!-- 테이블네이션 -->
		<br><br>
		<div style="margin-left:40%"><pageNav:pageNav listURI="list.do" pageObject="${po}"></pageNav:pageNav></div>
	</div>
</body>
</html>