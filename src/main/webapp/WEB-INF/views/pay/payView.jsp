<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 보기</title>
<style type="text/css">
  /* 글씨 옆으로 두기 */
  .albumPont {
    margin-left: 80px; /* 원하는 만큼 조정 */
  }
#PaySubmitBtn{
         background-color: #dddddd;
         color: black;
         border: 2px solid black;
         width: 100%;
         height: 40px;
         font-size: 16px;
         cursor: pointer;
         border-radius: 5px; /* 모서리를 둥글게 설정 */
         text-align: center;
         display: block;
         margin: 0 auto;
}
#PaySubmitBtn:hover{
 background-color: #e6e6e6;
}

.center-text {
     text-align: center;
     font-size: 24px; /* 글자 크기를 조절 */
     font-weight: bold; /* 글자 굵게 */
     margin-bottom: 20px; /* 텍스트와 선 사이의 간격 */
 }
 .linePayWriteForm{
   border: 2px solid #000; /* 테두리 두께와 색상 설정 */
    padding: 20px; /* 내부 여백 설정 */
    border-radius: 30px; /* 모서리를 둥글게 설정 */ 
    margin-bottom: 30px; 
}
.fontStatus{
	margin-top: 10px;
	font-size: 20px; 
	
}
.cartimage{ 
/*   이미지 크기 조정  */
    width: 80px;
    height: 80px;
    object-fit: cover; /* 이미지 비율 유지하면서 크기 맞추기 */
  
  }
.text-container {
  display: inline-block;
 vertical-align: top;
 margin-left: 15px; /* 텍스트를 이미지에서 떨어뜨림 */
}
 .text-container i {
   display: block;
   font-size: 15px; /* 원하는 글씨 크기로 조정 */
   margin-bottom: 20px; /* 텍스트 사이의 간격 조정 */
 }
 .text-container b {
   font-size: 5x; /* 원하는 글씨 크기로 조정 */
    margin-left: 0px; /* b 태그를 왼쪽으로 이동 */
 }
 .addSum {
 	margin-top: 10px;
   font-size: 32px; /* 글씨 크기 */
   padding: 20px; /* 내부 여백 크기 */
   background-color: #D2D9E9; /* 배경색 */
   color: #004085; /* 글씨 색상 */
   border-radius: 5px; /* 모서리 둥글게 */
 }
 .addSum strong {
   font-size: 32px; /* strong 태그 글씨 크기 */
 }
.inline-block {
    display: inline-block;
}
.addressName{ /*배송지 이름*/
 margin-left: 140px;
}
.addressTel{ /*배송지 전화번호*/
 margin-left: 160px;
}
.address{ /*배송지*/
 margin-left: 175px;
}
.payMeans{  /*결제 수단*/
margin-bottom: 30px;
}
.marginLine{
margin-top: 20px;
}
.payMeans{
margin-left: 50px;
}
.horizontal-line {
    width: 99%; /* 선의 길이 조절 */
    margin: 0 auto; /* 중앙 정렬 */
    border-top: 1px solid rgba(0, 0, 0, 0.1); /* 매우 희미한 선 */
}
.flexClass{
           display: flex;
            flex-direction: column;
            max-width: 300px; /* 원하는 너비로 설정 */

}
.flexSpan{
            display: flex;
            justify-content: space-between;
            margin-bottom: 5px;

}
</style>
<script type="text/javascript">
$(function(){
	$("#home").click(function(){
		location ="/";
	});
});
</script>
<script type="text/javascript">
$(function(){
	 let totalPrice = 0;
	 let deliverys = 0;
	$(".flexClass").each(function(){
	      let payMeans = $(this);
          let price = parseInt(payMeans.find(".totalPrice").text(), 10);
          deliverys = parseInt(payMeans.find(".charge").text(), 10);      
          deliverys =  (price > 50000) ? 0 : 3000;
          
          totalPrice = price -deliverys;
          payMeans.find(".totalPrice").text(totalPrice);
          payMeans.find(".charge").text(deliverys);
          
	});
	

});

