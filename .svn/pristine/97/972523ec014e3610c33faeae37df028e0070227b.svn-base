package com.musaic.music.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.PageObject;

// Board Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class MusicController {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MusicController.execute()--------------------");
		
		HttpSession session = request.getSession();
		// uri
		String uri = request.getRequestURI();

		Object result = null;
		// String 정보
		String jsp = null;

		// 로그인 정보 꺼내기
		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		String id = null;
		long gradeNo = 0L;
		// 로그인 한 경우에만 꺼내기
		if(loginVO != null) {
			id = loginVO.getId();  gradeNo = loginVO.getGradeNo();
		}
		// 파일 업로드 설정 --------------------------------------------
		// 파일의 상대적인 저장 위치
		String savePath = "/upload/music";
		// 파일 시스템에서는 절대 저장 위치가 필요하다. - 파일 업로드시 필요
		String realSavePath = request.getServletContext().getRealPath(savePath);
		// 업로드 파일 용량 제한
		int sizeLimit = 100 * 1024 * 1024;
		// 입력 받는 데이터 선언
		Long no = 0L;
		Long albumNo = 0L;

		try { // 정상 처리 

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/music/newList.do":
			    System.out.println("1. 최신음악 리스트");

			    // PageObject 인스턴스 가져오기
			    PageObject pageObject = PageObject.getInstance(request);

			    // perPageNum 파라미터 가져오기
			    String strPerPageNum = request.getParameter("perPageNum");

			    // 페이지 그룹 내 항목 수 설정 (이 설정은 페이지당 항목 수와 관련이 없음)
			    pageObject.setPerGroupPageNum(6L);
			    
			    // 관리자에 따른 페이지 형변환
			    pageObject.setAcceptMode((int)gradeNo);

			    System.out.println("perPageNum parameter: " + strPerPageNum);

			    // 음악 리스트 가져오기
			    List<MusicVO> newList = (List<MusicVO>) Execute.execute(Init.get(uri), pageObject);
			    System.out.println("MusicController.execute().pageObject = " + pageObject);

			    // 요청 속성에 새 리스트와 PageObject 설정
			    request.setAttribute("newList", newList);
			    request.setAttribute("pageObject", pageObject);

			    // JSP 경로 설정
			    jsp = "music/newList";
			    break;


			case "/music/topList.do":
			    System.out.println("2. Top 리스트");

			    pageObject = PageObject.getInstance(request);
			    strPerPageNum = request.getParameter("perPageNum");
			    if (strPerPageNum == null) {
			        pageObject.setPerPageNum(10);
			    } else {
			        pageObject.setPerPageNum(Integer.parseInt(strPerPageNum));
			    }
			    pageObject.setPerGroupPageNum(6L);

			    List<MusicVO> topList = (List<MusicVO>) Execute.execute(Init.get(uri), pageObject);
			    System.out.println("MusicController.execute().pageObject = " + pageObject);

			    request.setAttribute("topList", topList);
			    request.setAttribute("pageObject", pageObject);

			    jsp = "music/topList";
			    break;

			 case "/music/increaseHit.do":
                 System.out.println("2-1. 조회수 증가 처리");
                 increaseHit(request, response);
                 break;
				
			 case "/music/view.do":
				  System.out.println("2-2.음원 상세 보기");
                  String strNo = request.getParameter("musicNo");
                  // long타입으로 변환해서 가져옴
                  no = Long.parseLong(strNo);
                  System.out.println(no);
              
                  result = Execute.execute(Init.get(uri), no);
                 
                  MusicVO musicVO = (MusicVO) result;
                  // 음악 상태 체크 및 조회 권한 확인
                  if ("비공개".equals(musicVO.getMusicStatus()) && gradeNo != 9) {
                      session.setAttribute("msg", "비공개 상태의 음악은 조회할 수 없습니다.");
                      jsp = "redirect:topList.do";
                  } else {
                      // 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
                      request.setAttribute("vo", result);
                      jsp = "music/view";
                  }
					break;

			case "/music/writeForm.do":
				System.out.println("3-1.음원 등록 폼");
				request.setAttribute("list", result);
				pageObject = PageObject.getInstance(request);
				result = Execute.execute(Init.get("/album/list.do"), pageObject);
				request.setAttribute("albumList", result);
				jsp = "music/writeForm";
				break;

			case "/music/write.do":
				System.out.println("3-2.음원 등록 처리");
				// 이미지 업로드 처리
				// new MultipartRequest(request, 실제 저장 위치(realPath), 사이즈제한, encoding, 중복처리객체-파일명 뒤에 cnt 붙임(숫자))
				MultipartRequest multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서 
				String musicTitle = multi.getParameter("musicTitle");
				String singer = multi.getParameter("singer");
				String songWriter = multi.getParameter("songWriter");
				String lyricist = multi.getParameter("lyricist");
				String lyric = multi.getParameter("lyric");
				String fileName = multi.getFilesystemName("musicFile");
				albumNo = Long.parseLong(multi.getParameter("albumNo"));
				Long includedNo = Long.parseLong(multi.getParameter("includedNo"));
				
				// id 는 session에서 받아와야 한다. <수정, 삭제할때도 받아와야해서 로그인이 되어있을때 꺼내 사용하자>
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				MusicVO vo = new MusicVO();
				vo.setMusicTitle(musicTitle);
				vo.setSinger(singer);
				vo.setSongWriter(songWriter);
				vo.setLyricist(lyricist);
				vo.setLyric(lyric);
				vo.setMusicFile(savePath + "/" + fileName);
				vo.setAlbumNo(albumNo);
				vo.setIncludedNo(includedNo);
				// 페이지 정보 받기 & uri에 붙이기
				// [ImageController] - ImageWriteService - ImageDAO.write(vo)
				Execute.execute(Init.get(uri), vo);
				
				// jsp 정보 앞에 redirect가 있으면 redirect(자동페이지이동)를 아니면 jsp로 forward 시킨다.
				jsp = "redirect:newList.do?perPageNum=" + multi.getParameter("perPageNum");
				// 메세지
				session.setAttribute("msg", "음원이 등록 되었습니다.");
				break;
			case "/music/updateForm.do":
				System.out.println("4-1.음원 수정 폼");
				// 사용자 -> 서버 : 글번호 수집
				no = Long.parseLong(request.getParameter("musicNo"));
				
				// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
				pageObject = PageObject.getInstance(request);
				// no에 맞는 데이터 가져오기 DB(execute service dao) BoardViewService
				// 전달 데이터 - 글번호, 조회수 증가 여부(1:증가, 0:증가 안함) : 배열 또는 Map
				result = Execute.execute(Init.get("/music/view.do"), no);
				request.setAttribute("vo", result);
				result = Execute.execute(Init.get("/album/list.do"), pageObject);
				request.setAttribute("albumList", result);
				
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				// jsp 정보
				jsp = "music/updateForm";
				break;
			case "/music/update.do":
				System.out.println("4-2. 음원 수정");
				// 수정할 글번호를 받는다. - 데이터 수집
				// 데이터 수집(사용자->서버 : form - input - name)
				
				multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				no = Long.parseLong(multi.getParameter("musicNo"));
				musicTitle = multi.getParameter("musicTitle");
				singer = multi.getParameter("singer");
				songWriter = multi.getParameter("songWriter");
				lyricist = multi.getParameter("lyricist");
				lyric = multi.getParameter("lyric");
				fileName = multi.getFilesystemName("musicFile");
				albumNo = Long.parseLong(multi.getParameter("albumNo"));
				includedNo = Long.parseLong(multi.getParameter("includedNo"));
				System.out.println(no);
				
				// 변수 - vo 저장하고 Service
				// 위에 있는걸 vo에 다 저장함 데이터는 각각 다르니까 new해서 따로 생성
				vo = new MusicVO();
				vo.setMusicNo(no); // Ensure this is correctly set
				vo.setMusicTitle(musicTitle);
				vo.setSinger(singer);
				vo.setSongWriter(songWriter);
				vo.setLyricist(lyricist);
				vo.setLyric(lyric);
				vo.setMusicFile(savePath + "/" + fileName);
				vo.setAlbumNo(albumNo);
				vo.setIncludedNo(includedNo);
				Execute.execute(Init.get(uri), vo);
				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 시키기 : 수정할때는 조회수 증가 안 시킨다.
				// jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:view.do?musicNo=" + no + "&inc=0" + "&" + pageObject.getPageQuery();
				session.setAttribute("msg", "음원이 수정 되었습니다.");
				break;
			case "/music/changeStatus.do":
			    System.out.println("4-2. 음원 상태 수정");

			    // 폼 데이터 수집
			   String musicNo = request.getParameter("musicNo");
			   String status = request.getParameter("MusicStatus");

			    // 변수 - vo 저장하고 Service
			    vo = new MusicVO();
			    vo.setMusicNo(Long.parseLong(musicNo));
			    vo.setMusicStatus(status);

			    // 서비스 호출
			    Execute.execute(Init.get(uri), vo);
			    

			    // 페이지 정보 받기 & URI에 붙이기
			    pageObject = PageObject.getInstance(request);
			    session.setAttribute("msg", "[" + musicNo + "] 음원 상태가 " + status + "로 변경되었습니다.");

			    // 리다이렉트
			    jsp = "redirect:view.do?musicNo="+ musicNo + "&" + pageObject.getPageQuery();
			    break;

			default:
				jsp = "error/404";
				break;
			} // end of switch
		} catch (Exception e) {
			 e.printStackTrace();
			request.setAttribute("e", e);
			jsp = "error/500";
		} // end of try~catch
		return jsp;

	} // end of execute()

	// 음원 조회수 증가 메서드
	private MusicDAO musicDAO = new MusicDAO();

	public void increaseHit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	    Long musicNo = Long.parseLong(request.getParameter("musicNo"));
	    MusicVO musicVO = null;

	    try {
	        // 음악 정보 조회
	        musicVO = musicDAO.view(musicNo);

	        // 음악이 존재하고 비공개 상태가 아니면 조회수 증가
	        if (musicVO != null && !"비공개".equals(musicVO.getMusicStatus())) {
	            boolean success = musicDAO.increaseHitCount(musicNo);
	            response.setContentType("text/plain");
	            response.getWriter().print(success ? "success" : "failure");
	        } else {
	            response.setContentType("text/plain");
	            response.getWriter().print("failure");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setContentType("text/plain");
	        response.getWriter().print("error");
	    }
	}



} // end of class
