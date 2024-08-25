<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 폼</title>

<style type="text/css">
.linePayWriteForm{
   border: 2px solid #000; /* 테두리 두께와 색상 설정 */
    padding: 20px; /* 내부 여백 설정 */
    border-radius: 30px; /* 모서리를 둥글게 설정 */  
}
 /* 앵커 태그를 히든으로 만듭니다. */
 #performAction {
     display: none; /* 또는 visibility: hidden; */
 }
/*Modal title 중앙 스타일*/
  .modal-header {
    display: flex;
    justify-content: center;
    position: relative;
  }
  .modal-title {
    margin: auto;
  }
  .modal-header .close {
    position: absolute;
    right: 15px;
  }
  .cartimage{ 
/*   이미지 크기 조정  */
    width: 80px;
    height: 80px;
    object-fit: cover; /* 이미지 비율 유지하면서 크기 맞추기 */
  
  }
  /* 글씨 옆으로 두기 */
  .albumPont {
    margin-left: 80px; /* 원하는 만큼 조정 */
  }
  .text-container {
    display: inline-block;
    vertical-align: top;
    margin-left: 15px; /* 텍스트를 이미지에서 떨어뜨림 */
  }
  .text-container i {
    display: block;
    font-size: 14px; /* 원하는 글씨 크기로 조정 */
    margin-bottom: 30px; /* 텍스트 사이의 간격 조정 */
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
  .Btns{
  border: 2px solid; /* 테두리 크기 및 색상 */
   background-color: #D2D9E9;
  }
  .marginLine{
  margin-top: 30px;
  }
  .payBtn{
    height: 100%;
    margin: 0;
    display: flex;
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
  }
  .centerPont{
    display: flex;
    justify-content: center; /* 버튼 내부 수평 중앙 정렬 */
    align-items: center; /* 버튼 내부 수직 중앙 정렬 */
    font-size: 16px; /* 버튼 텍스트 크기 */
    padding: 10px 20px; /* 버튼 내부 여백 */
    text-align: center; /* 텍스트 중앙 정렬 */
  }
.flex-container {
    display: flex; /* Flexbox 컨테이너로 설정 */
    justify-content: space-between; /* 아이템들을 양 옆으로 정렬 */
    padding: 0 20px; /* 컨테이너의 좌우 여백 */
}

.flex-item {
   border: 2px solid; /* 테두리 크기 및 색상 */
   background-color: #D2D9E9;
    padding: 10px 20px; /* 버튼 내부 여백 */
}
.horizontal-radio {
    display: flex;
    align-items: center;
}

.horizontal-radio .form-check {
    margin-right: 15px; /* 필요에 따라 간격 조정 */
}
#PaySubmitBtn{
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
#PaySubmitBtn:hover{
 background-color: #e6e6e6;
}

</style>
<!-- 총합 계산 시작 -->
<script type="text/javascript">
    function calculateTotalPrice() {
        let totalPrice = 0;
        
        $(".album-item").each(function() {
            let albumItem = $(this);
            let albumCnt = parseInt(albumItem.find(".albumCnt").text(), 10);
            let price = parseInt(albumItem.find(".price").text(), 10);
            totalPrice += price * albumCnt;
        });
        
        $("#totalSum").text(totalPrice);
        // 총 가격 및 배송비 계산
        let totalShippingCost = (totalPrice > 50000) ? 0 : 3000;
        let totalSum = totalPrice + totalShippingCost;
        $("#shippingCost").text(totalShippingCost);
        $("#totalSumWithShipping").text(totalPrice + totalShippingCost);
      
        $(".totalPrices").val(totalSum);
    }

    $(document).ready(function() {
        calculateTotalPrice();
    });
</script>
<!-- 총합 계산 끝 -->

