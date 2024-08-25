package com.musaic.main.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.PageObject;

// Main Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class Maincontroller {

	public String execute(HttpServletRequest request) {
		System.out.println("MainController.execute() --------------------------");
		// uri
		String uri = request.getRequestURI();
		
		Object result = null;
		
		String jsp = null;
		
		HttpSession session =request.getSession();
		Long gradeNo = 0L;
		LoginVO login = (LoginVO) session.getAttribute("login");
		if (login != null) {
			gradeNo = login.getGradeNo();
		}
//		
		try { // 정상 처리
		
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/main/main.do":
				System.out.println("1.메인처리");
				
				// 페이지 처리를 위한 객체 생성
				PageObject pageObject = new PageObject();
				
				if (gradeNo == 9) {
					pageObject.setPeriod("all");
				}
				else {
					pageObject.setPeriod("pre");
					
				}
				// 메인에 표시할 데이터 - 최신음악 / top10 / 이미지
				// DB에서 데이터 가져오기
	            // newMusic
	            pageObject.setPerPageNum(10);
	            // [MainController] - (Execute) - MusicNewListService - MusicDAO.list()
	            result = Execute.execute(Init.get("/music/newList.do"), pageObject);
	            request.setAttribute("newList", result);
	            
	            // DB에서 데이터 가져오기
	            // top10
	            pageObject.setPerPageNum(10);
	            // [MainController] - (Execute) - MusicNewListService - MusicDAO.getMusicSortedByHit()
	            result = Execute.execute(Init.get("/music/topList.do"), pageObject);
	            request.setAttribute("topList", result);
	            
	            // Album
	            pageObject.setPerPageNum(6);
	            // [MainController] - (Execute) - ImageListService - ImageDAO.list()
	            result = Execute.execute(Init.get("/album/list.do"), pageObject);
	            request.setAttribute("albumList", result);
	            
				// /WEB-INF/views/ + board/list + .jsp
				jsp = "main/main";
				break;
				
			default:
				jsp = "error/404";
				break;
			} // end of switch
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			
			// 예외객체를 jsp에서 사용하기 위해 request에 담는다.
			request.setAttribute("e", e);
			
			jsp = "error/500";
		} // end of try~catch
		return jsp;
	} // end of execute()
	
} // end of class
