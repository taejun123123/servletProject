<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>이벤트</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- jQuery 라이브러리 추가 -->
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

	$(".eventOption").change(function(){
		if(this.optionList[0].checked) {
			location = "/event/list.do?period=pre";
		}
		else if(this.optionList[1].checked) {
			location = "/event/list.do?period=old";
		}
		else if(this.optionList[2].checked) {
			location = "/event/list.do?period=res";
		}
		else if(this.optionList[3].checked) {
			location = "/event/list.do?period=all";
		}
	});
});
function navigateToEventList() {
    location.href = "list.do";
}
</script>
<style>
.card {
    margin-bottom: 30px; /* 카드 사이의 간격 설정 */
    width: 100%; /* 카드 너비를 100%로 설정하여 컨테이너에 맞게 조절 */
    height: 330px; /* 카드 높이 설정 */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 1px solid #ddd; /* 카드 테두리 추가 */
    border-radius: 5px; /* 카드 모서리 둥글게 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 카드 그림자 추가 */
    overflow: hidden; /* 카드 안의 요소가 카드 영역을 넘지 않도록 설정 */
}

.card-img-top {
    width: 100%; /* 카드 너비에 맞게 조정 */
    height: 200px; /* 고정된 높이 설정 */
    object-fit: cover; /* 이미지 비율을 유지하며 영역에 맞게 크기 조절 */
}

.card-body {
    padding: 5px; /* 카드 내부 패딩 설정 */
    flex-grow: 1; /* 텍스트 영역이 남는 공간을 차지하도록 설정 */
    display: flex;
    flex-direction: column;
    justify-content: center; /* 텍스트를 가운데로 정렬 */
    align-items: left; /* 텍스트를 수평으로 가운데로 정렬 */
    text-align: left; /* 텍스트 가운데 정렬 */
}

.card-title {
    font-size: 1rem; /* 제목 폰트 크기 증가 */
    margin-bottom: 5px; /* 제목과 본문 사이의 간격 설정 */
}

.card-text {
    font-size: 0.98rem; /* 본문 폰트 크기 증가 */
    color: #555; /* 본문 색상 설정 */
    margin: 0; /* 본문과 다른 요소 사이의 간격 제거 */
}

.dataRow:hover {
    opacity: 0.7; /* 투명도 */
    cursor: pointer;
}

.event-image {
    width: 100%; /* 이미지 너비를 카드 너비에 맞게 조절 */
    height: 200px; /* 고정된 높이 설정 */
    object-fit: cover; /* 이미지 비율을 유지하며 영역에 맞게 크기 조절 */
}

.card-img-top {
    width: 100%; /* 카드 너비에 맞게 조정 */
    height: 200px; /* 고정된 높이 설정 */
    object-fit: cover; /* 이미지 비율을 유지하며 영역에 맞게 크기 조절 */
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

.card {
    margin-bottom: 30px; /* 카드 사이의 간격 설정 */
}
 h1 {
 width: 15%; /* 원하는 넓이로 설정 */
    margin: 20px auto 40px auto; /* 상단과 하단 여백, 좌우 여백을 auto로 설정하여 가운데 정렬 */
        text-align: center; /* 제목 가운데 정렬 */
        margin-top: 20px; /* 제목 상단 여백 추가 */
        margin-bottom: 40px; /* 제목 하단 여백 추가 */
        font-family: 'Courier New', sans-serif; /* 제목 폰트 */
        cursor: pointer;
         border-radius: 10px; /* 모서리 둥글게 */
         border: none; /* 테두리 제거 */
         
    }
</style>
</head>
<body>
    <div class="container">

        <h1 onclick="navigateToEventList()">Event</h1>
        <!-- 검색란의 시작 -->
        <form action="list.do" id="searchForm" class="search-container">
            <input name="page" value="1" type="hidden">
            <div class="search-form">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <select name="key" id="key" class="form-control">
                            <option value="t">제목</option>
                            <option value="c">내용</option>
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
                        <option>6</option>
                        <option>9</option>
                        <option>12</option>
                        <option>15</option>
                    </select>
                </div>
            </div>
        </form>

        <div class="event-options">
    <form class="eventOption">
      <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" class="custom-control-input" id="pre" name="optionList" value="pre">
        <label class="custom-control-label" for="pre">현재 이벤트</label>
      </div>
      <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" class="custom-control-input" id="old" name="optionList" value="old">
        <label class="custom-control-label" for="old">지난 이벤트</label>
      </div>
      <c:if test="${!empty login && login.gradeNo == 9 }">
      <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" class="custom-control-input" id="res" name="optionList" value="res">
        <label class="custom-control-label" for="res">예약 이벤트</label>
      </div>
      <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" class="custom-control-input" id="all" name="optionList" value="all">
        <label class="custom-control-label" for="all">모든 이벤트</label>
      </div>
      </c:if>
      </form>
      </div>
    

        <br>
        <c:if test="${empty list }">
            <div class="jumbotron">
                <h4>데이터가 존재하지 않습니다.</h4>
                <button type="button" onclick="history.back();"
            class="btn btn-dark">리스트</button>
            </div>
        </c:if>
        <c:if test="${!empty list }">
            <div class="row">
                <c:forEach items="${list }" var="vo" varStatus="vs">
                    <c:if test="${(vs.index != 0) && (vs.index % 3 == 0) }">
            </div>
            <div class="row">
        </c:if>
        <!-- 데이터 표시 시작 -->
        <div class="col-md-4 dataRow">
            <div class="card" style="width: 100%">
                <div class="imageDiv text-center align-content-center">
                    <img class="card-img-top event-image" src="${vo.image }"
                        alt="image">
                </div>
                <div class="card-body">
                    <strong class="card-title truncate title"><span class="no">${vo.no}</span>.
                        ${vo.title } </strong>
                    <p class="card-text text-truncate title">발표일 : ${vo.anocDate }</p>
                </div>
            </div>
        </div>

        <!-- 데이터 표시 끝 -->
        </c:forEach>
    </div>
    <!-- 페이지 네이션 처리 -->
    <c:if test="${!empty login && login.gradeNo == 9 }">
        <a href="writeForm.do?perPageNum=${pageObject.perPageNum }"
            class="btn btn-secondary">등록</a>
    </c:if>
    <div class="pageNav">
        <pageNav:pageNav listURI="list.do" pageObject="${pageObject }" />
    </div>
    </c:if>
    <!-- 리스트 데이터 표시의 끝 -->
    </div>
</body>
</html>
