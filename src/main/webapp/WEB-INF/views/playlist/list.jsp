<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>플레이 리스트</title>

<style type="text/css">
.dataRow:hover {
    background: #D2D9E9;
    cursor: pointer;
}

.title {
    text-overflow: ellipsis;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
}

.card {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 15px;
}

.imageDiv {
    flex: 1;
    text-align: center;
}

.imageDiv > img {
    width: auto; /* 비율 유지 */
    height: 100%; /* 이미지 높이가 컨테이너를 채우도록 설정 */
    display: block;
}

.card-body {
    flex: 2;
    padding-left: 15px;
}

.card-body .title {
    font-size: 1.1em;
    font-weight: bold;
}

#audioPlayer {
    display: none; /* 오디오 플레이어 숨기기 */
}

.deletebtn {
    margin-left: 1000px;
}

#customPlayer {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

#customPlayer .controls {
    display: flex;
    justify-content: center;
    align-items: center;
}

#customPlayer .controls button {
    font-size: 48px;
    background: none;
    border: none;
    cursor: pointer;
}

#customPlayer .progress {
    width: 100%;
    height: 10px;
    background-color: #e0e0e0;
    border-radius: 5px;
    overflow: hidden;
    margin-top: 10px;
}

#customPlayer .progress .progress-bar {
    height: 100%;
    background-color: #007bff;
    width: 0%;
    transition: width 0.1s linear;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    let audioPlayer = $("#audioPlayer")[0];
    let currentTrackIndex = -1; // 현재 곡의 인덱스
    let tracks = []; // 플레이리스트의 곡 정보
    
 // 플레이리스트의 곡 정보를 tracks 배열에 저장
    $(".dataRow").each(function(index) {
        let musicFile = $(this).data("musicfile");        
        tracks.push({
            index: index,
            musicFile: musicFile,
            element: $(this)
        });
    });

    function playTrack(index) {
        if (index >= 0 && index < tracks.length) {
            currentTrackIndex = index;
            let track = tracks[index];
            $("#audioPlayer").attr("src", track.musicFile);
            audioPlayer.play();
            let title = track.element.find(".title").text();
            let singer = track.element.find(".singer").text();
            let albumImage = track.element.find("img").attr("src");
            $(".imageSec > img").attr("src", albumImage);
            $("#singer").text(singer);
            $("#title").text(title);
        }
    }

    // 이미지 크기 조정
    let imgWidth = $(".imageDiv").first().width();
    let height = imgWidth * 1 / 6;
    $(".imageDiv").height(height);
    $(".imageDiv > img").css("height", height);

    // 플레이 리스트 항목 클릭 시 음원 재생 및 이미지 변경
    $(".dataRow").click(function() {
        let musicFile = $(this).data("musicfile");
        console.log(musicFile);
        let title = $(this).find(".title").text();
        let singer = $(this).find(".singer").text();
        let albumImage = $(this).find("img").attr("src");

        $("#audioPlayer").attr("src", musicFile);
        audioPlayer.play();
        $(".imageSec > img").attr("src", albumImage);
        $("#singer").text(singer);
        $("#title").text(title);
    });

    // 음원 플레이 이미지
    let imgWidthSec = $(".imageSec").first().width();
    let heightSec = imgWidthSec * 1 / 1;
    $(".imageSec").height(heightSec);
    $(".imageSec > img").css("height", heightSec);

    // 모달 열기 버튼 클릭 시 체크된 항목을 모달에 표시
    $(".deletebtn").click(function() {
        let selectedSongs = $("input[name='playlistNo']:checked").map(function() {
            return $(this).closest(".card-body").find(".title").text();
        }).get();

        if (selectedSongs.length == 0) {
            $(".modal-body").text("지울 곡을 선택해 주세요.");
            $(".modal-footer .btn-danger").hide();
        } else {
            $(".modal-body").html("다음 곡을 지우시겠습니까?<br><ul>" + selectedSongs.map(function(song) {
                return "<li>" + song + "</li>";
            }).join("") + "</ul>");
            let selectedMusicNos = $("input[name='playlistNo']:checked").map(function() {
                return $(this).val();
            }).get().join(",");
            $("input[name='playlistNo_hidden']").val(selectedMusicNos);
            $(".modal-footer .btn-danger").show();
        }
    });

    // 재생/일시정지 버튼 클릭 시 처리
    $("#playBtn").click(function() {
        if (audioPlayer.paused) {
            audioPlayer.play();
            $(this).removeClass('fa-play-circle-o').addClass('fa-pause-circle-o');
        } else {
            audioPlayer.pause();
            $(this).removeClass('fa-pause-circle-o').addClass('fa-play-circle-o');
        }
    });

    // 이전 곡 버튼 클릭 시 처리
    $("#prevBtn").click(function() {
        if (currentTrackIndex > 0) {
            playTrack(currentTrackIndex - 1);
        }
    });

    // 다음 곡 버튼 클릭 시 처리
    $("#nextBtn").click(function() {
        if (currentTrackIndex < tracks.length - 1) {
            playTrack(currentTrackIndex + 1);
        }
    });
    
 // 진행 바 클릭 이벤트
    $(".progress").click(function(e) {
        let progressWidth = $(this).width();
        let offsetX = e.offsetX;
        let duration = audioPlayer.duration;
        let clickTime = (offsetX / progressWidth) * duration;
        audioPlayer.currentTime = clickTime;
    });

    // 진행 바 업데이트
    audioPlayer.ontimeupdate = function() {
        let percentage = (audioPlayer.currentTime / audioPlayer.duration) * 100;
        $(".progress-bar").css("width", percentage + "%");
    };

    // 음악 재생 완료 시 다음 곡 자동 재생
    audioPlayer.onended = function() {
        let nextRow = $(".dataRow[data-musicno='" + $(".dataRow[data-musicfile='" + audioPlayer.src + "']").next().data("musicno") + "']");
        if (nextRow.length > 0) {
            nextRow.click();
        } else {
            audioPlayer.pause();
            $("#playBtn").removeClass('fa-pause-circle-o').addClass('fa-play-circle-o');
        }
    };
});
</script>
</head>
<body>
    <div class="container"> 
        <h2>플레이 리스트</h2>
        <br>
           <!-- 음원 지우기 버튼 -->
           <button type="button" class="btn btn-danger deletebtn" data-toggle="modal" data-target="#myModal">지우기</button>
        <div class="row">
            <!-- 음원 재생기 -->
            <div class="col-lg-6">
                <div class="audio-player">
                 <!-- 맨위 상단바 노래명, 가수명 -->
                    <div data-musicno="${vo.musicNo}">
                        <div style="margin-left:35%">
                        <h3 id="title">${vo.musictitle}</h3>
                        </div>
                        <div style="margin-left:43%">
                        <p id="singer">${vo.singer}</p>
                        </div>
                        <div class="imageSec">
                         <!-- 음원 플레이 전용 이미지 -->
                            <img class="img-thumbnail" src="/upload/playlist/playlist.jpg" alt="image">
                        </div>
                        <br>
                        <div class="progress">
                            <div class="progress-bar"></div>
                        </div>
                        <br>
                        <div id="customPlayer">
                        <!-- 일시정지&재생, 다음곡, 이전곡 버튼 -->
                            <div class="controls">
                                <button id="prevBtn" class="fa fa-backward"></button>
                                <button id="playBtn" class="fa fa-play-circle-o"></button>
                                <button id="nextBtn" class="fa fa-forward"></button>
                            </div>
                        </div>
                        <audio id="audioPlayer" controls>
                        <!-- 뮤직파일 -->
                            <source src="${vo.musicFile}" type="audio/mpeg">
                        </audio>
                        <p>${vo.ptime}</p>
                        <div id="div"></div>
                    </div>
                </div>
            </div>

            <!-- 플레이 리스트 -->
            <div class="col-lg-6">
                <form id="deleteForm" action="delete.do" method="get">
                    <c:if test="${!empty login}">
                        <c:forEach items="${list}" var="vo">
                            <div class="dataRow" data-musicno="${vo.musicNo}" data-musicfile="${vo.musicFile}">
                                <div class="card" style="width: 100%">
                                    <div class="imageDiv p-2">
                                        <img class="card-img-top" src="${vo.image}" alt="image">
                                    </div>
                                    <div class="card-body">
                                        <input type="checkbox" name="playlistNo" value="${vo.playlistNo}" class="form-check-input">
                                        <p class="title">${vo.musictitle}</p>
                                        <p class="singer">${vo.singer}</p>
                                        <p>${vo.ptime}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- 모달 창 -->
        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 모달 헤더 -->
                    <div class="modal-header">
                        <h4 class="modal-title">플레이 리스트 음원 지우기</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- 모달 본문 -->
                    <div class="modal-body">지울 곡을 선택해 주세요.</div>

                    <!-- 모달 푸터 -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="$('#deleteForm').submit();" style="display: none;">지우기</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
