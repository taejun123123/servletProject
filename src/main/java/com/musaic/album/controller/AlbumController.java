package com.musaic.album.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.music.vo.MusicVO;
import com.musaic.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.ReplyPageObject;
// Album Module에 맞는 메뉴 선택, 데이터 수집, 예외 처리
public class AlbumController {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request) {
		System.out.println("AlbumController.execute() --------------------------");
		
			// session을 request에서부터 꺼낸다.
			HttpSession session = request.getSession();
			// 메뉴 입력
			String uri = request.getRequestURI();
			
			Object result = null;
			// 메뉴 처리
			Long no = 0L;
			String id = null;
			Integer gradeNo = 0;
			LoginVO login=(LoginVO) session.getAttribute("login");
			
			if(login!=null) {
				id =login.getId();
				gradeNo = login.getGradeNo().intValue();
			}
			String jsp = null;
			
			// 파일 업로드 설정 -----------
			// 파일의 상대적인 저장위치
			String savePath = "/upload/image";
			// 파일 시스템에서는 절대 저장위치가 필요하다. - 파일 업로드 시 필요.
			String realSavePath = request.getServletContext().getRealPath(savePath);
			
			// 업로드 파일 용량 제한
			int sizeLimit = 100 * 1024 * 1024;
			
			try { //정상처리
				
				// 메뉴 처리 : CRUD DB처리 - controller - service - DAO
				switch (uri) {
				case "/album/list.do":
					//[AlbumController] - (Excute) - AlbumListService - AlbumDAO.list()
					System.out.println("1. 앨범 게시판 리스트");
					// 페이지 처리를 위한 객체
					// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
					PageObject pageObject = PageObject.getInstance(request);
					// 이미지 게시판의 한 페이지 이미지의 개수의 기본 값을 6으로 처리하자.
				    // 중복처리 - 앞의 데이터에 덮어쓰기가 된다.
				    String strPerPageNum = request.getParameter("perPageNum");
				    if(strPerPageNum == null) pageObject.setPerPageNum(6);
				    pageObject.setAcceptMode(gradeNo);
					// DB에서 데이터 가져오기 - 가져온 데이터는 List<AlbumVO>
					result = Execute.execute(Init.get(uri), pageObject);
					// PageObject 확인하기ㅏ
					System.out.println("AlbumController.execute().pageObject = " + pageObject);
					// 가져온 데이터 출력하기
					request.setAttribute("list", result);
					// PageObject 담기
					request.setAttribute("pageObject", pageObject);
					// /WEB-INF/views/ + Album/list + .jsp
					jsp = "album/list";
					
					break;
				case "/album/view.do":
					System.out.println("2. 앨범 상세 보기");
					pageObject =  new PageObject();
					// 1. 조회수 1증가(글보기)
					String strNo = request.getParameter("no");
					no = Long.parseLong(strNo);
					// 2. 앨범게시판 글보기 데이터 가져오기 : 글보기 , 글수정
					//전달 데이터 - 글번호, 조회수 증가 여부 (1:증가 0: 증가 안함) : 배열 또는 Map
					result = Execute.execute(Init.get(uri), no);
					
					request.setAttribute("vo", result);
					
					result = Execute.execute(Init.get("/album/musicList.do"), no);
						
					request.setAttribute("musicList", result);
					// 댓글 페이지 객체
					// 데이터 전달 - page / perPageNum / no / replyPage / replyPerPageNum
					ReplyPageObject replyPageObject = ReplyPageObject.getInstance(request);
					// 가져온 댓글 데이터 request에 "담기"
					request.setAttribute("replyList", Execute.execute(Init.get("/albumreply/list.do"), replyPageObject));
					// 댓글 페이지 객체 "담기"
					request.setAttribute("replyPageObject",replyPageObject);
					jsp = "/album/view";				
					
					break;
				case "/album/includeForm.do":
					System.out.println("2-1. 앨범 수록곡 등록 폼");
					
				    // PageObject 인스턴스 가져오기
				    pageObject = PageObject.getInstance(request);

				    // perPageNum 파라미터 가져오기
				    strPerPageNum = request.getParameter("perPageNum");
				    String albumNo1 = request.getParameter("no");

				    // 페이지 그룹 내 항목 수 설정 (이 설정은 페이지당 항목 수와 관련이 없음)
				    pageObject.setPerGroupPageNum(6L);
				    pageObject.setAcceptMode(gradeNo);
				    // 페이지당 항목 수
				    if(strPerPageNum == null)pageObject.setPerPageNum(25);
				    System.out.println("perPageNum parameter: " + strPerPageNum);

				    // 음악 리스트 가져오기
				    List<MusicVO> newList = (List<MusicVO>) Execute.execute(Init.get("/music/newList.do"), pageObject);
				    System.out.println("MusicController.execute().pageObject = " + pageObject);

				    // 요청 속성에 새 리스트와 PageObject 설정
				    request.setAttribute("newList", newList);
				    request.setAttribute("pageObject", pageObject);
				    request.setAttribute("albumNo", albumNo1);
					
					jsp = "/album/includeForm";
					break;
					
				case "/album/include.do":
					System.out.println("2-1. 앨범 수록곡 등록 처리");
					
					
					Long albumNo = Long.parseLong(request.getParameter("albumNo"));
					// 수록곡 등록 처리할 음원번호를 배열로 받아온다.
					String [] musicNo = request.getParameterValues("musicNo");
					String perPageNum = request.getParameter("perPageNum");
					// 배열을 문자열로 변환
//					String passMusic = "";
//					for (String mNo : musicNo) {
//						passMusic += "," + mNo;
//					}
//					String passNO = passMusic.substring(1, passMusic.length());
//					System.out.println(Arrays.toString(musicNo));
					// 변수 - vo 저장하고 Service 
					AlbumVO vo = new AlbumVO();
					vo.setAlbumNo(albumNo);
//					vo.setPassNo(passNO);
					vo.setMusicArray(musicNo);
					
					// [AlbumController] - AlbumIncludeService - AlbumDAO.include(vo)
					Execute.execute(Init.get(uri), vo);
					//페이지 정보 받기 & uri에 붙이기
					pageObject = PageObject.getInstance(request);					
					// 글보기로 자동 이동 -> jsp정보를 작성해서 넘긴다.
					jsp = "redirect:view.do?no=" + albumNo + "&" + pageObject.getPageQuery();
					break;
					
					
				case "/album/writeForm.do":
					System.out.println("3-1. 앨범 등록 폼");
					jsp = "/album/writeForm";
					break;
				
				case "/album/write.do":
					System.out.println("3. 앨범 등록 처리");
					MultipartRequest multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
					// 데이터 수집 - 사용자 -> 서버 : form - input - name 
					//title, release_date, artist, price, genre, info, image, status
					String title = multi.getParameter("title");
					String release_date = multi.getParameter("release_date");
					String artist = multi.getParameter("artist");
					String price = multi.getParameter("price");
					String genre = multi.getParameter("genre");
					String info = multi.getParameter("info");
					String image = multi.getFilesystemName("image");
					String status = multi.getParameter("status");
					perPageNum = multi.getParameter("perPageNum");
					
					// 변수 - vo 저장하고 Service 
					vo = new AlbumVO();
					vo.setTitle(title);
					vo.setRelease_date(release_date);
					vo.setArtist(artist);
					vo.setPrice(price);
					vo.setGenre(genre);
					vo.setInfo(info);
					vo.setImage(savePath + "/" + image);
					vo.setStatus(status);
					// [AlbumController] - AlbumWriteService - AlbumDAO.write(vo)
					Execute.execute(Init.get(uri), vo);
					
					// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를 
					// 아니면 jsp로 forward를 시킨다.
					jsp = "redirect:list.do?perPageNum=" + perPageNum;
					session.setAttribute("msg", "글 등록이 성곡적으로 되었습니다.");
					
					break;
				case "/album/updateForm.do":
					System.out.println("4-1. 앨범 게시판 글 수정 폼");
					// 사용자 -> 서버 : 글번호 
					no = Long.parseLong(request.getParameter("no"));
					// no 맞는 데이터 DB에서 가져온다. AlbumViewService
					//전달 데이터 - 글번호, 조회수 증가 여부 (1:증가 0: 증가 안함) : 배열 또는 Map
					result = Execute.execute(Init.get("/album/view.do"), no);
					// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
					request.setAttribute("vo", result);
					// jsp 정보
					jsp = "album/updateForm";
					break;
					
				case "/album/update.do":
					
					System.out.println("4-2. 앨범 게시판 글 수정 처리");
				
					multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
					// 데이터 수집 - 사용자 -> 서버 : form - input - name 
					//title, release_date, artist, price, genre, info, image, status
					albumNo = Long.parseLong(multi.getParameter("albumNo"));
					title = multi.getParameter("title");
					release_date = multi.getParameter("release_date");
					artist = multi.getParameter("artist");
					price = multi.getParameter("price");
					genre = multi.getParameter("genre");
					info = multi.getParameter("info");
					status = multi.getParameter("status");
					perPageNum = multi.getParameter("perPageNum");
					
					// 변수 - vo 저장하고 Service 
					vo = new AlbumVO();
					vo.setAlbumNo(albumNo);
					vo.setTitle(title);
					vo.setRelease_date(release_date);
					vo.setArtist(artist);
					vo.setPrice(price);
					vo.setGenre(genre);
					vo.setInfo(info);
					vo.setStatus(status);
					
					// DB에 데이터 수정하기 - AlbumUpdateService
					Execute.execute(Init.get(uri), vo);
					//페이지 정보 받기 & uri에 붙이기
					pageObject = PageObject.getInstance(request);					
					// 글보기로 자동 이동 -> jsp정보를 작성해서 넘긴다.
					jsp = "redirect:view.do?no=" + albumNo + "&" + pageObject.getPageQuery();
					session.setAttribute("msg", "글 수정이 성곡적으로 되었습니다.");
					break;
					
				case "/album/delete.do":
					System.out.println("5. 앨범 삭제");
					// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, pw - AlbumVO
					albumNo = Long.parseLong(request.getParameter("albumNo"));
					perPageNum = request.getParameter("perPageNum");
				
					vo = new AlbumVO();
					vo.setAlbumNo(albumNo);
					
					
					// DB 처리
					Execute.execute(Init.get(uri), vo);
					System.out.println();
					System.out.println("***************************");
					System.out.println("**"+ vo.getAlbumNo()+"번 게시글이 삭제되었습니다. "+"**");
					System.out.println("***************************");
					
					// 파일 삭제 
					// 삭제할 파일 이름
					String deleteImageName = request.getParameter("deleteImageName");
					File deleteImage = new File(request.getServletContext().getRealPath(deleteImageName));
					if (deleteImage.exists()) deleteImage.delete();
					
					jsp = "redirect:list.do?" + "&" + "perPageNum="+ perPageNum;
					session.setAttribute("msg", "글 삭제가 성곡적으로 되었습니다.");
					break;
				
				case "/album/changeImage.do":
					
					System.out.println("6. 이미지 바꾸기 처리");
				
					// 파일 업로드 [cos 라이브러리] 사용  MultipartRequest
					// new MultipartRequest(request, 실제저장위치, 사이즈 제한, encoding, 중복처리 객체)
					// file 객체 업로드 시 input의 name이 같으면 한개만 처리 가능.
					// name을 다르게 해서 올려야 한다. file1, file2 
					multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
					// 데이터 수집 - 사용자 -> 서버 : form - input - name 
					albumNo = Long.parseLong(multi.getParameter("albumNo"));
					image = multi.getFilesystemName("image");
					
					deleteImageName =multi.getParameter("deleteImageName");
					// 변수 - vo 저장하고 Service 
					vo = new AlbumVO();
					vo.setAlbumNo(albumNo);
					vo.setImage(savePath+ "/" + image);
					
					
					// DB에 데이터 수정하기 - ImageChangeService
					Execute.execute(Init.get(uri), vo);
					// 지난 이미지 파일이 존재하면 지운다.
					deleteImage = new File(request.getServletContext().getRealPath(deleteImageName));
					if (deleteImage.exists()) deleteImage.delete();
					// 처리결과 메세지 전달
					session.setAttribute("msg", "이미지가 바꾸기에 성공하셨습니다.");
					//페이지 정보 받기 & uri에 붙이기
					pageObject = PageObject.getInstance(request);					
					// 글보기로 자동 이동 -> jsp정보를 작성해서 넘긴다.
					jsp = "redirect:view.do?no=" + albumNo + "&" + pageObject.getPageQuery();
					break;
					
	
				default:
					jsp = "error/404";
					break;
				}// end of switch
			
			}catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
				
				// 예외객체를 jsp에서 사용하기 위해 request에 담는다.
				request.setAttribute("e", e);
				e.printStackTrace();
				jsp = "error/500";
				
				
			} // end of try ~ catch
			return jsp;
	} //end of execute()
}// end of class
