package com.musaic.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.member.db.MemberVO;
import com.musaic.playlist.vo.PlaylistVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.PageObject;

public class AjaxController {
	public String execute(HttpServletRequest request) {
		String uri=request.getRequestURI();
		
		String jsp=null;
		String id=null;
		Object result=null;
		
		// 로그인 처리를 sesstion으로 한다.
		HttpSession session = request.getSession();
		
		LoginVO login = (LoginVO) session.getAttribute("login");
		// login이 되어 있는 경우만 id를 꺼내온다.
		if(login != null) id = login.getId();
		
		try {
			switch(uri) {
			case "/ajax/idCheck.do":
				System.out.println("------ Ajax Controller id check -----");
				
				id=request.getParameter("id");
				result=Execute.execute(Init.get(uri), id);
				
				request.setAttribute("id", result);
				
				jsp="ajax/idCheck";
				break;
			case "/ajax/emailCheck.do":
				System.out.println("------ Ajax Controller email check -----");
				
				String email=request.getParameter("email");
				result=Execute.execute(Init.get(uri), email);
				
				request.setAttribute("email", result);
				
				jsp="ajax/emailCheck";
				break;
			case "/ajax/findpw.do":
				System.out.println("------ Ajax Controller find pw -----");
				
				id=request.getParameter("id");
				email=request.getParameter("email");
				
				System.out.println("id: "+id+", email: "+email);
				
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setEmail(email);
				
				result=Execute.execute(Init.get(uri), vo);
				
				request.setAttribute("result", result);
				
				jsp="ajax/findpw";
				break;
			case "/ajax/findid.do":
				System.out.println("------ Ajax Controller find id -----");
				
				String name=request.getParameter("name");
				email=request.getParameter("email");
				
				vo=new MemberVO();
				vo.setName(name);
				vo.setEmail(email);
				
				System.out.println("email: "+email+", name: "+name);
				
				result=Execute.execute(Init.get(uri), vo);
				
				request.setAttribute("id", result);
				
				jsp="ajax/findid";
				break;
				
				case "/ajax/playlistWrite.do":
					System.out.println("---------- 플레이 리스트 -----------");
					System.out.println(request.getParameter("albumNo"));
					System.out.println(request.getParameter("musicNo"));
					PlaylistVO playlistVO = new PlaylistVO();
					playlistVO.setAlbumNo(Long.parseLong(request.getParameter("albumNo")));
					playlistVO.setMusicNo(Long.parseLong(request.getParameter("musicNo")));
					playlistVO.setId(id);
					System.out.println(id);
					result=Execute.execute(Init.get(uri), playlistVO);
					System.out.println(result);
					request.setAttribute("vo", result);
					System.out.println("플레이리스트에 등록이 완료 되었습니다.");
					jsp = "album/playlistWrite";
					break;
					
				case "/ajax/playlistMultiWrite.do":
					System.out.println("---------- 플레이 리스트 다중 등록-----------");
					System.out.println(request.getParameter("albumNo"));
					playlistVO = new PlaylistVO();
					playlistVO.setAlbumNo(Long.parseLong(request.getParameter("albumNo")));
					playlistVO.setId(id);
					result=Execute.execute(Init.get(uri), playlistVO);
					request.setAttribute("vo", result);
					System.out.println("플레이리스트에 다중 등록이 완료 되었습니다.");
					System.out.println(result);
					jsp = "album/playlistMultiWrite";
					break;
					
				case "/ajax/getTotalMusic.do":
					System.out.println("2. 수록곡 개수 가져오기");
					Long no = Long.parseLong(request.getParameter("albumNo"));
					// [AjaxController] - (Execute) - [MembercheckIdService] - [MemberDAO.checkId(id)]
					// session에 데이터를 담아서 로그인 처리한다.
					result = Execute.execute(Init.get(uri), no);
//					System.out.println("albumNo="+no +"넘어오는 값=" + result);
					request.setAttribute("vo", result);
					
					jsp = "album/getTotalMusic";
					
					break;	
			}//end of switch
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		return jsp;
	}
}