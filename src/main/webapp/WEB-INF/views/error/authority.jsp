<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>No Access</title>
<style>
.row{
padding:10px;
}
.col-md-3{
font-weight: bold;
}
</style>
</head>
<body>
<div class="container">
		<div class="card">
			<div class="card-header">
				<h4>요청 자원 오류(404)</h4>
			</div>
			<div class="card-body" id="errorDiv">
			<div class="text-center">
			<i class="material-icons" style="font-size:60px;color:red">error_outline</i>
			<hr>
			</div>
				<div class="row">
					<div class="col-md-3">요청 URI</div>
					<div class="col-md-9">${uri }</div> 
				</div>
				<div class="row">
					<div class="col-md-3">오류 메시지</div>
					<div class="col-md-9">요청하신 페이지의 접근 권한이 없습니다.
					</div>
				</div>
				<div>
					<div class="row">
						<div class="col-md-3">조치 사항</div>
						<div class="col-md-9">
						로그인 정보의 등급을 확인해 주세요.오류가 있는 경우 관리자에게 연락해 주세요.
						</div>
					</div>
				</div>
				<br>
			</div>
			<div class="card-footer">
				<a href="/main/main.do" class="btn btn-secondary float-right">돌아가기</a>
			</div>
		</div>
		</div>	

</body>
</html>