<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음원 수정 폼</title>
<style>
.container {
	width: 80%; /* 컨테이너의 최대 너비를 80%로 설정 */
	margin: 0 auto; /* 화면 중앙에 배치 */
	padding: 20px;
	box-sizing: border-box;
}

.form-container {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.form-column {
	display: flex;
	flex-direction: column;
	width: 48%; /* 각 열의 너비를 48%로 설정 */
}

.form-group {
	margin-bottom: 15px;
}

.button-container {
	text-align: center;
	width: 100%;
}

textarea {
	width: 100%;
	resize: none;
}

label {
	display: flex;
	align-items: center;
	margin-bottom: 5px;
}

.badge-spacing {
	margin-left: 10px; /* 라벨과 뱃지 사이의 간격을 설정 */
}

input[type="text"], input[type="file"], textarea {
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
}

/* 추가 스타일: 각 폼 그룹 높이 맞추기 */
.form-group-container {
	display: flex;
	flex-direction: column;
	justify-content: space-between; /* 위아래 사이 간격을 균등하게 배치 */
	height: 100%;
}

h1 {
	text-align: center;
	margin-top: 50px;
}
</style>
</head>
<body>

	<div class="container mt-5">
		<h1>Music UpdateForm</h1>
		<form action="update.do?musicNo=${vo.musicNo }" method="post"
			id="updateForm" enctype="multipart/form-data">
			<!-- 수정할 음원의 번호를 hidden 필드로 추가 -->
			<input name="musicNo" type="hidden" value="${vo.musicNo}">

			<div class="form-container mt-5">
				<div class="form-column">
					<div class="form-group-container mt-5">
						<div class="form-group">
							<label for="musicTitle" class="label"> 음원명 <span
								class="badge badge-secondary badge-spacing">필수</span></label> <input
								type="text" class="form-control" placeholder="제목 입력" id="title"
								name="musicTitle" value="${vo.musicTitle}">
						</div>
						<div class="form-group">
							<label for="singer" class="label"> 가수명 <span
								class="badge badge-secondary badge-spacing">필수</span></label> <input
								type="text" class="form-control" placeholder="가수명 입력"
								id="singer" name="singer" value="${vo.singer}">
						</div>
						<div class="form-group">
							<label for="lyricist" class="label"> 작사가 <span
								class="badge badge-secondary badge-spacing">필수</span></label> <input
								type="text" class="form-control" placeholder="작사가 입력"
								id="lyricist" name="lyricist" value="${vo.lyricist}">
						</div>
						<div class="form-group">
							<label for="songWriter" class="label"> 작곡가 <span
								class="badge badge-secondary badge-spacing">필수</span></label> <input
								type="text" class="form-control" placeholder="작곡가 입력"
								id="songWriter" name="songWriter" value="${vo.songWriter}">
						</div>
						<div class="form-group">
							<label for="musicFile" class="label"> 음원 파일 <span
								class="badge badge-secondary badge-spacing">필수 <i
									class="fa fa-upload"></i></span></label> <input type="file"
								class="form-control" id="musicFile" name="musicFile"
								accept="audio/mpeg, audio/flac" required>
						</div>
						<!-- 앨범 선택 드롭다운 -->
						<div>
							<label for="albumNo">앨범 선택:</label> <select name="albumNo"
								class="custom-select mb-3">
								<option selected>Custom Select Menu</option>
								<c:forEach items="${albumList}" var="album">
									<option value="${album.albumNo}">${album.albumNo}.
										${album.title}</option>
								</c:forEach>

							</select>
						</div>
						<div>
							<label for="includedNo"></label> 수록곡 번호 (max 10) <span
								class="badge badge-secondary badge-spacing">필수</span><select name="includedNo"
								class="custom-select mb-3">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
							</select>
						</div>
					</div>
				</div>
				<div class="form-column">
					<div class="form-group mt-5">
						<label for="lyric" class="label"> 가사 <span
							class="badge badge-secondary badge-spacing">필수</span></label>
						<textarea rows="22" class="form-control" placeholder="내용 입력"
							id="lyric" name="lyric">${vo.lyric}</textarea>
					</div>
				</div>
			</div>

			<div class="button-container mt-5 mb-5">
				<button type="submit" class="btn btn-dark" id="writeBtn">수정</button>
				<button type="reset" class="btn btn-dark">다시 입력</button>
				<button type="button" class="btn btn-dark" id="cancelBtn"
					onclick="history.back()">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
