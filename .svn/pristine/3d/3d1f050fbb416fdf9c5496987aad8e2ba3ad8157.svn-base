package com.musaic.playlist.controller;

import javax.servlet.http.HttpServletRequest;

import com.musaic.main.controller.Init;
import com.musaic.util.exe.Execute;

public class PlaylistAjaxController {
	public String execute(HttpServletRequest request) {
		// 디버그 메세지 출력
		System.out.println("playlistAjaxController.execute()-----------------");
		String jsp = null;
		Object result = null;
		String uri = request.getRequestURI();
		Long musicNo = 0L;
		
		try {
			switch (uri) {
			case "/playlistAjax/ajax.do":
				// 플레이 리스트 상세보기
				System.out.println("2. 플레이 리스트 상세보기");
				String strmusicNo = request.getParameter("musicNo");
				musicNo = Long.parseLong(strmusicNo);
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<PlaylistVO>
				result = Execute.execute(Init.get(uri), musicNo);
				// 가져온 데이터 request에 저장 -> jsp까지 전달한다.
				request.setAttribute("vo", result);
				// WEB-INF/views/ + playlist/view+.jsp
				jsp = "playlist/view";
				break;
			}
		}catch (Exception e) {
			// 예외 발생 시 스택 트레이스 출력
			e.printStackTrace();
			request.setAttribute("e", e);
			// 오류 페이지 지정
			jsp = "error/500";
		}
		return jsp;
	}

}
