<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.album-container {
    padding: 20px;
}

.album-card-container {
    position: relative; /* 부모 요소에 상대 위치 지정 */
    overflow: hidden; /* 자식 요소가 부모를 넘지 않도록 설정 */
    border-radius: 8px; /* 카드와 이미지를 원형으로 만들기 위해 동일한 라운딩 적용 */
}

.album-card {
    border: none;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s, box-shadow 0.2s;
    cursor: pointer; /* 클릭 가능임을 나타내기 위한 커서 스타일 */
    position: relative;
    z-index: 1; /* 이미지와 카드가 겹치지 않도록 카드가 위에 오도록 설정 */
}

.album-card-container:hover .album-card {
    transform: translateY(-10px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.album-image {
    width: 90%; /* 기본 상태에서 이미지를 더 작게 설정 */
    height: auto;
    border-radius: 50%; /* 원형으로 만들기 */
    margin-bottom: 15px; /* 카드와 이미지 사이의 여백 */
    transition: transform 0.2s, opacity 0.2s; /* 이미지에도 호버 효과 적용 */
    cursor: pointer; /* 호버 시 커서 포인터 스타일 */
    display: block; /* 기본 상태에서 블록 요소로 설정하여 중앙 정렬 */
    margin: 0 auto; /* 중앙 정렬 */
}

.album-card-container:hover .album-image {
    transform: scale(1.1); /* 호버 시 이미지 확대 효과 */
    opacity: 0.8; /* 호버 시 이미지 투명도 감소 */
}

.album-card-body {
    padding: 15px;
}

.album-card-title {
    font-size: 1.25rem;
    margin-bottom: 0.5rem;
}

.album-card-text {
    font-size: 1rem;
    color: #555;
}

.album-release-date {
    font-size: 0.875rem;
    color: #888;
}

.album-card-price {
    font-weight: bold; /* 가격을 볼드체로 설정 */
    margin-top: 10px; /* 가격과 HR 선 사이에 여백 추가 */
}

.album-card-hr {
    margin: 10px 0; /* HR 선과 다른 요소 사이의 여백 설정 */
}
.album-no {
    display: none; /* 앨범 번호 숨기기 */
}
</style>

<h1 class="text-center mt-5" style="margin-bottom: 50px; color: #9FAED0;">Album</h1><br>

<c:if test="${empty albumList}">
    <div class="jumbotron text-center">
        <h4>데이터가 존재하지 않습니다.</h4>
    </div>
</c:if>

<c:if test="${!empty albumList}">
    <div class="container album-container">
        <div class="row">
            <c:forEach items="${albumList}" var="vo" varStatus="vs">
                <c:if test="${(vs.index != 0) && (vs.index % 3 == 0)}">
                    </div>
                    <div class="row">
                </c:if>
                <div class="col-md-4 mb-4">
                    <!-- 카드와 이미지의 공통 부모 요소 -->
                    <div class="album-card-container">
                        <!-- 카드 외부에 이미지를 배치 -->
                        <img class="album-image" src="${vo.image}" alt="Album image" data-album-no="${vo.albumNo}">
                        <div class="card album-card" data-album-no="${vo.albumNo}">
                            <div class="album-card-body" style="text-align: center;">
                                <h5 class="card-title album-card-title">
                                    ${vo.title}
                                </h5>
                                <p class="card-text album-card-text">
                                    <span class="album-no">${vo.albumNo}</span> <!-- 앨범 번호 숨기기 -->
                                    ${vo.artist}
                                </p>
                                <hr class="album-card-hr"> <!-- 가격 위에 HR 선 추가 -->
                                <p class="card-text album-card-price">
                                    <span class="price">${vo.price}</span>원 <!-- 가격 표시 -->
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<script type="text/javascript">
    $(document).ready(function() {
        // 가격 포맷 함수
        function formatPrice(price) {
            return Number(price).toLocaleString();
        }

        // 각 가격을 포맷하여 표시
        $('.price').each(function() {
            var unformattedPrice = $(this).text().replace(/,/g, ''); // 기존 가격에서 쉼표 제거
            $(this).text(formatPrice(unformattedPrice)); // 포맷된 가격으로 업데이트
        });

        // 클릭 시 페이지 이동
        $('.album-image, .album-card').on('click', function() {
            let albumNo = $(this).data('albumNo');
            if (albumNo) {
                window.location.href = '/album/view.do?no=' + albumNo;
            }
        });
    });
</script>