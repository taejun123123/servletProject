package com.musaic.albumreply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.albumreply.vo.AlbumReplyVO;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.ReplyPageObject;
// Album Module에 맞는 메뉴 선택, 데이터 수집, 예외 처리
public class AlbumReplyController {

	public String execute(HttpServletRequest request) {
		System.out.println("AlbumReplyController.execute() --------------------------");
			
			// session을 request에서부터 꺼낸다.
			HttpSession session = request.getSession();
			
			
			String uri = request.getRequestURI();
			// login된 정보 중에서 id를 많이 사용한다.
			String id = null;
			LoginVO login = (LoginVO) session.getAttribute("login");
			// login이 되어 있는 경우만 id를 꺼내온다.
			if(login != null) id = login.getId();
			System.out.println("@@@@@@@@@id=" + id);
			String jsp = null;
			
			try { //정상처리
				
				ReplyPageObject pageObject = ReplyPageObject.getInstance(request);				
				
				// 메뉴 처리 : CRUD DB처리 - controller - service - DAO
				switch (uri) {
				
				
				case "/albumreply/write.do":
					System.out.println("1. 앨범 댓글등록 처리");
					
					// 데이터 수집 - 사용자 -> 서버 : form - input - name 
					// albumNo, content, id, rating
					System.out.println("@@@@@@@@@@"+request.getParameter("no"));
					Long albumNo = Long.parseLong(request.getParameter("no"));
					String content = request.getParameter("content");
					String rating = request.getParameter("rating");
					
					// 변수 - vo 저장하고 Service 
					AlbumReplyVO vo = new AlbumReplyVO();
					vo.setContent(content);
					vo.setId(id);
					vo.setAlbumNo(albumNo);
					vo.setRating(rating);
					
					
					// [AlbumController] - AlbumWriteService - AlbumDAO.write(vo)
					Execute.execute(Init.get(uri), vo);
					
					// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를 
					// 아니면 jsp로 forward를 시킨다.
					// 
					jsp = "redirect:/album/view.do?no=" + pageObject.getNo() + "&" 
					// 일반게시판의 페이지 & 검색 정보 붙이기
					+pageObject.getPageObject().getPageQuery();
					
					session.setAttribute("msg", "댓글 등록이 성곡적으로 되었습니다.");
					break;
		
					
				case "/albumreply/update.do":
					
					System.out.println("2. 일반 게시판 댓글 수정 처리");
				
					// 데이터 수집 - 사용자 -> 서버 : form - input - name 
					Long rno = Long.parseLong(request.getParameter("rno"));
					
					content = request.getParameter("content");
					rating = request.getParameter("rating");
				
					
					// 변수 - vo 저장하고 Service 
					vo = new AlbumReplyVO();
					vo.setRno(rno);
					vo.setContent(content);
					vo.setRating(rating);
					
					// DB에 데이터 수정하기 - AlbumUpdateService
					Execute.execute(Init.get(uri), vo);
					//페이지 정보 받기 & uri에 붙이기
						
					// 글보기로 자동 이동 -> jsp정보를 작성해서 넘긴다.
					jsp = "redirect:/album/view.do?no=" + pageObject.getNo() + "&" 
							// 일반게시판의 페이지 & 검색 정보 붙이기
							+pageObject.getPageObject().getPageQuery();
					session.setAttribute("msg", "댓글 수정이 성곡적으로 되었습니다.");
					break;
					
				case "/albumreply/delete.do":
					System.out.println("3. 일반 게시판 댓글 삭제");
					// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, pw - AlbumVO
					rno = Long.parseLong(request.getParameter("rno"));
					
				
					vo = new AlbumReplyVO();
					vo.setRno(rno);
					
					// DB 처리
					Execute.execute(Init.get(uri), vo);
					System.out.println();
					System.out.println("***************************");
					System.out.println("**"+ vo.getRno()+"번 게시글이 삭제되었습니다. "+"**");
					System.out.println("***************************");

					jsp = "redirect:/album/view.do?no=" + pageObject.getNo() + "&inc=0" + "&" 
							// 일반게시판의 페이지 & 검색 정보 붙이기
							+pageObject.getPageObject().getPageQuery();
					
					session.setAttribute("msg", "댓글 삭제가 성곡적으로 되었습니다.");
					break;
				
				default:
					System.out.println("================================");
					System.out.println("==    잘못된 메뉴를 입력하셨습니다.   ==");
					System.out.println("== [0~5,0] 중에서 입력하셔야 합니다. ==");
					System.out.println("================================");
					break;
				}// end of switch
			
			}catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
				System.out.println();
				System.out.println("$%@$%@%$%@$%$@%@$%$@$%@%$$@%$%@$%@$%@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%$@%       오류 출력        @$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%$@%@$%$@$%@%$$@%$%@$%@$%@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%  타입 : "+ e.getClass().getSimpleName() + "@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%  내용 : "+ e.getMessage() + "@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%  조치 : "+ "데이터를 확인후 다시 실행하시오" + "@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%   : "+ "계속 오류가 나면 전산담당자에게 연락하시오" + "@$%@$%@$%$@$@%");
				System.out.println("$%@$%@%$%@$%$@%@$%$@$%@%$$@%$%@$%@$%@$%@$%@$%$@$@%");
				
				
			} // end of try ~ catch
			return jsp;
	} //end of execute()
}// end of class
