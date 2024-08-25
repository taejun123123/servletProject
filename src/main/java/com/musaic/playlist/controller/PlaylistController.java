package com.musaic.playlist.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.playlist.vo.PlaylistVO;
import com.musaic.util.exe.Execute;

public class PlaylistController {
	public String execute(HttpServletRequest request) {
		System.out.println("PlaylistController.execute()--------------");
		// 로그인 처리르 session으로 한다.
		HttpSession session = request.getSession();
		String jsp = null;
		Object result = null;
		String uri = request.getRequestURI();
		
		// login된 정보 중에서 id를 많이 사용한다.
		String id = null;
		LoginVO login = (LoginVO) session.getAttribute("login");
		// login이 되어 있는 경우만 idfmf 꺼내 온다.
		if(login != null) id =login.getId();
		
		
		
		try {
			
			switch (uri) {
			case "/playlist/list.do":
				// [PlaylistController] - (Execute) - PlaylistListService - PlaylistDAO.list()
				System.out.println("1. 플레이 리스트 리스트");
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<PlaylistVO>
				result = Execute.execute(Init.get(uri), id);
				// 가져온 데이터 request에 저장 -> jsp까지 전달한다.
				request.setAttribute("list", result);
				// pageObject 담기
				// WEB-INF/views/ + Playlist/list+ .jsp
				jsp = "playlist/list";
				break;
				
			case "/playlist/write.do":
				System.out.println("3-2. 플레이 리스트 음원 추가 처리 : write");
				//String musicNo = request.getParameter("musictitle");
				PlaylistVO vo = new PlaylistVO();
				result = Execute.execute(Init.get(uri), vo);
				jsp = "redirect:list.do";
				break;
			
			case "/playlist/delete.do":
				System.out.println("4. 플레이 리스트 곡 지우기 ");
				
				String[] strPlaylistNo = request.getParameterValues("playlistNo");
				
				long[] playlistNo = new long[strPlaylistNo.length];
				
				for(int i=0; i<strPlaylistNo.length; i++){
					playlistNo[i]= Long.parseLong(strPlaylistNo[i]);
				}
				result = Execute.execute(Init.get(uri),playlistNo);
				System.out.println("playlistNo=" + playlistNo);
				// WEB-INF/views/ + Playlist/list+ .jsp
				jsp ="redirect:list.do";
				session.setAttribute("msg", "곡 지우기가 되었습니다");
				break;
				
			case "/playlist/multidelete.do":
				System.out.println("4. 플레이 리스트 다중 곡 지우기 ");
				
				String[] strPlaylistNos = request.getParameterValues("playlistNo");
				
				long[] playlistNos = new long[strPlaylistNos.length];
				
				for(int i=0; i<strPlaylistNos.length; i++){
					playlistNos[i]= Long.parseLong(strPlaylistNos[i]);
				}
				result = Execute.execute(Init.get(uri),playlistNos);
				System.out.println("playlistNos=" + playlistNos);
				// WEB-INF/views/ + Playlist/list+ .jsp
				jsp ="redirect:list.do";
				session.setAttribute("msg", "곡 지우기가 되었습니다");
				break;
			
			default:
				jsp = "error/404";
				break;
			}// end of switch
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("e", e);
			jsp = "error/500";
		} // end of try~catch
		return jsp;
	}// end of execute()
} // end of class()
