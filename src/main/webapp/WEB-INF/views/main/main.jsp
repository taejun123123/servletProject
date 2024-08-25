<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<div class="container">
		<!-- 캐로셀 -->
		<div id="demo" class="carousel slide" data-ride="carousel"
			style="margin-top: 80px; margin-bottom: 50px;">
			<!-- Indicators -->
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ul>
			<!-- The slideshow -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href="/album/view.do?no=1"><img src="/img/sample01.jpg"></a>
				</div>
				<div class="carousel-item">
					<a href="/album/view.do?no=7"><img src="/img/sample02.jpg"></a>
				</div>
				<div class="carousel-item">
					<a href="/album/view.do?no=3"><img src="/img/sample03.jpg"></a>
				</div>
			</div>
			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>

		<div class="row">
			<div class="col-md-12 module">
				<jsp:include page="albumList.jsp" />
			</div>
		</div>
		<div>
		<img alt="chart" src="/img/chart.jpg" style="width: 1135px; height: 200px">
		</div><br><br>
		<!-- 그리드 시스템으로 두 개의 리스트 옆으로 배치 -->
		<div class="row">
			<!-- 첫 번째 리스트 (보드 리스트) -->
			<div class="col-md-6">
				<div class="moduleList fixed">
					<jsp:include page="newList.jsp" />
				</div>
			</div>
			<!-- 두 번째 리스트 (노티스 리스트) -->
			<div class="col-md-6">
				<div class="moduleList fixed">
					<jsp:include page="topList.jsp" />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/main.js"></script>
</body>
</html>
