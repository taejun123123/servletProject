<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CartAlbum list</title>

<!-- 스타일 정의 -->
<style type="text/css">
/* 입력 그룹 스타일 */
.input-group {
    display: flex;
    align-items: center;
    margin-right: 10px; /* 수량 조절바 여백 추가 */
}

/* 입력 그룹의 버튼 스타일 */
.input-group-prepend,
.input-group-append {
    display: flex;
}

/* 버튼의 최소 너비 설정 */
.input-group-prepend button,
.input-group-append button {
    min-width: 40px;
}

/* 입력 필드 텍스트 정렬 */
.form-control {
    text-align: center;
}

/* 체크박스 크기 조정 */
.custom-checkbox {
    transform: scale(1.5); /* 체크박스 크기 조정 */
}

/* 장바구니 제목 아래 여백 설정 */
#shopping-cart-title {
    margin-bottom: 20px; /* 제목 아래 여백 추가 */
}

/* 앨범 항목 스타일 */
.album-item {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

/* 앨범 이미지 크기 및 정렬 */
.album-item img {
    height: 200px;
    width: 200px;
    object-fit: cover;
    margin-right: 20px;
}

/* 앨범 정보의 여백 설정 */
.album-item p {
    margin: 0 10px;
}

/* 전체 컨테이너의 패딩 설정 */
.container {
    padding: 20px;
}

/* 입력 그룹의 최대 너비 설정 */
.input-group {
    max-width: 200px;
}

/* 페이지네이션 컨테이너 스타일 */
.pagination-container {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}

/* 페이지네이션 링크 스타일 */
.pagination-container a {
    margin: 0 5px;
    padding: 10px 15px;
    background-color: #343a40;
    color: white;
    text-decoration: none;
    border-radius: 5px;
}

/* 페이지네이션 링크의 호버 효과 */
.pagination-container a:hover {
    background-color: #495057;
}

/* 현재 페이지 링크 스타일 */
.pagination-container .active {
    background-color: #495057;
}

/* 버튼 그룹의 여백 및 간격 설정 */
.button-group {
    display: flex;
    gap: 10px;
    margin-top: 20px;
}
</style>

<!-- JavaScript 코드 -->
<script type="text/javascript">
$(function() {
    // 선택한 항목을 삭제하는 함수
    $("#delete-albums").click(function () {
        // 체크된 체크박스에서 cartNo 값을 가져와서 문자열로 변환
        let checkedValues = Array.from($(".checkbox:checked")).map(function (el) { return $(el).parent().find("input[name=cartNo]").val() });
        let checkedValuesString = checkedValues.join(",");
        // 삭제 요청을 서버로 전송
        location = "delete.do?checkedCartNos=" + checkedValuesString;
    });
    

 // 수량 감소 버튼 클릭 시 처리
    $(".minus").click(function() {
        // 클릭된 감소 버튼의 가장 가까운 부모 요소 중 .album-item 클래스를 가진 요소를 찾음
        let albumItem = $(this).closest(".album-item");
        
        // 해당 앨범 아이템 내의 수량 입력 필드에서 현재 수량 값을 가져옴
        let albumCnt = albumItem.find(".quantity").val();
        
        // 현재 수량이 2 이상인 경우에만 수량 감소 요청을 처리
        if(albumCnt >= 2) {
            // 앨범 아이템 내에서 cartNo 값을 가진 input 요소를 찾아 그 값을 가져옴
            let no = albumItem.find("input[name=cartNo]").val();
            
            // 서버로 수량 감소 요청을 전송
            // minus.do?cartNo=로 cartNo 값을 포함하여 페이지 이동
            location = "minus.do?cartNo=" + no;
        }
    });

    

    // 수량 증가 버튼 클릭 시 처리
    $(".add").click(function() {
        // 현재 앨범의 cartNo 값을 가져와서 수량 증가 요청을 서버로 전송
        let no = $(this).closest(".album-item").find("input[name=cartNo]").val();
        location = "add.do?cartNo=" + no;
    });

    // 체크박스 상태 변경 시 총 가격 계산
    $(".checkbox").change(function() {
        priceOpp();
    });

    // 주문하기 버튼 클릭 시 선택된 항목의 cartNo를 폼에 추가하고 제출
    $(".btnSubmit").click(function() {
        $(".checkbox:checked").each(function() {
            let cartNo = $(this).closest(".album-item").find(".cartNo").val();
            let hiddenCartNo = `<input type="hidden" name="cartNo" value="\${cartNo}">`;
            $("#inputHidden").append(hiddenCartNo);
        });
        $("#toPayForm").submit();
    });

    // 체크박스 선택 시 가격 계산
    function priceOpp() {
        let totalPrice = 0;

        // 선택된 항목의 총 가격 계산
        $(".checkbox:checked").each(function() {
            let albumItem = $(this).closest(".album-item");
            let albumCnt = albumItem.find(".quantity").val();
            totalPrice += parseInt(albumItem.find(".price").text()) * parseInt(albumCnt);
        });

        // 총 가격 및 배송비 계산
        $("#totalPrice").text(totalPrice);
        let totalShippingCost = (totalPrice > 50000) ? 0 : 3000;
        $("#shippingCost").text(totalShippingCost);
        $("#totalAmount").text(totalPrice + totalShippingCost);
    }

    // 페이지 로드 시 가격 계산
    $(document).ready(function () {
        priceOpp();
    });
});
</script>
</head>
<body>

<!-- 장바구니 목록 표시를 위한 컨테이너 시작 -->
<div class="container">
    <h3 id="shopping-cart-title">장바구니</h3>
</div> 

<!-- 로그인이 안 된 경우 로그인 요청 후 장바구니 이용 -->
<c:if test="${empty login}">
    <div class="container">
        <div class="alert alert-warning">
            장바구니를 이용하시려면 로그인 해주세요!
            <br>
            <br> <!-- 줄바꿈과 간격을 주기 위한 두 개의 <br> 태그 -->
            <a href="/member/loginform.do" class="btn btn-primary">로그인</a>
        </div>
    </div>
</c:if>

<c:if test="${!empty login}">
    <!-- 장바구니가 비어있는 경우 메시지 표시 -->
    <c:if test="${empty list}">
        <div class="container">
            <div class="alert alert-warning">
                장바구니가 비어있습니다.
                <br>
                <br> <!-- 줄바꿈과 간격을 주기 위한 두 개의 <br> 태그 -->
                <a href="/album/list.do" class="btn btn-primary">앨범 담으러 가기</a>
            </div>
        </div>
    </c:if>

    <!-- 장바구니 항목 및 금액 정보 섹션 -->
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-12 d-flex justify-content-between align-items-center">
                <div>
                    <!-- 장바구니 항목 반복 -->
                    <c:forEach items="${list}" var="vo" varStatus="vs">
                        <div class="album-item">
                            <!-- 장바구니 항목의 cartNo와 albumNo를 숨겨진 필드로 저장 -->
                            <input type="hidden" name="cartNo" class="cartNo" value="${vo.cartNo}">
                            <input type="hidden" name="albumNo" value="${param.albumNo}" class="albumNo">
                            <br>
                            <!-- 항목의 순서 표시 -->
                            <span style="margin-right: 1em;">${vs.count}</span>
                            <!-- 선택 체크박스 -->
                            <input type="checkbox" class="custom-checkbox checkbox" style="margin-right: 1em;">
                            <!-- 앨범 이미지 -->
                            <img alt="" src="${vo.image}">
                            <div>
                                <pre>앨범명 : ${vo.title}</pre>
                                <pre>가격 : <span class="price">${vo.price}</span>원</pre>
                            </div>
                            <!-- 수량 조절 -->
                            <div class="count-class"> 
                                <div class="input-group mr-auto" style="margin-left: 1em;">
                                    <div class="input-group-prepend">
                                        <!-- 수량 감소 버튼 -->
                                        <a class="btn btn-outline-secondary btn-subtract minus" type="button">-</a>
                                    </div>
                                    <!-- 수량 입력 필드 -->
                                    <input type="text" class="form-control quantity-input quantity" value="${vo.albumCnt}" readonly>
                                    <div class="input-group-append">
                                        <!-- 수량 증가 버튼 -->
                                        
                                        <a class="btn btn-outline-secondary add" type="button">+</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- 로그인된 경우 금액 정보 및 버튼 섹션 표시 -->
                    <c:if test="${!empty list}">
                        <div class="container">
                            <div class="d-flex align-items-center">
                                <div>
                                    상품금액 : <span id="totalPrice"></span>원<br>
                                    배송비 : <span id="shippingCost"></span>원<br>
                                    총 주문금액 : <span id="totalAmount"></span>원<br>
                                </div>
                            </div>
                            <!-- 버튼 그룹 -->
                            <div class="button-group ml-auto">
                                <!-- 선택 항목 삭제 버튼 -->
                                <input type="hidden" name="totalAmount" value="0">
                                <input id="delete-albums" type="button" class="btn btn-dark" value="선택한 항목 삭제">
                                <!-- 주문하기 버튼 -->
                                <form action="/pay/itemlist.do" method="get" id="toPayForm">
                                    <div id="inputHidden"></div>
                                    <input type="button" class="btn btn-dark btnSubmit" value="주문하기">
                                </form>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- 페이지네이션 처리 -->
<div class="container mt-3">
    <div class="row">
       <div>
        <pageNav:pageNav listURI="list.do" pageObject="${pageObject}"></pageNav:pageNav>
    </div>
    </div>
</div>
</body>
</html>
