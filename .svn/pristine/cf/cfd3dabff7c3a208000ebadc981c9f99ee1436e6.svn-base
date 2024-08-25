package com.musaic.main.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musaic.ajax.controller.AjaxController;
import com.musaic.album.controller.AlbumController;
import com.musaic.albumreply.controller.AlbumReplyController;
import com.musaic.cartalbum.controller.CartAlbumController;
import com.musaic.event.controller.EventController;
import com.musaic.eventreply.controller.EventReplyController;
import com.musaic.member.controller.MemberController;
import com.musaic.music.controller.MusicController;
import com.musaic.notice.controller.NoticeController;
import com.musaic.pay.controller.PayController;
import com.musaic.playlist.controller.PlaylistAjaxController;
import com.musaic.playlist.controller.PlaylistController;


/**
 * Servlet implementation class DispatcherServlet
 */
// 웹서버와 연결하기 위해서 sevlet으로 등록이 되어 있어야 한다.
// 1. @WebServlet - 프로그램 수정 가능, 2. web.xml에 등록 - 프로그램 수정 불가능
// @WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
// Servlet을 상속 - 기능 : URL 연결 - 서버에서 동작 프로그램 - 한번만 생성(싱글톤 프로그램)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PayController payController = new PayController();
	private Maincontroller maincontroller = new Maincontroller();
	private MemberController memberController = new MemberController();
	private PlaylistController playlistController = new PlaylistController();
	private PlaylistAjaxController playlistAjaxController = new PlaylistAjaxController();
	private MusicController musicController = new MusicController();
	private NoticeController noticeController = new NoticeController();
	private EventController eventController = new EventController();
	private CartAlbumController cartAlbumController = new CartAlbumController();
	private AjaxController ajaxController = new AjaxController();
	private AlbumController albumController = new AlbumController();
	private AlbumReplyController albumReplyController =new AlbumReplyController();
	private EventReplyController eventReplyController = new EventReplyController();
	// Controller 선언과 생성 - 1번만 된다.
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 드라이버 확인 / 객체 생성 처리 - Class.forName(class명)
		// 서버가 실행될 때 먼저 실행되어야만 한다.
		System.out.println("DispatcherServlet.init() --- 초기화 진행 -------");
		try {
			// -- 객체 생성과 초기화 + 조립
			Class.forName("com.musaic.main.controller.Init");
			// -- 오라클 드라이버 확인 + 로딩
			Class.forName("com.musaic.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 메뉴를 출력하고 선택(uri)하고 처리
		// uri - /board가 앞에 붙어 있으면 BoardController로 가게 만든다.
		System.out.println("DispatcherServlet.service()-----------------------");
		
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
		// --- 음원 에서 담기 버튼 이용시 생성한 코드
	    boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		
		//main 처리 - localhost -> localhost / main.do ->/main/main.do
		
		
		if(uri.equals("/") || uri.equals("/main.do")){
			response.sendRedirect("/main/main.do");
			return;
		}
		// uri = /module/기능 -> /board/list.do
		int pos = uri.indexOf("/", 1);
		System.out.println("pos = " + pos);
		
		if(pos == -1) {
			request.setAttribute("uri", request.getRequestURI());
			request.getRequestDispatcher("/WEB-INF/views/error/noModule_404.jsp")
			.forward(request, response);
			return;
		}
		
		String module = uri.substring(0, pos);
		System.out.println("module = " + module);
		String jsp = null;
		
		//request에 module 담아서 어떤 메뉴가 선택되었는지 처리 : deafult_decorator.jsp
		request.setAttribute("module", module);
		
		switch (module) {
		case "/pay":
			System.out.println("결제");
			jsp = payController.execute(request);
			break;
		case "/main":
			System.out.println("메인 처리");
			jsp = maincontroller.execute(request);
			break;
		case "/member":
			System.out.println("회원 처리");
			jsp = memberController.execute(request);
			break;

		case "/playlist":
			System.out.println("플레이 리스트");
			jsp = playlistController.execute(request);
			break;
		case "/notice":
			System.out.println("공지사항 처리");
			jsp = noticeController.execute(request);
			break;
		case "/event":
			System.out.println("이벤트 처리");
			jsp = eventController.execute(request);
			break;
	
		case "/ajax":
			System.out.println("아작스 처리");
			jsp = ajaxController.execute(request);
			request.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp").forward(request, response);
			return;
		case "/playlistAjax":
			System.out.println("아작스 처리");
			jsp = playlistAjaxController.execute(request);
			request.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp").forward(request, response);
			return;
			

		  case "/music":
              if (isAjaxRequest && uri.equals("/music/increaseHit.do")) {
                  try {
					musicController.increaseHit(request, response);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  return;
              } else {
                  jsp = musicController.execute(request, response);
              }
              break;
		  case "/cartalbum":
				System.out.println("앨범 장바구니 처리");
				jsp = cartAlbumController.execute(request);
				break;
	      case "/album":
	          System.out.println("앨범 관리");
	          jsp = albumController.execute(request);
	          break;
	       case "/albumreply":
	          System.out.println("앨범 댓글 관리");
	          jsp = albumReplyController.execute(request);
	          break;			
	       case "/eventreply":
	           System.out.println("이벤트 댓글");
	           jsp = eventReplyController.execute(request);
	           break;	
		default:
			request.setAttribute("uri", request.getRequestURI());
			request.getRequestDispatcher("/WEB-INF/views/error/noModule_404.jsp")
			.forward(request, response);
			return;
		}
		
		// jsp 정보 앞에 "redirect:"이 붙어 있으면 redirect 시킨다.(페이지 자동 이동)
		// jsp 정보 앞에 "redirect:"이 붙어 있지 않으면 jsp로 forward 시킨다.
        if (jsp != null && isAjaxRequest) {
            response.setContentType("text/plain");
            response.getWriter().print(jsp);
        } else if (jsp.startsWith("redirect:")) {
            response.sendRedirect(jsp.substring("redirect:".length()));
        } else if (jsp != null) {
            request.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp").forward(request, response);
        }
		
	}

}
