<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 리스트</title>
<style type="text/css">
 table {
     width: 100%;
     border-collapse: collapse;
     table-layout: auto; /* Ensures the table takes up the full width */
 }
 th, td {
     border: 1px solid #ccc;
     padding: 10px;
     text-align: center;
     white-space: nowrap; /* Prevents text from wrapping */
 }
 th {
     background-color: #f0f4f7;
 }
 .highlight {
     font-weight: bold;
 }
.horizontal-line {
    width: 99%; /* 선의 길이 조절 */
    margin: 0 auto; /* 중앙 정렬 */
    border-top: 1px solid rgba(0, 0, 0, 0.1); /* 매우 희미한 선 */
    margin-top: 10px;
    margin-bottom: 10px;
}
.payStatus{
margin-right: 40px;
margin-left: 10px;
}
</style>
 <script type="text/javascript">
   $(function(){
       $("input[name='paySatus']").change(function(){
    	   $(".paySatusForm").submit();
       });

       $("input[name='payMeans']").change(function(){
    	   $(".paySatusForm").submit();
       });
   });    </script>
</head>
<body>
<div class="container">
	<h2>결제 관리자 리스트</h2>
	<div class="horizontal-line"></div> <!-- 얇은 선  -->	
	<!-- 결제 정렬 ridio 버튼 -->
	<form action="paySatus.do" method="post" class="paySatusForm"> <!-- 주문 상태 데이터 전송 부분 -->
	<span class="payStatus"><b>주문 상태</b></span>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="paySatus" value="전체">전체
	  </label>
	</div>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="paySatus" value="준비">준비
	  </label>
	</div>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="paySatus" value="취소">취소
	  </label>
	</div>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="paySatus" value="완료">완료
	  </label>
	</div>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="paySatus" value="배송중">배송중
	  </label>
	</div>
	</form>
	<!-- 결제 수단 끝 -->
	<!-- 결제 정렬 ridio 버튼 끝 -->
	<div class="horizontal-line"></div> <!-- 얇은 선  -->	
	<table class="table">
        <thead>
            <tr>
                <th>주문번호</th>
                <th>주문자</th>
                <th>주문자전화</th>
                <th>받는분</th>
                <th>주문상태</th>
                <th>결제수단</th>
                <th class="highlight">주문금액<br>선불/송비포함</th>
                <th class="highlight">입금확인</th>
                <th>쿠폰</th>
                <th>미수금</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${list }" var="vo">
            <tr>
                <td>${vo.payNo }</td>
                <td>${vo.name }</td>
                <td>${vo.phone }</td>
                <td>${vo.name }</td>
                <td>   
					<form action="changeStatus.do">
					<input name="payNo" value="${vo.payNo}" type="hidden">
					<div class="input-group mb-3">
				 	 <select class="form-control" name="status">
				  	<option ${(vo.status=='준비')?"selected":""}>준비</option> <!-- 데이터가 많으면 저렇게 써야됨 데이터가 한개면 jquery 가능 -->
				  	<option ${(vo.status=='취소')?"selected":""}>취소</option>
				  	<option ${(vo.status=='완료')?"selected":""}>완료</option>
				  	<option ${(vo.status=='배송중')?"selected":""}>배송중</option>
				  	</select>
				  <div class="input-group-append">
				    <button class="btn btn-success grade" type="submit">변경</button>
				  </div>
				</div>
				</form>
			 	 </td>
               <c:if test="${!empty vo.cardNum }">
                <td>신용카드</td>
                </c:if>
                <c:if test="${!empty vo.payTel }">
                <td>휴대폰 결제</td>
                </c:if>
                <c:if test="${!empty vo.bankNum }">
                <td>계좌이체</td>
                </c:if>
                <td>${vo.cartPrice }</td>
                <td>확인</td>
                <td>미사용</td>
                <td>0</td>
            </tr>
          </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>