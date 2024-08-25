<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 리스트</title>
<style>
.center-text {
     text-align: center;
     font-size: 24px; /* 글자 크기를 조절 */
     font-weight: bold; /* 글자 굵게 */
     margin-bottom: 20px; /* 텍스트와 선 사이의 간격 */
 }
.horizontal-line {
    width: 99%; /* 선의 길이 조절 */
    margin: 0 auto; /* 중앙 정렬 */
    border-top: 1px solid rgba(0, 0, 0, 0.1); /* 매우 희미한 선 */
}
.linePayWriteForm{
   border: 2px solid #000; /* 테두리 두께와 색상 설정 */
    padding: 20px; /* 내부 여백 설정 */
    border-radius: 30px; /* 모서리를 둥글게 설정 */  
}
.fontPre {
    font-size: 12px; /* 글자 크기 조절 */
    font-weight: 100; /* 글자 굵기 조절 */
    color: rgba(0, 0, 0, 0.2); /* 텍스트 색상 조절, 희미하게 설정 */
    text-shadow: 0 0 0.5px rgba(0, 0, 0, 0.2); /* 텍스트 그림자 추가, 희미한 효과 */
    margin-top: 20px; /* 텍스트사이의 간격 */
}
/*간격을 주기 위해 만듬*/
.marginLine{
margin-top: 30px;
}
.updateBtn { /*버튼을 설정하는 스타일*/
      background-color: white;
      color: black;
      border: 2px solid black;
      padding: 5px 10px;
      font-size: 12px;
      cursor: pointer;
      border-radius: 5px; /* 모서리를 둥글게 설정 */
}

.updateBtn:hover {
    background-color: #f0f0f0;
}
.addressCode{
 margin-top: 10px;
 margin-bottom: 20px;
}
 .cartimage{ 
/*   이미지 크기 조정  */
    width: 80px;
    height: 80px;
    object-fit: cover; /* 이미지 비율 유지하면서 크기 맞추기 */
 
  }
.goods{
margin-top: 15px;
}
.ViewPayBtn {
    background-color: #737373;
    color: white;
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

.ViewPayBtn:hover {
    background-color: #e6e6e6;
}


</style>
<script type="text/javascript">
$(function(){
	$(".ViewPayBtn").click(function(){
		location = "/";
	});
});
</script>

<script type="text/javascript"> 
//  새 배송지 변경 코드
	$(function(){
	    $(".changeAddress").click(function(){
	        let address =$(".address").val();
	        let detailAddress =$(".detailAddress").val();
	        let extraAddress =$(".extraAddress").val();
	        let real = address + detailAddress + extraAddress;
	        
	        $("#address").val(real);
	        $('#addressModal').modal('hide');
// 	        alert(address);
// 	        alert(detailAddress);
// 	        alert(extraAddress);
// 	        alert(real);
 	        });
		});
</script>
<script> 
//카카오 API 사용부분
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    $('#sample6_extraAddress').val(extraAddr);
                } else {
                    $('#sample6_extraAddress').val('');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $('#sample6_postcode').val(data.zonecode);
                $('#sample6_address').val(addr);

                // 커서를 상세주소 필드로 이동한다.
                $('#sample6_detailAddress').focus();
            }
        }).open();
    }
    //카카오 API 끝
</script>

</head>
<body>
	<div class="container">

	<!-- list 1은 pay list2 장바구니 -->
<%-- 	<p>${list }</p><p>${cart }</p> --%>
		<!-- 주문 확인 텍스트  -->
		<div class="center-text">주문 확인되었습니다.</div> 
		<!-- 리스트 시작 부분 -->
		<div class="linePayWriteForm">  <!-- 테두리 만듬  -->
		<div class="center-text">${list.get(0).cartPrice }원</div> <!-- 가격  -->
		<div class="horizontal-line"></div> <!-- 얇은 선  -->
		<pre class="fontPre"> <!-- 안내 -->
·판매자 상황에 따라 일반 발송 될 수 있습니다.
·입금자가 달라도 금액이 일치하면 정상 결제됩니다.
·무통장입금 불가 ATM:농협,우리,제주도,우체국(은행 창구 또는 인터넷 뱅킹을 이용해주세요)
·오늘출발 마감시간을 넘기면 다음 영업일에 발송됩니다.</pre>
		</div>
	<div class="marginLine"></div> <!-- 간격을 살짝 많이 주는 코드 -->
		<div class="linePayWriteForm">	
	<!-- 서로 간격을 주기 위해 사용 -->
		<form action="payUpdate.do">
		<b>${list.get(0).name }</b>
		<span class="float-right">
		<button type="button"class="updateBtn"  data-toggle="modal" data-target="#updateModal">변경</button>
		</span>
		</form>
		<p class="addressCode">${list.get(0).address }</p>
		<div class="horizontal-line"></div> <!-- 얇은 선  -->	
		<div class="goods"> <!-- 간격을 주는데 살짝 주기 위함 -->
		<img alt="없습니다." src="${cart.get(0).cartImage }" class="cartimage">	
		<span><b>총 상품 ${fn:length(cart)}건</b></span>
		</div>	<!-- goods 끝  -->	
		<div class="goods"> <!-- 간격을 주는데 살짝 주기 위함 -->
		<a class="btn btn-dark ViewPayBtn">메인 가기</a>
		</div><!-- goods 끝  -->	
		</div><!-- linePayWriteForm 끝  -->		
	</div> <!-- container 끝  -->	
	
<!-- 배송지 변경 모달 시작 -->	
<!-- The Modal -->
<div class="modal" id="updateModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">배송지 수정</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <form action="payUpdate.do">
      <input type="hidden" name="payNo" value="${list.get(0).payNo }" class="payNoModal">
     
      <div class="modal-body">
			<div class="form-group">
			<input type="text" id="sample6_postcode" placeholder="우편번호">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			</div>
			<div class="form-group">
			<input type="text" id="sample6_address" class="form-control address" placeholder="주소"><br>
			</div>
			<div class="form-group" id="detailAddress">		
			<input type="text" id="sample6_detailAddress" class="form-control detailAddress" placeholder="상세주소">
			</div>
			<div class="form-group" id="extraAddress">
			<input type="text" id="sample6_extraAddress" class="form-control extraAddress" placeholder="참고항목">   
			</div>  
			<div class="form-group">
			  <input type="hidden" class="form-control" id="address" name="address" value="">
			</div>
			<div class="form-group">
			  <label for="name">이름</label>
			  <input type="text" class="form-control" id="name" name="name" value="${list.get(0).name }">
			</div>       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button class="btn btn-primary changeAddress">변경</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>
	</form>
    </div>
  </div>
</div>
<!-- 배송지 변경 모달 끝 -->		
</body>
</html>