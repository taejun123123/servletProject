
 	$(function() {
			// 클릭 시 페이지 이동
			$(document).on('click', '.dataRow', function() {
				let musicNo = $(this).data("musicNo");
				let albumNo = $(this).data("albumNo");
				let isDisabled = $(this).hasClass("disabled");

				if (isDisabled) {
					return; // 비활성화된 행의 클릭 이벤트 무시
				}

				if (musicNo) {
					location.href = "/music/view.do?musicNo=" + musicNo;
				} else if (albumNo) {
					location.href = "/album/view.do?no=" + albumNo;
				}
			});
			
		});