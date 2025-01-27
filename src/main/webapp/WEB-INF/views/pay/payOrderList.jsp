<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 목록</title>
<style type="text/css">
.center-text {
     text-align: center;
     font-size: 24px; /* 글자 크기를 조절 */
     font-weight: bold; /* 글자 굵게 */
     margin-bottom: 20px; /* 텍스트와 선 사이의 간격 */
 }
 .cartimage {
     width: 50px; /* 원하는 너비로 조정 */
     height: 50px; /* 원하는 높이로 조정 */
     object-fit: cover; /* 이미지 비율 유지하면서 크기 맞추기 */
 }
 .dataRow:hover{
 background: #ddd;
 cursor: pointer;
 }
 .payNo{
 display: none;
 }
</style>
<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
	
	let payNo =$(this).find(".payNo").text();
	//alert(payNo);
	location = "payView.do?payNo="+payNo;
	});
	
});
</script>
</head>
<body>
    <div class="container">
        <div class="center-text">주문 목록</div> 
        <table class="table">
            <tr>
            	<th>주문 날짜</th>
                <th class="payNo">주문 번호</th>
                <th>이미지</th>
                <th>제목</th>
                <th>가격</th>
                <th>수량</th>
            </tr>
            <c:forEach items="${list}" var="vo">
            <tr class="dataRow">
            	<td>${vo.today }</td>
                <td class="payNo">${vo.payNo}</td>
                <td><img alt="없습니다." src="${vo.cartImage}" class="cartimage"></td>
                <td>${vo.cartTitle}</td>
                <td>${vo.price}</td>
                <td>${vo.albumCnt}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