</script>
</head>
<body>

	<div class="container">
		<div class="center-text">주문 상세보기</div> 
		<div class="linePayWriteForm">  <!-- 테두리 만듬  -->
		<p>주문일:${vo.payToday }</p> <!-- 날짜 -->
		<b>주문번호:${vo.payNo }</b> <!-- 주문 번호 -->
		</div>
		
		<h3>주문상품</h3> <!-- 제목 -->
		<div class="linePayWriteForm">  <!-- 테두리 만듬  -->	
		<b class="fontStatus">주문상태:${vo.status }</b>
		<c:forEach items="${order }" var="orderVo">
		<div class="goods">
		<img alt="없습니다." src="${orderVo.cartImage }" class="cartimage">
		<div class="text-container">
		<i>${orderVo.cartTitle }</i>수량:${orderVo.albumCnt }개
		<div>
		<b class="albumPont">${orderVo.cartPrice }원</b>
		</div>
		</div>
		</div>
		</c:forEach>
		
		 <div class="alert alert-primary">
  		 	<strong>총 가격:</strong>${vo.cartPrice }원 
  		</div>
		<button type="submit"class="btn btn-dark" id="PaySubmitBtn"data-toggle="modal" data-target="#cancel">취소 하기</button>
		</div><!-- linePayWriteForm 끝 -->
		<h3>받는 사람 정보</h3>	
		<div class="linePayWriteForm">  <!-- 테두리 만듬  -->
		<p class="inline-block">받는 사람</p><b class="addressName">${vo.name }</b><br>
		<p class="inline-block">연락처</p><b class="addressTel">${vo.phone }</b><br>
		<p class="inline-block">주소</p><b class="address">${vo.address }</b><br>
		</div>
		<h3>결제 정보</h3>
		<div class="linePayWriteForm">  <!-- 테두리 만듬  -->
		<b>결제 수단</b>
		<div class="marginLine"></div>	
		<div class=row>
	        <div class="col-md-9">
	        <!-- 신용카드 확인  -->
	        <c:if test="${!empty vo.cardBank && !empty vo.cardType}">
			<span>${vo.cardBank } 은행
			<span class="payMeans">${vo.cardType } 카드</span>
			</span>
			</c:if>
	        <!-- 핸드폰 확인  -->
	        <c:if test="${!empty vo.phonePay && !empty vo.payTel}">
			<span>${vo.phonePay }
			<span class="payMeans">${vo.payTel }</span>
			</span>
			</c:if>
	        <!-- 계좌이체 확인  -->
	        <c:if test="${!empty vo.bankType && !empty vo.bankNum}">
			<span>${vo.bankType }
			<span class="payMeans">계좌번호: ${vo.bankNum }</span>
			</span>
			</c:if>
			
     		</div>
	      	<div class="col-md-3 flexClass">
	      	<span class="flexSpan">총 상품 가격
	      	<span class="float-right"><b class="totalPrice">${vo.cartPrice}</b>원</span>
	      	</span><br>
	      	<span class="flexSpan">할인 금액
	      	<span class="float-right"><b>0원</b></span>
	      	</span><br>
	      	<span class="flexSpan">배송비
	      	<span class="float-right"><b class="charge">0</b>원</span>
	      	</span>
	        	
        </div>
   		</div>
		<div class="horizontal-line"></div> <!-- 얇은 선  -->
		 <div class="alert alert-primary addSum">
  		 	<strong>총 결제금액:</strong>${vo.cartPrice }원 
  		</div>
		</div>	
		<!-- 클릭하면 메인으로 간다. -->
		<button type="button" class="btn btn-dark btn-block" id="home">확인</button>		
		
	</div>
	
<!-- 버튼 취소 모달 시작 -->
<!-- The Modal -->
<div class="modal" id="cancel">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">취소 사유</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <form action="payDelete.do" method="post">
      <input type="hidden" name="payNo" value="${vo.payNo }">
      <input type="hidden" name="status" value="취소">
      <div class="modal-body">
		<div class="form-group">
		  		<label for="cencel">취소 사유를 입력해주세요.</label>
		  		<select class="form-control" id="cencel" name="cencel">
    				<option>예상 배송일보다 상품의 배송이 늦어졌습니다.</option>
		   		 	<option>구매한 상품의 품질이나 상태가 설명과 다릅니다.</option>
	    			<option>제공받은 서비스가 기대 이하로,설명된 서비스와 실제 서비스가 상이합니다.</option>
	   			 	<option>결제 과정에서 오류가 발생하여 잘못된 금액이 청구되었거나 결제가 완료되지 않았습니다.</option>
	   			 	<option>동일한 상품이나 서비스를 중복으로 주문했습니다.</option>
	   			 	<option>계약 체결 후 계약 조건이 변경되어, 변경된 조건을 수용할 수 없게 되었습니다.</option>
	   			 	<option>주문한 상품이 품절되었거나 더 이상 재고가 없어 취소를 요청합니다.</option>
	   			 	<option>잘못된 상품이 주문되었거나, 상품에 오류가 있어 취소를 요청합니다.</option>
	   			 	<option>개인적인 사정이나 일정 변경으로 인해 주문을 유지할 수 없게 되었습니다.</option>
				  </select>
			</div>
		  <div class="form-group">

		</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button class="btn btn-primary">확인</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>
	</form>
    </div>
  </div>
</div>	
<!-- 버튼 취소 모달 끝 -->	
	<!-- container 끝 -->
</body>
</html>