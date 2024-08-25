<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container {
    max-width: 900px; /* 원하는 최대 너비로 설정 */
    max-height: 800px; /* 컨테이너의 최대 높이 설정 */
    border: 2px solid rgb(215, 215, 215); /* 테두리 색상과 두께 */
    padding: 10px; /* 테두리와 내용 사이의 여백 */
    border-radius: 5px; /* 테두리 모서리 둥글게 */
    margin-top: 50px;
}
h2 {
    margin-top: 150px;
    border: 2px solid rgba(0, 0, 0, 0.2); 
    padding: 5px;
    border-radius: 5px;
    width: 30%;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
}
td, th {
    text-align: center;
}
th {
    width: 20%; /* th의 너비 설정 */
}
</style>
</head>
<body>
    <h2>상세보기</h2>
    <div class="container">
        <table class="table">
            <tr>
                <th>번호</th>
                <td>${vo.no }</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${vo.title }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td><pre>${vo.content }</pre></td>
            </tr>
            <tr>
                <th>공지기간</th>
                <td>${vo.startDate }&nbsp; ~ &nbsp;${vo.endDate }</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${vo.writeDate }</td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="d-flex justify-content-between">
                        <div>
                            <a href="list.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key }&word=${param.word }" class="btn btn-secondary">리스트</a>
                        </div>
                        <div>
                            <c:if test="${!empty login && login.gradeNo == 9 }">
                                <a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key }&word=${param.word }" class="btn btn-secondary">수정</a>
                                <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#deleteModal">삭제</button>
                            </c:if>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <!-- container의 끝 -->

    <!-- 삭제 경고 모달 -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="deleteModalLabel">삭제 확인</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            정말 삭제하시겠습니까?
          </div>
          <div class="modal-footer">
            <a href="delete.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key }&word=${param.word }" class="btn btn-danger">삭제</a>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
          </div>
        </div>
      </div>
    </div>

</body>
</html>
