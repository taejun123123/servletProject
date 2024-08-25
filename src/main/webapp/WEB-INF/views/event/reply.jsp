<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
$(function() {
    // 이벤트 처리 
    // ---------------- Modal 화면 이벤트 처리 ----------------
    // 댓글 등록 이벤트
    $("#replyWriteBtn").click(function() {
        $("#eventReplyModal").find(".modal-title").text("댓글 등록");
        $("#eventReplyForm").find(".form-group").show();
        $("#eventReplyForm").find(".form-group input, .form-group textarea").val("");
        $("#eventReplyForm button").show();
        $("#replyModalUpdateBtn, #replyModalDeleteBtn").hide();
        $("#eventReplyModal").modal("show");
    });

    // 댓글 수정 이벤트
    $(".replyUpdateBtn").click(function() {
        $("#eventReplyModal").find(".modal-title").text("댓글 수정");
        $("#eventReplyForm").find(".form-group input, .form-group textarea").val("");
        let replyDataRow = $(this).closest(".replyDataRow");
        let rno = replyDataRow.data("rno");
        let content = replyDataRow.find(".replyContent").text();
        $("#rno").val(rno);
        $("#content").val(content);
        $("#eventReplyForm").find(".form-group").show();
        $("#eventReplyForm button").show();
        $("#replyModalWriteBtn, #replyModalDeleteBtn").hide();
        $("#eventReplyModal").modal("show");
    });

    // 댓글 삭제 이벤트
    $(".replyDeleteBtn").click(function() {
        $("#eventReplyModal").find(".modal-title").text("댓글 삭제");
        $("#eventReplyForm").find(".form-group").hide();
        let replyDataRow = $(this).closest(".replyDataRow");
        let rno = replyDataRow.data("rno");
        $("#rno").val(rno);
        $("#eventReplyModal .modal-body").text("정말 삭제 하시겠습니까?");
        $("#eventReplyForm button").show();
        $("#replyModalUpdateBtn, #replyModalWriteBtn").hide();
        $("#eventReplyModal").modal("show");
    });
    
    // ---------------- Modal 화면 안의 처리버튼 이벤트 처리 ----------------
    $("#replyModalWriteBtn").click(function () {
        $("#eventReplyForm").attr("action", "/eventreply/write.do");
        $("#eventReplyForm").submit();
    });

    $("#replyModalUpdateBtn").click(function () {
        $("#eventReplyForm").attr("action", "/eventreply/update.do");
        $("#eventReplyForm").submit();
    });
    
    $("#replyModalDeleteBtn").click(function () {
        $("#eventReplyForm").attr("action", "/eventreply/delete.do");
        $("#eventReplyForm").submit();
    });
});

</script>

<div class="card">
   <div class="card-header">
      <c:if test="${ !empty login }">
         <span class="btn btn-dark float-right" id="replyWriteBtn">등록</span>
      </c:if>
      <h3>댓글 리스트</h3>
   </div>
   <div class="card-body">
      <c:if test="${ empty replyList }">
         데이터가 존재하지 않습니다.
      </c:if>
      <c:if test="${ !empty replyList }">
         <!-- 데이터가 있는 만큼 반복 처리 시작 -->
         <c:forEach items="${replyList }" var="replyVO">
            <div class="card replyDataRow" data-rno="${replyVO.rno }" style="margin: 5px 0;">
               <div class="card-header">
                  <span class="float-right">${replyVO.writeDate }</span> 
                  <c:choose>
                     <c:when test="${login.gradeNo == 9}">
                        <b class="replyid">${replyVO.id}</b>
                     </c:when>
                     <c:otherwise>
                        <b class="replyid">
                           ${fn:substring(replyVO.id, 0, 2)}***
                        </b>
                     </c:otherwise>
                  </c:choose>
               </div>
               <div class="card-body">
                  <pre class="replyContent">${replyVO.content }</pre>
               </div>
               <c:if test="${!empty login}">
   <div class="card-footer">
      <!-- 댓글 작성자일 경우 수정 및 삭제 버튼을 보여줌 -->
      <c:if test="${login.id == replyVO.id}">
         <button class="btn btn-dark replyUpdateBtn">수정</button>
      </c:if>
      <!-- 관리자일 경우 삭제 버튼을 보여줌 -->
      <c:if test="${login.gradeNo != 9 && login.id == replyVO.id}">
         <button class="btn btn-dark replyDeleteBtn">삭제</button>
      </c:if>
      <!-- 관리자일 경우 삭제 버튼을 보여줌 -->
      <c:if test="${login.gradeNo == 9}">
         <button class="btn btn-dark replyDeleteBtn">삭제</button>
      </c:if>
   </div>
</c:if>

            </div>
         </c:forEach>
         <!-- 데이터가 있는 만큼 반복 처리 끝 -->
      </c:if>
   </div>
   <div class="card-footer" style="display: flex; justify-content: center; margin-top: 20px;">
      <pageNav:replayPageNav listURI="view.do" pageObject="${replyPageObject }" query="&inc=0"></pageNav:replayPageNav>
   </div>
</div>

<!-- 댓글 등록 / 수정 / 삭제를 위한 모달창 -->
<div class="modal" id="eventReplyModal">
   <div class="modal-dialog">
      <div class="modal-content">
         <!-- Modal Header -->
         <div class="modal-header">
            <h4 class="modal-title">Modal Heading</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
         </div>
         <!-- 데이터를 넘기는 form -->
         <form id="eventReplyForm" method="post">
            <input type="hidden" name="rno" id="rno"> 
            <input type="hidden" name="no" value="${param.no }">
            <!-- 페이지 정보 보내기 -->
            <input type="hidden" name="page" value="${param.page }">
            <input type="hidden" name="perPageNum" value="${param.perPageNum }">
            <input type="hidden" name="key" value="${param.key }">
            <input type="hidden" name="word" value="${param.word }">
            <!-- Modal body -->
            <div class="modal-body">
               <div class="form-group" id="contentDiv">
                  <label for="content">내용</label>
                  <textarea class="form-control" rows="3" id="content" name="content" placeholder="이벤트와 무관한 댓글을 입력시 관리자에 의해 삭제 될 수 있습니다."></textarea>
               </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
               <button class="btn btn-dark" type="button" id="replyModalWriteBtn">등록</button>
               <button class="btn btn-primary" type="button" id="replyModalUpdateBtn">수정</button>
               <button class="btn btn-danger" type="button" id="replyModalDeleteBtn">삭제</button>
               <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
            </div>
         </form>
      </div>
   </div>
</div>