<!-- 신용 카드 xxxx-xxxx-xxxx-xxxx 를 사용하기 위한 js -->
<script>
$(document).ready(function() {
  $('#cardNum').on('input', function() {
    // 현재 입력된 값을 가져오기
    let value = $(this).val();

    // 숫자 외의 문자 제거
    value = value.replace(/\D/g, '');

    // 16자리까지만 허용
    if (value.length > 16) {
      value = value.substring(0, 16);
    }

    // 4자리마다 하이픈 추가
    let formattedValue = '';
    for (let i = 0; i < value.length; i++) {
      if (i > 0 && i % 4 === 0) {
        formattedValue += '-';
      }
      formattedValue += value[i];
    }

    // 입력 필드의 값 업데이트
    $(this).val(formattedValue);
  });
});
</script>
<!-- 신용카드 js 끝 -->
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
<script type="text/javascript"> //결제 수단 처리 
$(function(){
	$(".Payall").hide();
	savaPay = 0;
	$(".PayCards").click(function(){
		$(".Payall").show();
		$(".phones").hide();
		$(".banks").hide();
		$(".cards").show();
		savaPay = 1;
	});
	$(".PayPhones").click(function(){
		$(".Payall").show();
		$(".phones").show();
		$(".banks").hide();
		$(".cards").hide();
		savaPay = 2;
	});
	$(".PayBanks").click(function(){
		$(".Payall").show();
		$(".phones").hide();
		$(".banks").show();
		$(".cards").hide();
		savaPay = 3;
	});
});
</script>


<!-- 휴대폰 결제 js -->
<script type="text/javascript">
$(document).ready(function() {
    $('#payTel').on('input', function() {
        // 현재 입력된 값을 가져오기
        let value = $(this).val();
        
        // 숫자 외의 문자 제거
        value = value.replace(/\D/g, '');

        // 11자리까지만 허용 (010 포함)
        if (value.length > 11) {
            value = value.substring(0, 11);
        }

        // 형식 적용: 010-xxxx-xxxx
        let formattedValue = '';
        if (value.length > 0) {
            formattedValue += '010';
        }
        if (value.length > 3) {
            formattedValue += '-' + value.substring(3, 7);
        }
        if (value.length > 7) {
            formattedValue += '-' + value.substring(7, 11);
        }

        // 입력 필드의 값 업데이트
        $(this).val(formattedValue);
    });
});
</script>
<!-- 계좌번호 결제 js -->
    <script>
        $(document).ready(function() {
            $('#bankNum').on('input', function() {
                // 현재 입력된 값을 가져오기
                let value = $(this).val();

                // 숫자 외의 문자 제거
                value = value.replace(/\D/g, '');

                // 계좌번호 형식 적용: xxx-xxxxxx-xx-xxx
                let formattedValue = '';
                if (value.length > 0) {
                    formattedValue += value.substring(0, 3);
                }
                if (value.length > 3) {
                    formattedValue += '-' + value.substring(3, 9);
                }
                if (value.length > 9) {
                    formattedValue += '-' + value.substring(9, 11);
                }
                if (value.length > 11) {
                    formattedValue += '-' + value.substring(11, 14);
                }

                // 입력 필드의 값 업데이트
                $(this).val(formattedValue);
            });
        });
    </script>
<!-- 계좌번호 결제 js 끝 -->
<!-- 사용자 전화 번호 입력 -->
<script type="text/javascript">
$(document).ready(function() {
    $('#phone').on('input', function() {
        // 현재 입력된 값을 가져오기
        let value = $(this).val();
        
        // 숫자 외의 문자 제거
        value = value.replace(/\D/g, '');

        // 11자리까지만 허용 (010 포함)
        if (value.length > 11) {
            value = value.substring(0, 11);
        }

        // 형식 적용: 010-xxxx-xxxx
        let formattedValue = '';
        if (value.length > 0) {
            formattedValue += '010';
        }
        if (value.length > 3) {
            formattedValue += '-' + value.substring(3, 7);
        }
        if (value.length > 7) {
            formattedValue += '-' + value.substring(7, 11);
        }

        // 입력 필드의 값 업데이트
        $(this).val(formattedValue);
    });
});
</script>
<script type="text/javascript">
$(function(){
	$("#PaySubmitBtn").click(function(){
	$(".Payall").find("input,select").attr("disabled",true);
	if(savaPay == 1){
		$(".cards").find("input,select").removeAttr("disabled");
	}
	if(savaPay == 2){
		$(".phones").find("input,select").removeAttr("disabled");
	}
	if(savaPay == 3){
		$(".banks").find("input,select").removeAttr("disabled");
	}
	
	});
});

