<%@ page import="com.musaic.member.db.LoginVO" %>
<%@ page import="com.musaic.music.dao.MusicDAO" %>
<%@ page import="com.musaic.music.vo.MusicVO" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // DAO 객체 생성 및 음악 리스트 가져오기
    MusicDAO musicDAO = new MusicDAO();
    List<MusicVO> musicList = musicDAO.getMusicSortedByHit();
    request.setAttribute("musicList", musicList);

    // 세션에서 로그인 정보 가져오기
   session = request.getSession();
    LoginVO login = (LoginVO) session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top List</title>
<style type="text/css">
.dataRow:hover {
    cursor: pointer;
    background: #eee;
}

.dataRow.disabled {
    background: #f9f9f9;
    cursor: not-allowed;
}

.btn-add {
    cursor: pointer;
    display: inline-block; /* 인라인 블록으로 설정하여 클릭 영역 확대 */
    padding: 10px 15px; /* 버튼의 내외부 여백을 조정하여 클릭 가능한 영역 확대 */
}

.btn-add.disabled {
    pointer-events: none;
    color: #ccc;
}

button:hover {
    cursor: pointer;
}

#word {
    width: 200px; /* 원하는 너비로 조정 */
}

.custom-link {
    color: black;
}

h1 {
    text-align: center;
}

/* 테이블 가운데 정렬 추가 */
.table {
    width: 100%;
    border-collapse: collapse;
}

.table th, .table td {
    text-align: center; /* 가운데 정렬 */
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

.image-container img {
    margin-right: 10px; /* 이미지와 텍스트 사이의 간격 */
}

/* 제목을 가운데 정렬 */
.music-title {
    flex: 1;
    text-align: center;
}
</style>
<script>
    $(document).ready(function() {
        // Tooltip 초기화
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<script type="text/javascript">
    $(function() {
        // 로그인 여부를 확인하는 변수
        var isLoggedIn = "${login != null ? 'true' : 'false'}";

        // 데이터 행 클릭 시 view.do로 이동
        $(".dataRow").click(function() {
            if ($(this).hasClass("disabled")) {
                return; // 비활성화된 행의 클릭 이벤트 무시
            }
            let musicNo = $(this).data("music-no");
            if (musicNo) {
                location.href = "view.do?musicNo=" + musicNo;
            } else {
                console.error("Music number is undefined");
            }
        });

        // 담기 버튼 클릭 시 AJAX 요청 보내기
        $(".btn-add").click(function(event) {
            if ($(this).hasClass("disabled")) {
                return; // 비활성화된 버튼의 클릭 이벤트 무시
            }

            // 이벤트 전파 막기
            event.stopPropagation();
            event.preventDefault();

            if (isLoggedIn === 'false') {
                // 사용자가 로그인하지 않은 상태면, 로그인 필요 모달 표시
                $("#modalMessage").text("로그인 후 이용하실 수 있습니다.");
                $("#resultModal").modal('show');
                return;
            }

            let musicNo = $(this).data("music-no");
            if (!musicNo) {
                console.error("Music number is undefined");
                return;
            }

            $.ajax({
                url: "increaseHit.do",
                type: "POST",
                data: {
                    musicNo: musicNo
                },
                success: function(response) {
                    let modalMessage = response === "success" ? "플레이 리스트 목록에 추가되었습니다."
                        : "플레이 리스트 목록에 담기지 않았습니다.";
                    $("#modalMessage").text(modalMessage);
                    $("#resultModal").modal('show');
                },
                error: function() {
                    $("#modalMessage").text("서버와의 통신에 실패했습니다.");
                    $("#resultModal").modal('show');
                }
            });
        });
    });
</script>
</head>
<body>
    <div class="container">
        <br> <br>
        <h1>TOP 10</h1>
        <br> <br>
        <table class="table">
            <tr>
                <th>순위</th>
                <th>곡정보</th>
                <th>가수명</th>
                <th>담기</th>
            </tr>
         <tbody>
                <c:forEach var="music" items="${musicList}" varStatus="status">
                    <c:if test="${status.index < 10 && music.musicStatus != '비공개'}">
                        <tr class="dataRow" data-music-no="${music.musicNo}"
                            data-toggle="${music.musicStatus == '비공개' ? 'tooltip' : ''}"
                            data-placement="top"
                            title="${music.musicStatus == '비공개' ? '이 곡은 비공개 상태입니다.' : ''}">
                            <td style="font-size: larger; font-family: sans-serif; color: #7F8BA6">
                                <strong>${status.index + 1}</strong>
                            </td>
                            <td class="album-image">
                                <div class="image-container">
                                    <img src="${music.image}" alt="${music.musicTitle}" style="width: 40px; height: 40px;" />
                                    <span class="music-title">${music.musicTitle}</span>
                                </div>
                            </td>
                            <td>${music.singer}</td>
                            <td>
                                <!-- 담기 버튼 -->
                                <span class="btn-add" data-music-no="${music.musicNo}">
                                    <i class="fa fa-plus"></i>
                                </span>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- 결과 모달 -->
    <div class="modal fade" id="resultModal" tabindex="-1" role="dialog"
        aria-labelledby="resultModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="resultModalLabel">결과</h5>
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="modalMessage"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
