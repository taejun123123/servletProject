<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<style type="text/css">

/* 기본 스타일 설정 */
.container {
	min-height: calc(100vh - 80px);
}

/* 테이블 컨테이너 스타일 */
.table-container {
	max-height: 600px; /* 최대 높이를 설정 */
	overflow-y: auto; /* 세로 스크롤 추가 */
}

/* 테이블 스타일 */
.table {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed; /* 테이블 레이아웃을 고정하여 열 너비를 고정 */
}

/* 테이블 헤더와 셀 스타일 */
.table th, .table td {
	text-align: center;
	vertical-align: middle;
	padding: 8px;
	position: relative;
	transition: background-color 0.3s ease, box-shadow 0.3s ease;
	overflow: hidden; /* 오버플로우된 내용 숨기기 */
	box-sizing: border-box; /* 패딩과 테두리를 포함한 전체 크기 설정 */
	text-overflow: ellipsis; /* 텍스트가 길 경우 생략 기호 추가 */
	white-space: nowrap; /* 텍스트가 줄 바꿈 없이 한 줄로 표시 */
}

/* 테이블 헤더 스타일 */
.table th {
	background-color: #F4F6FA;
	font-weight: bold;
	color: #9FAED0;
}

/* 테이블 열 너비 조정 */
.table th:nth-child(1), .table td:nth-child(1) {
	width: 50px; /* 번호 열 너비 고정 */
}

.table th:nth-child(2), .table td:nth-child(2) {
	width: 300px; /* 곡정보 열 너비 고정 */
}

.table th:nth-child(3), .table td:nth-child(3) {
	width: 200px; /* 가수 열 너비 조정 */
}

/* 번호 셀 스타일 */
.table td.number {
	font-size: larger;
	font-family: sans-serif;
	color: #7F8BA6;
}

/* 제목 텍스트 스타일 */
.titleText {
	flex: 1; /* 텍스트가 가능한 공간을 차지하도록 설정 */
	text-align: left; /* 텍스트를 왼쪽 정렬 */
	overflow: hidden; /* 넘치는 텍스트 숨기기 */
	text-overflow: ellipsis; /* 텍스트가 길 경우 생략 기호 추가 */
	white-space: nowrap; /* 텍스트가 줄 바꿈 없이 한 줄로 표시 */
}

/* 제목 내용 컨테이너 */
.musicTitleContent {
	display: flex;
	align-items: center; /* 이미지와 텍스트의 수직 정렬 */
	overflow: hidden; /* 넘치는 내용 숨기기 */
}

/* 행의 기본 스타일 */
.dataRow {
	position: relative;
	background-color: #ffffff;
	transition: background-color 0.3s ease, box-shadow 0.3s ease;
	overflow: hidden;
	white-space: nowrap; /* 텍스트가 행을 넘어가지 않도록 설정 */
	text-overflow: ellipsis; /* 텍스트가 길 경우 생략 기호 추가 */
	height: 50px; /* 기본 행 높이 설정 */
	line-height: 50px; /* 줄 높이와 행 높이를 맞춤 */
}

/* 마우스 오버 시 배경색 변경 */
.dataRow:hover {
	background-color: white; /* 마우스 오버 시 배경색 변경 */
}

/* 앨범 이미지 기본 스타일 */
.albumImage {
	width: 30px; /* 기본 너비를 작게 설정 */
	height: 30px; /* 기본 높이를 작게 설정 */
	object-fit: cover;
	border-radius: 5px;
	margin-right: 10px;
	transition: opacity 0.3s ease; /* 투명도 변화 애니메이션 추가 */
	opacity: 0; /* 기본 상태에서 이미지 숨기기 */
}

/* 마우스 오버 시 앨범 이미지 보이기 */
.dataRow:hover .albumImage {
	opacity: 1; /* 마우스 오버 시 이미지 보이기 */
	width: 100px; /* 확장된 이미지 너비 */
	height: 100px; /* 확장된 이미지 높이 */
}


</style>

<script type="text/javascript">
	$(function() {
		// Tooltip 초기화
		$('[data-toggle="tooltip"]').tooltip();
		// 데이터 행 클릭 시 view.do로 이동
		$(".dataRow").click(function() {
			if ($(this).hasClass("disabled")) {
				return; // 비활성화된 행의 클릭 이벤트 무시
			}
			let musicNo = $(this).data("music-no");
			location.href = "view.do?musicNo=" + musicNo;
		});
	});
</script>
<h3 style="margin-top: 10px; text-align: center; color: #9FAED0;">TOP10</h3>
<div class="container">
	<table class="table" style="text-align: center;">
		<thead>
			<tr>
				<th>번호</th>
				<th>곡정보</th>
				<th>가수</th>
			</tr>
		</thead>
		<tbody>
            <c:forEach var="music" items="${topList}" varStatus="status">
                <c:if test="${status.index < 10}"> <!-- Top 10만 표시 -->
                    <tr class="dataRow musicLink ${music.musicStatus == '비공개' ? 'disabled' : ''}" 
                        data-music-no="${music.musicNo}"
                        ${music.musicStatus == '비공개' ? 'data-toggle="tooltip" title="비공개 음원입니다."' : ''}>
                        <td class="number"><strong>${status.index + 1}</strong></td>
                        <td class="musicTitle">
                            <div class="musicTitleContent">
                                <!-- 앨범 이미지 표시 -->
                                <img src="${music.image}" alt="앨범 이미지" class="albumImage float-left" />
                                <!-- 타이틀 -->
                                <span class="titleText">${music.musicTitle}</span>
                            </div>
                        </td>
                        <td>${music.singer}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </tbody>
	</table>
</div>
