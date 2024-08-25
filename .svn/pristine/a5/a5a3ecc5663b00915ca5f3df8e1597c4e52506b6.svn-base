<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
    .container {
        max-width: 1000px; /* 최대 넓이 설정 */
        margin: 0 auto; /* 중앙 정렬 */
        padding: 20px; /* 여백 추가 (선택 사항) */
    }

    .card-body img {
        width: 100%; /* 이미지가 컨테이너 너비에 맞도록 설정 */
        height: auto; /* 이미지 비율을 유지 */
        object-fit: cover; /* 이미지를 컨테이너에 꽉 채움 */
    }

    h2 {
        margin-top: 30px;
        padding: 5px;
        border-radius: 5px;
        width: 30%;
        text-align: center;
        margin-right: auto;
    }

    .btn-group {
        white-space: nowrap;
    }

    .btn-group a {
        margin-right: 10px; /* 버튼 간격 조정 */
    }

    /* 새롭게 추가된 스타일 */
    .btn-list {
        background-color: rgb(210, 217, 233);
        color: black; /* 텍스트 색상 조정 */
        border: 1px solid rgb(200, 210, 220); /* 선택 사항: 경계선 색상 조정 */
    }
    .btn-list:hover {
        background-color: rgb(190, 200, 220); /* 호버 시 색상 조정 */
        color: black; /* 호버 시 텍스트 색상 조정 */
    }
</style>

<script type="text/javascript">
    $(function() {
        $('[data-toggle="tooltip"]').tooltip();

        // 이벤트 처리
        $("#deleteBtn").click(function() {
            // 모달을 띄운다
            $('#deleteModal').modal('show');
        });
    });
</script>
</head>
<body>
    <div class="container">
        <h2><strong>이벤트</strong></h2>
        <div class="card">
            <div class="card-header">
                <b>${vo.no }. ${vo.title }</b> 
                <span class="float-right">
                    Date : ${vo.startDate } &nbsp; ~ &nbsp; ${vo.endDate }
                </span>
            </div>
            <div class="card-body">
                <div class="card">
                    <div class="card-header">
                        <!-- card 아래의 card-img-overlay
                        위쪽의 이미지(또는 class-card-img-top - card 클래스 밖에도 적용)
                        - width 의 기본이 100% -->
                        <img src="${vo.image }" alt="image">
                        <div class="card-img-overlay">
                            <c:if test="${!empty login && login.gradeNo == 9 }">
                                <button type="button" class="btn btn-dark" data-toggle="modal"
                                    data-target="#changeImageModal">사진 변경</button>
                            </c:if>
                        </div>
                    </div>
                    <p class="card-text">
                        <pre>${vo.content }
                                                                                              WriteDate : ${vo.writeDate }</pre>
                    </p>
                </div>
            </div>
            <div class="card-footer text-right">
                <!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. -->
                <c:if test="${!empty login && login.gradeNo == 9 }">
                    <a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
                        class="btn btn-secondary" data-toggle="tooltip"
                        data-placement="top" id="uudateBtn">수정</a>
                    <button class="btn btn-secondary" id="deleteBtn">삭제</button>
                </c:if>
                <a
                    href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
                    class="btn btn-list">리스트</a>
            </div>
        </div>
        <br><br>
        <!-- 댓글 처리 시작 -->
        <jsp:include page="reply.jsp" />
        <!-- 댓글 처리 끝 -->
    </div>
    <!-- container의 끝 -->

    <!-- The Modal for Image Change -->
    <div class="modal" id="changeImageModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">바꿀 이미지 선택하기</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="changeImage.do" method="post"
                    enctype="multipart/form-data">
                    <!-- 숨겨서 넘겨야할 데이터 - 이미지 번호, 현재 파일이름(삭제) -->
                    <input name="no" value="${vo.no }" type="hidden"> 
                    <input name="deleteImage" value="${vo.image }" type="hidden">
                    <!-- 페이지 정보도 넘긴다. -->
                    <input name="page" value="${param.page }" type="hidden"> 
                    <input name="perPageNum" value="${param.perPageNum }" type="hidden">
                    <input name="key" value="${param.key }" type="hidden"> 
                    <input name="word" value="${param.word }" type="hidden">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="imageFile">첨부 이미지</label> 
                            <input id="imageFile" name="imageFile" required class="form-control" type="file">
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button class="btn btn-primary">바꾸기</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- The Modal for Delete Confirmation -->
    <div class="modal" id="deleteModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">삭제 확인</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <p>정말 삭제 하시겠습니까?</p>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <a id="confirmDelete"
                        href="delete.do?no=${vo.no }&deleteImage=${vo.image}&perPageNum=${param.perPageNum}"
                        class="btn btn-danger">삭제</a>
                    <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
