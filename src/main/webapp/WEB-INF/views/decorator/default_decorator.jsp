<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 함수진 -->
<!-- 작성일 : 2024-06-28 -->
<!-- 최종수정일 : 2024-07-19 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 개발자가 작성한 title을 가져다가 사용 -->
<title>Musaic:<decorator:title /></title>
<!-- Bootstrap 4 + jquery 라이브러리 등록 - CDN 방식 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 아이콘 라이브러리(부트스트랩) -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style type="text/css">
/* 구글 폰트 */
@import url('https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap');

.dm-sans-navbar-nav {
  font-family: "DM Sans", sans-serif;
  font-optical-sizing: auto;
  font-weight: <weight>;
  font-style: normal;
}


.navbar {
    margin-bottom: 0;
    border-radius: 0;
}

footer {
    background-color: #f2f2f2;
    padding: 25px;
}

article {
    min-height: 795px;
    margin-top: 60px;
}

.nav-item {
    margin: 0 10px;
}

.container {
    text-align: left;
    padding: 30px 0;
}

.bg-custom {
    background-color: #d2d9e9;
}

.nav-link {
    color: #444 !important;
    font-weight: bold;
}

.dropdown-menu-right {
    right: 0;
    left: auto;
}

.dropdown {
    margin-left: 50px;
}

#scrollTopBtn {
    display: none;
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 99;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 50%;
    width: 50px;
    height: 50px;
    cursor: pointer;
}

</style>
<script>
// TOP 버튼 이벤트
document.addEventListener('DOMContentLoaded', function() {
    var scrollTopBtn = document.getElementById("scrollTopBtn");

    window.addEventListener('scroll', function() {
        var scrollPosition = window.scrollY || document.documentElement.scrollTop;

        if (scrollPosition > 20) {
            scrollTopBtn.style.display = "block";
        } else {
            scrollTopBtn.style.display = "none";
        }
    });
	// TOP 버튼 이벤트 스크롤 상세 설정
    scrollTopBtn.addEventListener("click", function() {
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    });
});
</script>
<decorator:head />
</head>
<body>
<header>
	<!-- TOP 버튼 -->
    <button id="scrollTopBtn" title="Go to top">Top</button>
    <!-- 네비게이션 메뉴바 (로고 이미지)-->
<nav class="navbar navbar-expand-lg bg-custom fixed-top">
    <a class="navbar-brand ${(empty module || module == '/main')?'active':'' }" href="/main/main.do">
        <img src="/img/logo.png" alt="Logo" style="width: 80px; margin-left: 50px">
    </a>
    <!-- 모바일 화면시 보이는 햄버거 버튼 -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="fa fa-align-justify" style="color: white"></span>
    </button>
    <!-- 네비게이션 메뉴바 -->
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">
            <li class="nav-item ${( module == '/playList')?'active':'' }"><a class="nav-link" href="/playlist/list.do">PLAYLIST</a></li>
            <li class="nav-item ${( module == '/album')?'active':'' }"><a class="nav-link" href="/album/list.do">ALBUM</a></li>
            <li class="nav-item ${( module == '/music')?'active':'' }"><a class="nav-link" href="/music/newList.do" >NEWMUSIC</a></li>
            <li class="nav-item ${( module == '/music')?'active':'' }"><a class="nav-link" href="/music/topList.do" >TOP10</a></li>
            <li class="nav-item ${( module == '/event')?'active':'' }"><a class="nav-link" href="/event/list.do" >EVENT</a></li>
            <li class="nav-item ${( module == '/notice')?'active':'' }"><a class="nav-link" href="/notice/list.do">NOTICE</a></li>
            <c:if test="${!empty login && login.gradeNo == 9 }">
                <li class="nav-item ${( module == '/member')?'active':'' }"><a class="nav-link" href="/member/list.do" >MEMBER</a></li>
                <li class="nav-item ${( module == '/pay')?'active':'' }"><a class="nav-link" href="/pay/adminList.do" >PAY</a></li>
            </c:if>
        </ul>
        <!-- 회원 관리 드롭다운 메뉴 -->
         <ul class="navbar-nav">
                <c:if test="${ empty login }">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user-circle-o" style="font-size: 30px"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/member/writeform.do">Join</a>
                            <a class="dropdown-item" href="/member/loginform.do">Login</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${ !empty login }">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${empty login.photo}">
                                <i class="fa fa-user-circle-o" style="font-size: 30px"></i>
                            </c:if>
                            <c:if test="${!empty login.photo}">
                                <img src="${login.photo}" class="rounded-circle" style="width: 30px; height: 30px;">
                            </c:if>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/member/view.do?id=${login.id }"><i class="fa fa-address-card-o"></i> MyPage</a>
                            <a class="dropdown-item" href="/cartalbum/list.do"><i class="fa fa-shopping-cart"></i> Cart</a>
                            <a class="dropdown-item" href="/pay/payOrderList.do"><i class="material-icons" style="font-size:20px">payment</i> Pay </a>
                            <a class="dropdown-item" href="/member/logout.do"><i class="fa fa-sign-out"></i> Logout</a>
                        </div>
                    </li>
                </c:if>
            </ul>
    </div>
</nav>
</header>

<article>
    <decorator:body />
</article>
<footer class="container-fluid text-center fixed" style="background: #d2d9e9;">
    <p style="color: white">Copyright © 2024 Musaic All right reserved.</p>
</footer>
<!-- 처리 결과 모달창 -->
<c:if test="${ !empty msg }">
    <div class="modal" id="msgModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">처리 결과</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">${msg }</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 모달창 이벤트 -->
    <script type="text/javascript">
        $(function() {
            $("#msgModal").modal("show");
        });
    </script>
</c:if>
</body>
</html>
<!-- 세션 지우기 -->
<%
session.removeAttribute("msg");
%>
