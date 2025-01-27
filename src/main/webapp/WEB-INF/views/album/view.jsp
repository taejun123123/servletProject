<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 상세 보기</title>
<style type="text/css">

.musicImg{
	width: 40px;
	height: 40px;
	margin-right: 10px;
}
.quantity-input {
    display: flex;
    justify-content: center; /* 가운데 정렬 */
    align-items: center;
    margin-top: 10px;
}

.quantity-input input {
    width: 60px;
    text-align: center;
}
.quantity-input button {
    width: 30px;
    height: 30px;
}
.modal-header {
    background-color: #f2f2f2;
    border-bottom: 1px solid #ccc;
}
.modal-header .close {
    color: #333;
}

.modal-body {
    background-color: #fff;
    padding: 20px;
}
.quantity-input button {
    background-color: #fff;
    border: 1px solid #ccc;
    font-size: 18px;
    line-height: 1;
    margin: 0 5px;
    padding: 0;
    width: 40px;
    height: 40px;
}
.quantity-input button#decreaseQuantity {
    color: red;
}
.quantity-input button#increaseQuantity {
    color: blue;
}
.quantity-input input {
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 18px;
    text-align: center;
    height: 40px;
    margin: 0 5px;
}
.modal-footer {
    background-color: #f2f2f2;
    border-top: 1px solid #ccc;
    padding: 15px;
}
.btn-success {
    background-color: #5cb85c;
    border-color: #4cae4c;
}
.btn-secondary {
    background-color: #6c757d;
    border-color: #6c757d;
}
.info-content {
    max-height: 4em;
    overflow: hidden;
    transition: max-height 0.3s ease;
}
.info-content.expanded {
    max-height: none;
}
#toggleInfo {
    display: block;
    margin: 10px auto;
    text-align: center;
}
.replyRating {
    margin-top: 5px;
    
}
.btn-add {
    display: inline-block; /* 인라인 블록으로 설정하여 클릭 영역 확대 */
    padding: 10px 15px; /* 버튼의 내외부 여백을 조정하여 클릭 가능한 영역 확대 */
}
</style>