</script>
</head>
<body>
	<div class="container">
	<form action="payWrite.do" method="post">
	<input type="hidden" name="totalPrices" value="" class="totalPrices">
	
		<h3>배송지</h3>
		<div class="linePayWriteForm"> 
		<!-- 테두리의 시작 -->
			
		<div class="form-group">
		  <label for="address">주소</label>
		  <input type="text" class="form-control" id="address" name="address" readonly>
		</div>
		<div class="form-group">
		  <label for="name">이름</label>
		  <input type="text" class="form-control" id="name" name="name">
		</div>
		<div class="form-group">
		  <label for="phone">전화번호</label>
		  <input type="text" class="form-control" id="phone" name="phone">
		</div>
		<a href="addressForm.do" class="btn btn-dark Btns" data-toggle="modal" data-target="#addressModal">새 주소 등록</a>
		<!-- 테두리의 끝 -->
		</div>
		<!-- 서로 간격을 주기 위해 사용 -->
		<div class="marginLine"></div>
		<!-- 간격주기 끝 -->
		<h3>주문 상품</h3>
		<!-- 장바구니 물건 리스트 시작 -->
		<div class="linePayWriteForm"> 
		    <c:forEach items="${list}" var="vo">
		    <input type="hidden" value="${vo.cartNo }" name="cartNo">
		        <div class="album-item">
		            <img alt="없습니다." src="${vo.cartImage}" class="cartimage">
		            <div class="text-container">
		                <i class="cartTitle">${vo.cartTitle}</i> 수량: <span class="albumCnt">${vo.albumCnt}</span>개
		                <div>
		                    <b class="albumPont cartSum price">${vo.price}</b>원
		                </div>
		            </div>
		            <br>
		            <div class="marginLine"></div>
		        </div>
		    </c:forEach>
		   <div class="alert alert-primary">
		       <strong>총 가격:</strong> <span id="totalSum"></span>원
		   </div>
		   <div class="alert alert-secondary">
		       <strong>배송비:</strong> <span id="shippingCost"></span>원
		   </div>
		   <div class="alert alert-success">
		       <strong>총 결제 금액:</strong> <span id="totalSumWithShipping"></span>원
		   </div>
   		</div>
		<!-- 장바구니 물건 리스트 끝 -->
	<!-- 서로 간격을 주기 위해 사용 -->
	<div class="marginLine"></div>
	<!-- 간격주기 끝 -->
	<h3>결제 수단</h3>	
	<div class="linePayWriteForm"> 
	<div class="PayMines flex-container">
	<button type="button" class="btn btn-dark flex-item PayCards" >신용카드</button>
	<button type="button" class="btn btn-dark flex-item PayPhones">휴대폰</button>
	<button type="button" class="btn btn-dark flex-item PayBanks">계좌번호</button>
	</div>
	<!-- PayMines Btn 끝 -->
	<!-- 서로 간격을 주기 위해 사용 -->
	<div class="marginLine"></div>	
		<div class="Payall"> <!-- 전체 안보여주기 위해서 사용하는 클래스 -->
		<!-- 신용카드 시작 -->
		<div class="cards"> <!-- 나중에 hide으로 숨기기 위해서 div로 감싸줌  -->
			<div class="form-group horizontal-radio">
			<div class="form-check">
				<label class="form-check-label"> <input type="radio"
					class="form-check-input" name="cardType" value="개인">개인
				</label>
			</div>
			<div class="form-check">
				<label class="form-check-label"> <input type="radio"
					class="form-check-input" name="cardType" value="법인">법인
				</label>
			</div>
			</div>
			<div class="form-group">
			  <label for="cardBank">카드를 선택해 주세요</label>
			  <select class="form-control" id="cardBank" name="cardBank">
			    <option>농협</option>
			    <option>신한</option>
			    <option>우리</option>
			    <option>국민</option>
			    <option>기업</option>
			    <option>롯데</option>
			    <option>하나</option>
			  </select>
			</div>
			<div class="form-group">
			  <label for="cardNum">신용카드 번호 입력</label>
			  <input type="text" class="form-control" id="cardNum" name="cardNum" placeholder="신용카드 번호를 입력해주세요." pattern="\d{4}-\d{4}-\d{4}-\d{4}" title="xxxx-xxxx-xxxx-xxxx 형식으로 입력해주세요." maxlength="19" required>
			</div>	<!-- 신용카드 번호 입력 끝 -->
			</div> <!-- 신용카드 끝 -->
	<!-- 휴대폰 결제 시작 -->
		<div class="phones">
			<div class="form-group horizontal-radio">
			<div class="form-check">
				<label class="form-check-label"> <input type="radio"
					class="form-check-input" name="phonePay" value="간편결제">간편결제
				</label>
			</div>
			<div class="form-check">
				<label class="form-check-label"> <input type="radio"
					class="form-check-input" name="phonePay" value="일반결제">일반결제
				</label>
			</div>
			</div>
		    <div class="form-group">
		        <label for="payTel">전화번호 입력</label>
		        <input type="text" class="form-control" id="payTel" name="payTel" placeholder="전화번호를 입력해주세요." pattern="\d{3}-\d{4}-\d{4}" title="xxx-xxxx-xxxx 형식으로 입력해주세요." maxlength="13" required>
		    </div>
		</div>
	<!-- 휴대폰 결제 끝 -->
	<!-- 계좌번호 결제 시작 -->
	<div class="banks">
		<div class="form-group">
	  <label for="bankType">은행을 선택해 주세요</label>
	  <select class="form-control" id="bankType" name="bankType">
	    <option>국민은행</option>
	    <option>신한은행</option>
	    <option>우리은행</option>
	    <option>농협은행</option>
	    <option>기업은행</option>
	    <option>제주은행</option>
	    <option>하나은행</option>
	  </select>
	</div>
    <div class="form-group">
        <label for="bankNum">계좌번호 입력</label>
        <input type="text" class="form-control" id="bankNum" name="bankNum" placeholder="계좌번호를 입력해주세요."   pattern="\d{3}-\d{6}-\d{2}-\d{3}" title="xxx-xxxxxx-xx-xxx 형식으로 입력해주세요." maxlength="17" required>
    </div>
	</div>	
	<!-- 계좌번호 결제 끝 -->
	</div>
	<!-- linePayWriteForm 끝 -->
	</div> 
	<!-- 서로 간격을 주기 위해 사용 -->
	<div class="marginLine"></div>
	<b class="centerPont">주문 내용을 확인하였으며, 정보 제공 등에 동의합니다.</b>
	<!-- 서로 간격을 주기 위해 사용 -->
	<div class="marginLine"></div>
	<!-- 클래스 이름 재활용 -->
	<button type="submit"class="btn btn-dark" id="PaySubmitBtn">결제하기</button>
	</form>
	</div>
	<!-- container 끝 -->
	
<!-- Modal 시작 -->
<div class="modal" id="addressModal">
  <div class="modal-dialog ">
    <div class="modal-content">
	<form id="addressPayForm" method="post"> <!-- 주소 변경 부분 -->
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">새 배송지 입력</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <!-- 새 주소 데이터 넘기는 부분 -->
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
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button class="btn btn-primary changeAddress" type="button">변경</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
	</form>
    </div>
  </div>
</div>	
<!-- Modal 끝 -->
</body>
</html>