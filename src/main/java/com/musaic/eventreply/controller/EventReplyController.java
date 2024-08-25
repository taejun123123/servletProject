package com.musaic.eventreply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.eventreply.vo.EventReplyVO;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.ReplyPageObject;

public class EventReplyController {
	
	public String execute(HttpServletRequest request) {
		System.out.println("EventReplyController.execute()-------------");
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		LoginVO loginVO = (LoginVO) session.getAttribute("login"); 
		
		String id = null;
		if(loginVO != null) id = loginVO.getId();
		
		Object result = null;
		
		String jsp = null;
		
		Long no = 0L;
		
		try {
			ReplyPageObject pageObject
			= ReplyPageObject.getInstance(request);
			System.out.println("eventreplyController-pageObject" + pageObject);
			
			switch (uri) {
			case "/eventreply/write.do":
				System.out.println("이벤트 댓글 등록 처리");
				
				String content = request.getParameter("content");
				
				EventReplyVO vo = new EventReplyVO();
				vo.setNo(pageObject.getNo());
				vo.setContent(content);
				vo.setId(id);			
				
				Execute.execute(Init.get(uri), vo);
				
				jsp = "redirect:/event/view.do?no=" + pageObject.getNo()
				+ "&inc=0"
				+ "&" + pageObject.getPageObject().getPageQuery();
				
				session.setAttribute("msg", "댓글이 입력 되었습니다.");
				
				break;
				
			case "/eventreply/update.do":
				System.out.println("이벤트 댓글 수정 처리");
				Long rno = Long.parseLong(request.getParameter("rno"));
				content = request.getParameter("content");
				
				vo = new EventReplyVO();
				vo.setRno(rno);
				vo.setContent(content);

				Execute.execute(Init.get(uri), vo);
				
				jsp = "redirect:/event/view.do?no=" + pageObject.getNo()
				+ "&inc=0"
				+ "&" + pageObject.getPageObject().getPageQuery();
				
				session.setAttribute("msg", "댓글이 수정 되었습니다.");
				
				break;
				
			case "/eventreply/delete.do":
				System.out.println("3.이벤트 댓글 삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 비밀번호 - BoardVO
				
				rno = Long.parseLong(request.getParameter("rno"));
				
				EventReplyVO deleteVO = new EventReplyVO();
				deleteVO.setRno(rno);
				
				// DB 처리
				Execute.execute(Init.get(uri), deleteVO);
				System.out.println();
				System.out.println("***************************");
				System.out.println("**  " + deleteVO.getNo()+ "글이 삭제되었습니다.  **");
				System.out.println("***************************");
				
				jsp = "redirect:/event/view.do?no=" + pageObject.getNo() + "&inc=0"
						// 일반 게시판의 페이지 & 검색 정보
						+ "&" + pageObject.getPageObject().getPageQuery()
						// 일반 게시판 댓글의 페이지
						+ "&" + pageObject.getPageQuery();
						
				session.setAttribute("msg", "댓글 삭제가 성공적으로 되었습니다.");
				
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return jsp;
	}
}