<script>
$(function(){
	
	// 로그인 상태를 나타내는 전역 변수
	var isLoggedIn = ${login != null ? "true" : "false"};
	
  $('[data-toggle="tooltip"]').tooltip();  
  
  //이벤트 처리
  $("#deleteBtn").click(function(){
	 // js 경고창 - alert : 일반 경고, conform : 확인/취소, prompt : 키인
	 // 확인 창이 나타나는데 취소를 누르면 삭제페이지 이동을 취소(return false) 시킨다.
	 if(!confirm("정말 삭제 하시겠습니까?")) return false;
  });
  // 장바구니 담기버튼 권한처리
  $("#cartBtn").click(function(){
	 if (!isLoggedIn) {
		// 사용자가 로그인하지 않은 상태면, 로그인 필요 모달 표시
		$("#modalMessage").text("로그인 후 이용하실 수 있습니다.");
		$("#resultModal").modal('show');
		return;
		}
		$("#cartModal").modal('show');
  });
  
	//앨범 듣기 클릭시 플레이 리스트에 다중 등록
	$("#albumTotalBtn").click(function() {
				

				if (!isLoggedIn) {
					// 사용자가 로그인하지 않은 상태면, 로그인 필요 모달 표시
					$("#modalMessage").text("로그인 후 이용하실 수 있습니다.");
					$("#resultModal").modal('show');
					return;
				}
				
				let albumNo = $(this).data("album-no");
				let id = $(this).data("id");
				if (typeof albumNo === 'undefined') {
					console.error("albumNo is undefined");
					return;
				}

				$.ajax({url : "/ajax/playlistMultiWrite.do",
						type : "POST",
						data : {albumNo : albumNo,
								id : id
							},success : function(response) {
								let modalMessage = response !== "0" ? "플레이 리스트 목록에 추가되었습니다."
										: "플레이 리스트 목록에 담기지 않았습니다.";
								$("#modalMessage").text(
										modalMessage);
								$("#resultModal").modal('show');
							},
							error : function() {
								$("#modalMessage").text(
										"서버와의 통신에 실패했습니다.");
								$("#resultModal").modal('show');
							}
				});
			});
	
  
	$(".btn-add").click(function(event) {
      // 이벤트 전파 막기
      event.stopPropagation();

      if (!isLoggedIn) {
          // 사용자가 로그인하지 않은 상태면, 로그인 필요 모달 표시
          $("#modalMessage").text("로그인 후 이용하실 수 있습니다.");
          $("#resultModal").modal('show');
          return;
      }

      let musicNo = $(this).data("music-no");
      let albumNo = $(this).data("album-no"); // albumNo가 필요한 경우
      
      console.log("Music No: " + musicNo); // 콘솔에서 확인
      console.log("Album No: " + albumNo); // 콘솔에서 확인
      if (typeof musicNo === 'undefined') {
          console.error("musicNo is undefined");
          return;
      }

      $.ajax({
          url: "/music/increaseHit.do",
          type: "POST",
          data: {
              musicNo: musicNo
          },
          success: function(response) {
              let modalMessage = response === "success" ? "플레이 리스트 목록에 추가되었습니다."
                  : "플레이 리스트 목록에 담기지 않았습니다.";
              $("#modalMessage").text(modalMessage);
              $("#resultModal").modal('show');
          },
          error: function() {
              $("#modalMessage").text("서버와의 통신에 실패했습니다.");
              $("#resultModal").modal('show');
          }
      });

      $.ajax({
          url: "/ajax/playlistWrite.do",
          type: "POST",
          data: {
              musicNo: musicNo,
              albumNo: albumNo
          },
          success: function(response) {
              let modalMessage = response !== "0" ? "플레이 리스트 목록에 추가되었습니다."
                  : "플레이 리스트 목록에 담기지 않았습니다.";
              $("#modalMessage").text(modalMessage);
              $("#resultModal").modal('show');
          },
          error: function() {
              $("#modalMessage").text("서버와의 통신에 실패했습니다.");
              $("#resultModal").modal('show');
          }
      });
  });
  
// 데이터 행 클릭 시 view.do로 이동
$(".dataRow").click(function() {
if ($(this).hasClass("disabled")) {
	return; // 비활성화된 행의 클릭 이벤트 무시
}
let musicNo = $(this).data("music-no");
if (typeof musicNo === 'undefined') {
	console.error("musicNo is undefined");
	return;
}
console.log("Redirecting to view.do?musicNo=" + musicNo); // 로그 확인
location.href = "/music/view.do?musicNo=" + musicNo; // location.href를 사용하여 페이지 이동

});

$("#quantity").on("input", function() {
    updateTotalPrice();
});

$("#increaseQuantity").click(function() {
    let quantity = parseInt($("#quantity").val());
    $("#quantity").val(quantity + 1);
    updateTotalPrice();
});

$("#decreaseQuantity").click(function() {
    let quantity = parseInt($("#quantity").val());
    if (quantity > 1) {
        $("#quantity").val(quantity - 1);
        updateTotalPrice();
    }
});

function updateTotalPrice() {
    let quantity = parseInt($("#quantity").val());
    let price = parseInt("${vo.price}");
    let totalPrice = quantity * price;
    $("#totalPrice").text(totalPrice + "원");
}

$("#toggleInfo").click(function() {
    var content = $("#infoContent");
    if (content.hasClass("expanded")) {
        content.removeClass("expanded");
        $(this).text("펼치기");
    } else {
        content.addClass("expanded");
        $(this).text("접기");
    }
});
});
</script>

</head>
<body>
<div class="container">
	<h1>앨범 정보</h1>
	
  <div class="media border p-3">
    <img src="${vo.image }" alt="앨범 커버" class="mr-3" style="width:300px;" id = "">
    <div class="media-body">
     <h2>${vo.title }</h2>
     <h4>${vo.artist }</h4>
     <p style="margin-bottom: 0px;">발매일 : ${vo.release_date }</p>
     <p style="margin-bottom: 0px;">장르 : ${vo.genre }</p>
     <p style="margin-bottom: 0px;">가격 : ${vo.price }원</p>
     <p style="margin-bottom: 0px;">댓글<span style="margin-bottom: 0px;" class="replyCnt">${vo.replyCnt}</span>개</p>
     <div style="margin-top: 5px;"><span class="replyRating" >${vo.rating}</span><span class= "mb-2" style="font-size: 30px;" >${vo.decimalRating }/10</span></div>
    <br>
    <button class="btn btn-info" id="albumTotalBtn" data-album-no="${vo.albumNo}" data-id="${login.id}"><i class='fa fa-play'></i> 앨범 듣기</button>
    <button type="button" class="btn btn-secondary"  id = "cartBtn"><i class='fa fa-shopping-cart'></i> 장바구니 담기</button>
    <c:if test="${!empty login && login.gradeNo == 9}">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#changeImageModal">
			이미지 변경
		</button>
	</c:if>
    </div>
  </div>
	
