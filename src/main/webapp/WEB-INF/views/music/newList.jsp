<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    .btn-add {
        display: inline-block; /* 인라인 블록으로 설정하여 클릭 영역 확대 */
        padding: 10px 15px; /* 버튼의 내외부 여백을 조정하여 클릭 가능한 영역 확대 */
    }

    .table th, .table td {
        text-align: center; /* 수평 중앙 정렬 */
        vertical-align: middle; /* 수직 중앙 정렬 */
    }

.table td {
    vertical-align: middle; /* 수직 중앙 정렬 */
}

.table td img {
    margin-right: 10px; /* 이미지와 텍스트 사이에 여백 추가 */
}

.table td .music-title {
    display: inline-block;
    vertical-align: middle; /* 수직 중앙 정렬 */
    text-align: center; /* 제목 가운데 정렬 */
}
/* 앨범 이미지 왼쪽 정렬 스타일 추가 */
.album-image {
    text-align: left; /* 왼쪽 정렬 */
}

/* 이미지와 텍스트를 나란히 배치 */
.image-container {
    display: flex;
    align-items: center;
}

/* 제목을 가운데 정렬 */
.music-title {
    flex: 1;
    text-align: center;
}
</style>
<script type="text/javascript">
    $(function() {
        // 로그인 상태를 나타내는 전역 변수
        var isLoggedIn = ${login != null ? "true" : "false"};

        // perPageNum 처리
        $("#perPageNum").change(function() {
            $("#searchForm").submit();
        });

        // 검색 데이터 세팅
        $("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
        $("#perPageNum").val("${(empty pageObject.perPageNum)?'6':pageObject.perPageNum}");

        $(".btn-add").click(function(event) {
            // 이벤트 전파 막기
            event.stopPropagation();

            if (!isLoggedIn) {
                // 사용자가 로그인하지 않은 상태면, 로그인 필요 모달 표시
                $("#modalMessage").text("로그인 후 이용하실 수 있습니다.");
                $("#resultModal").modal('show');
                return;
            }

            let musicNo = $(this).data("music-no");
            let albumNo = $(this).data("album-no"); // albumNo가 필요한 경우

            console.log("Music No: " + musicNo); // 콘솔에서 확인
            console.log("Album No: " + albumNo); // 콘솔에서 확인
            if (typeof musicNo === 'undefined') {
                console.error("musicNo is undefined");
                return;
            }

            $.ajax({
                url: "increaseHit.do",
                type: "POST",
                data: {
                    musicNo: musicNo
                },
                success: function(response) {
                    let modalMessage = response === "success" ? "플레이 리스트 목록에 추가되었습니다." : "플레이 리스트 목록에 담기지 않았습니다.";
                    $("#modalMessage").text(modalMessage);
                    $("#resultModal").modal('show');
                },
                error: function() {
                    $("#modalMessage").text("서버와의 통신에 실패했습니다.");
                    $("#resultModal").modal('show');
                }
            });

            $.ajax({
                url: "/ajax/playlistWrite.do",
                type: "POST",
                data: {
                    musicNo: musicNo,
                    albumNo: albumNo
                }
            });
        });

        // 데이터 행 클릭 시 view.do로 이동
        $(".dataRow").click(function() {
            // 관리자가 비공개 음원도 접근할 수 있도록 수정
            if ($(this).hasClass("disabled") && !${login != null && login.gradeNo == 9}) {
                return; // 비활성화된 행의 클릭 이벤트 무시
            }
            let musicNo = $(this).data("music-no");
            if (typeof musicNo === 'undefined') {
                console.error("musicNo is undefined");
                return;
            }
            console.log("Redirecting to view.do?musicNo=" + musicNo); // 로그 확인
            location.href = "view.do?musicNo=" + musicNo; // location.href를 사용하여 페이지 이동
        });
    });
</script>
</head>
<body>
    <div class="container">
        <br> <br>
        <h1>New Music</h1>
        <br> <br>
        <form action="newList.do" id="searchForm">
            <!-- 검색을 하면 무조건 1페이지로 처음에 나오게 설정 -->
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
                        <input type="text" class="form-control" placeholder="검색" id="word"
                            name="word" value="${pageObject.word}">
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
            <table class="table">
                <thead>
                    <!-- 게시판 데이터의 제목 -->
                    <tr>
                        <th >번호</th>
                        <th >제목</th>
                        <th >가수명</th>
                        <c:if test="${!empty login && login.gradeNo == 9}">
                            <th class="title-cell text-center">상태</th>
                        </c:if>
                        <th >담기</th>
                    </tr>
                </thead>
          <tbody>
    <c:forEach items="${newList}" var="vo">
        <c:choose>
            <c:when test="${empty login || login.gradeNo != 9}">
                <c:if test="${vo.musicStatus != '비공개'}">
                    <tr class="dataRow" data-music-no="${vo.musicNo}">
                        <td class="no">&nbsp;&nbsp;${vo.musicNo}</td>
                   <td class="album-image">
                                <div class="image-container">
                                    <img src="${vo.image}" alt="${vo.musicTitle}" style="width: 40px; height: 40px;" />
                                    <span class="music-title">${vo.musicTitle}</span>
                                </div>
                            </td>
                        <td>${vo.singer}</td>
                        <td class="text-center">
                            <span class="btn-add" data-music-no="${vo.musicNo}" data-album-no="${vo.albumNo}">
                                <i class="fa fa-plus"></i>
                            </span>
                        </td>
                    </tr>
                </c:if>
            </c:when>
            <c:otherwise>
                <tr class="dataRow ${vo.musicStatus == '비공개' && (empty login || login.gradeNo != 9) ? 'disabled' : ''}" data-music-no="${vo.musicNo}">
                    <td class="no">&nbsp;&nbsp;${vo.musicNo}</td>
                      <td class="album-image">
                                <div class="image-container">
                                    <img src="${vo.image}" alt="${vo.musicTitle}" style="width: 40px; height: 40px;" />
                                    <span class="music-title">${vo.musicTitle}</span>
                                </div>
                            </td>
                    <td>${vo.singer}</td>
                    <c:if test="${!empty login && login.gradeNo == 9}">
                        <td>${vo.musicStatus}</td>
                    </c:if>
                    <td class="text-center">
                        <span class="btn-add" data-music-no="${vo.musicNo}" data-album-no="${vo.albumNo}">
                            <i class="fa fa-plus"></i>
                        </span>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</tbody>

                <c:if test="${!empty login && login.gradeNo == 9 }">
                    <tr>
                        <td colspan="5">
                            <!-- 등록 버튼 --> 
                            <a href="writeForm.do" class="btn btn-dark float-left">등록</a>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>
        <div class="pageNav">
            <pageNav:pageNav listURI="newList.do" pageObject="${pageObject }" />
        </div>
    </div>
    <!-- 페이지 네이션 처리 -->

    <!-- 결과 모달 -->
    <div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="resultModalLabel">알림</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="modalMessage"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