<!-- 	앨범 정보  -->
	<h5>앨범 설명</h5>
	<div>
    	<div id="infoContent" class="info-content">
       		 <div style="color: #767676; font-size: 14px;">${vo.info}</div>
  		 </div>
   		 <button id="toggleInfo" class="btn btn-link btn-sm">펼치기<i class="fa fa-angle-down"></i></button>
	</div>
		 	<!-- 수록곡 등록 버튼 -->
			<c:if test="${!empty login && login.gradeNo == 9}">
			<a class="btn btn-info" href="includeForm.do?no=${param.no }">수록곡 등록</a>
			</c:if>
			<!-- 수록곡 리스트 -->
			<table class="table">
				<!-- 수록곡 데이터의 제목 -->
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>가수명</th>
					<c:if test="${!empty login && login.gradeNo == 9}">
						<th>상태</th>
					</c:if>
					<th>담기</th>
				</tr>
				<!-- 실제 데이터 표시 -->
				<c:forEach items="${musicList}" var="vo">
					<c:choose>
						<c:when
							test="${vo.musicStatus == '공개' || (!empty login && login.gradeNo == 9)}">
							<tr class="dataRow ${vo.musicStatus == '비공개' ? 'disabled' : ''}"
								data-music-no="${vo.musicNo}">
								<td class="no">&nbsp;&nbsp;${vo.includedNo}</td>
								<td><img src="${vo.image}" alt="${vo.musicTitle}" class="musicImg"/>
									${vo.musicTitle}</td>
								<td>${vo.singer}</td>
								<c:if test="${!empty login && login.gradeNo == 9}">
									<td>${vo.musicStatus}</td>
								</c:if>
								<td>
									<!-- 담기 버튼 -->
									<span class="btn-add" data-music-no="${vo.musicNo}" data-album-no="${vo.albumNo}">&nbsp;&nbsp;
									<i class="fa fa-plus"></i>
									</span>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 비공식 음원은 표시되지 않음 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>

				
			</table>		
	

	<!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. -->
	<c:if test="${!empty login && login.gradeNo == 9}">
	<a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="이미지를 제외한 항목만 수정가능합니다">수정</a>
	<a class="btn btn-danger" href = "delete.do?albumNo=${vo.albumNo }&deleteImageName=${vo.image}&perPageNum=${param.perPageNum}" id = "deleteBtn">삭제 </a>
	</c:if>
	<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}" class="btn btn-info">리스트</a>
	
	<!-- 댓글 처리 시작 -->
<jsp:include page="reply.jsp"/> 
<!-- 댓글 처리 끝 -->
	
</div>
<!-- container의 끝 -->

<!-- The Modal -->
<div class="modal" id="changeImageModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">바꿀 이미지 선택하기</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
	
		<form action="changeImage.do" method="post" enctype="multipart/form-data">
			<!-- 숨겨서 넘겨야할 데이터 - 이미지 번호, 현재 파일이름(삭제) -->
			<input name="albumNo" value="${vo.albumNo }" type="hidden">
			<input name="deleteImageName" value="${vo.image }" type="hidden">
			 <!-- 페이지 정보도 넘긴다. -->
			<input name="page" value="${param.page }" type="hidden">
			<input name="perPageNum" value="${param.perPageNum }" type="hidden">
			<input name="key" value="${param.key }" type="hidden">
			<input name="word" value="${param.word }" type="hidden">
	      <!-- Modal body -->
	      <div class="modal-body">
			 <div class="form-group">
			    <label for="image">첨부 이미지</label>
				<input id="image" name="image" required class="form-control" type="file">
			  </div>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button class="btn btn-primary">바꾸기</button>
	        <button type="button" class="btn btn-danger"
	         data-dismiss="modal">취소</button>
	      </div>
		
		</form>
    </div>
  </div>
</div>

  			<div class="modal fade" id="cartModal">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">수량을 입력해주세요.</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
						<form action="/cartalbum/write.do">
							<input name="albumNo" value = "${vo.albumNo }" type="hidden">
							<!-- 수량 입력 -->
							<div class="quantity-input">
								<button style="color: red;" type="button" id="decreaseQuantity">-</button>
								<input name= "albumCnt" style="width:70px;" type="number" id="quantity" value="1" min="1">
								<button style="color: blue;" type="button" id="increaseQuantity">+</button>
							</div>
							<p align="center">
								총 가격: <span id="totalPrice">${vo.price}원</span>
							</p>
							<button class="btn btn-success float-right" >장바구니에 담기</button>
						</form>	
						</div>
					</div>
				</div>

	      </div>

	<!-- 결과 모달 -->
	<!-- Result Modal -->
	<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="resultModalLabel">알림</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="modalMessage"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>