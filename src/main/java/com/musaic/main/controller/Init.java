package com.musaic.main.controller;

import java.util.HashMap;
import java.util.Map;
import com.musaic.event.dao.EventDAO;
import com.musaic.event.service.EventChangeService;
import com.musaic.event.service.EventDeleteService;
import com.musaic.event.service.EventListService;
import com.musaic.event.service.EventUpdateService;
import com.musaic.event.service.EventViewService;
import com.musaic.event.service.EventWriteService;
import com.musaic.eventreply.dao.EventReplyDAO;
import com.musaic.eventreply.service.EventReplyDeleteService;
import com.musaic.eventreply.service.EventReplyListService;
import com.musaic.eventreply.service.EventReplyUpdateService;
import com.musaic.eventreply.service.EventReplyWriteService;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.member.db.MemberDAO;
import com.musaic.member.service.MemberChangeGradeService;
import com.musaic.member.service.MemberChangePwService;
import com.musaic.member.service.MemberChangeStatusService;
import com.musaic.member.service.MemberDeleteService;
import com.musaic.member.service.MemberEmailCheckService;
import com.musaic.member.service.MemberFindIdService;
import com.musaic.member.service.MemberFindPwService;
import com.musaic.member.service.MemberIdCheckService;
import com.musaic.member.service.MemberListService;
import com.musaic.member.service.MemberLoginService;
import com.musaic.member.service.MemberUpdateCondateService;
import com.musaic.member.service.MemberUpdateService;
import com.musaic.member.service.MemberViewService;
import com.musaic.member.service.MemberWriteService;
import com.musaic.music.dao.MusicDAO;
import com.musaic.music.service.MusicChangeStatusService;
import com.musaic.music.service.MusicNewListService;
import com.musaic.music.service.MusicTopListService;
import com.musaic.music.service.MusicUpdateService;
import com.musaic.music.service.MusicViewService;
import com.musaic.music.service.MusicWriteService;
import com.musaic.notice.dao.NoticeDAO;
import com.musaic.notice.service.NoticeDeleteService;
import com.musaic.notice.service.NoticeListService;
import com.musaic.notice.service.NoticeUpdateService;
import com.musaic.notice.service.NoticeViewService;
import com.musaic.notice.service.NoticeWriteService;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.service.PayAdminListPayStatusService;
import com.musaic.pay.service.PayAdminListService;
import com.musaic.pay.service.PayCartListService;
import com.musaic.pay.service.PayDeleteService;
import com.musaic.pay.service.PayItemService;
import com.musaic.pay.service.PayListService;
import com.musaic.pay.service.PayOrderCartListService;
import com.musaic.pay.service.PayOrderListService;
import com.musaic.pay.service.PayStatusService;
import com.musaic.pay.service.PayUpdateService;
import com.musaic.pay.service.PayViewService;
import com.musaic.pay.service.PayWriteService;
import com.musaic.playlist.dao.PlaylistDAO;
import com.musaic.playlist.service.PlaylistDeleteservice;
import com.musaic.playlist.service.PlaylistListservice;
import com.musaic.playlist.service.PlaylistMultiWriteService;
import com.musaic.playlist.service.PlaylistViewService;
import com.musaic.playlist.service.PlaylistWriteService;
import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.service.AlbumChangeService;
import com.musaic.album.service.AlbumDeleteService;
import com.musaic.album.service.AlbumIncludeService;
import com.musaic.album.service.AlbumListService;
import com.musaic.album.service.AlbumMusicListService;
import com.musaic.album.service.AlbumTotalMusicService;
import com.musaic.album.service.AlbumUpdateService;
import com.musaic.album.service.AlbumViewService;
import com.musaic.album.service.AlbumWriteService;
import com.musaic.albumreply.dao.AlbumReplyDAO;
import com.musaic.albumreply.service.AlbumReplyDeleteService;
import com.musaic.albumreply.service.AlbumReplyListService;
import com.musaic.albumreply.service.AlbumReplyUpdateService;
import com.musaic.albumreply.service.AlbumReplyWriteService;
import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.cartalbum.service.CartAlbumAddService;
import com.musaic.cartalbum.service.CartAlbumCheckAlbumNoService;
import com.musaic.cartalbum.service.CartAlbumDeleteService;
import com.musaic.cartalbum.service.CartAlbumListService;
import com.musaic.cartalbum.service.CartAlbumMinusService;
import com.musaic.cartalbum.service.CartAlbumUpdateService;
import com.musaic.cartalbum.service.CartAlbumWriteService;

public class Init {

	// service 생성해서 저장하는 객체 - <URI, service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	// static 변수에 데이터를 초기화 시키는 1번만 실행되는 블록 -> 클래스가 불려지만 자동실행
	static {
		
		// ---- [결제 객체 생성과 조립 ] -----------------------		
		daoMap.put("PayDAO", new PayDAO());
		// service 생성
		serviceMap.put("/pay/payWriteForm.do", new PayCartListService());
		serviceMap.put("/pay/payWrite.do", new PayWriteService());
		serviceMap.put("/pay/payList.do", new PayListService());
		serviceMap.put("/pay/payUpdate.do", new PayUpdateService());
		serviceMap.put("/pay/payView.do", new PayViewService());
		serviceMap.put("/pay/payDelete.do", new PayDeleteService());
		serviceMap.put("/pay/adminList.do", new PayAdminListService());
		serviceMap.put("/pay/changeStatus.do", new PayStatusService());
		serviceMap.put("/pay/paySatus.do", new PayAdminListPayStatusService());
		serviceMap.put("/pay/itemlist.do", new PayItemService());
		serviceMap.put("/pay/payOrderList.do", new PayOrderListService());
		serviceMap.put("/pay/orderCartList.do", new PayOrderCartListService());
		// 조립 dao->service
		serviceMap.get("/pay/payWriteForm.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payWrite.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payList.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payUpdate.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payView.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payDelete.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/adminList.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/changeStatus.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/paySatus.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/itemlist.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/payOrderList.do").setDAO(daoMap.get("PayDAO"));
		serviceMap.get("/pay/orderCartList.do").setDAO(daoMap.get("PayDAO"));

		//---------[Member 객체 생성과 조립]--------------------------
		daoMap.put("memberDAO", new MemberDAO());
		serviceMap.put("/member/list.do", new MemberListService());
		serviceMap.put("/member/write.do", new MemberWriteService());
		serviceMap.put("/member/login.do", new MemberLoginService());
		serviceMap.put("/member/view.do", new MemberViewService());
		serviceMap.put("/member/update.do", new MemberUpdateService());
		serviceMap.put("/ajax/idCheck.do", new MemberIdCheckService());
		serviceMap.put("/ajax/emailCheck.do", new MemberEmailCheckService());
		serviceMap.put("/ajax/findpw.do", new MemberFindPwService());
		serviceMap.put("/ajax/findid.do", new MemberFindIdService());
		serviceMap.put("/member/changepw.do", new MemberChangePwService());
		serviceMap.put("/member/delete.do", new MemberDeleteService());
		serviceMap.put("/member/changeGrade.do", new MemberChangeGradeService());
		serviceMap.put("/member/changeStatus.do", new MemberChangeStatusService());
		serviceMap.put("/member/updateCondate.do", new MemberUpdateCondateService());
		
		
		serviceMap.get("/member/list.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/write.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/login.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/update.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/view.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/idCheck.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/emailCheck.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/findpw.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/findid.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changepw.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/delete.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changeGrade.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changeStatus.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/updateCondate.do").setDAO(daoMap.get("memberDAO"));
		
		//--------------[Playlist 객체 생성과 조립]---------------
		daoMap.put("playlistDAO", new PlaylistDAO());
				
		serviceMap.put("/playlist/list.do", new PlaylistListservice());
		serviceMap.put("/playlist/delete.do", new PlaylistDeleteservice());
		serviceMap.put("/playlistAjax/ajax.do", new PlaylistViewService());
		serviceMap.put("/ajax/playlistWrite.do", new PlaylistWriteService());
		serviceMap.put("/ajax/playlistMultiWrite.do", new PlaylistMultiWriteService());
		
		
		serviceMap.get("/playlist/list.do").setDAO(daoMap.get("playlistDAO"));
		serviceMap.get("/playlist/delete.do").setDAO(daoMap.get("playlistDAO"));
		serviceMap.get("/playlistAjax/ajax.do").setDAO(daoMap.get("playlistDAO"));
		serviceMap.get("/ajax/playlistWrite.do").setDAO(daoMap.get("playlistDAO"));
		serviceMap.get("/ajax/playlistMultiWrite.do").setDAO(daoMap.get("playlistDAO"));
		
		// --------- [ 음원 생성 조립 ] -----------
		daoMap.put("musicDAO", new MusicDAO());
	
		// service 생성
		serviceMap.put("/music/newList.do", new MusicNewListService());
		serviceMap.put("/music/topList.do", new MusicTopListService());
		serviceMap.put("/music/view.do", new MusicViewService());
		serviceMap.put("/music/write.do", new MusicWriteService());
		serviceMap.put("/music/update.do", new MusicUpdateService());
		serviceMap.put("/music/changeStatus.do", new MusicChangeStatusService());

		// 조립 dao - service
		serviceMap.get("/music/newList.do").setDAO(daoMap.get("musicDAO"));
		serviceMap.get("/music/topList.do").setDAO(daoMap.get("musicDAO"));
		serviceMap.get("/music/view.do").setDAO(daoMap.get("musicDAO"));
		serviceMap.get("/music/write.do").setDAO(daoMap.get("musicDAO"));
		serviceMap.get("/music/update.do").setDAO(daoMap.get("musicDAO"));
		serviceMap.get("/music/changeStatus.do").setDAO(daoMap.get("musicDAO"));
		// --------- [ 공지사항 조립 ] -----------
		daoMap.put("noticeDAO", new NoticeDAO());
		
		// service 생성
		serviceMap.put("/notice/list.do", new NoticeListService());
		serviceMap.put("/notice/view.do", new NoticeViewService());
		serviceMap.put("/notice/write.do", new NoticeWriteService());
		serviceMap.put("/notice/update.do", new NoticeUpdateService());
		serviceMap.put("/notice/delete.do", new NoticeDeleteService());
		
		// 조립 dao - service
		serviceMap.get("/notice/list.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/view.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/write.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/update.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/delete.do").setDAO(daoMap.get("noticeDAO"));
		
		// --------- [ 이벤트 조립 ] -----------
		daoMap.put("eventDAO", new EventDAO());
		
		// service 생성
		serviceMap.put("/event/list.do", new EventListService());
		serviceMap.put("/event/view.do", new EventViewService());
		serviceMap.put("/event/write.do", new EventWriteService());
		serviceMap.put("/event/update.do", new EventUpdateService());
		serviceMap.put("/event/delete.do", new EventDeleteService());
		serviceMap.put("/event/changeImage.do", new EventChangeService());
		
		// 조립 dao - service
		serviceMap.get("/event/list.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/view.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/write.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/update.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/delete.do").setDAO(daoMap.get("eventDAO"));
	    serviceMap.get("/event/changeImage.do").setDAO(daoMap.get("eventDAO"));

		// ---- [이벤트 댓글 객체 생성과 조립 ] -----------------------
	      daoMap.put("replyEventDAO", new EventReplyDAO());
	      
	      serviceMap.put("/eventreply/list.do", new EventReplyListService());
	      serviceMap.put("/eventreply/write.do", new EventReplyWriteService());
	      serviceMap.put("/eventreply/update.do", new EventReplyUpdateService());
	      serviceMap.put("/eventreply/delete.do", new EventReplyDeleteService());
	      
	      serviceMap.get("/eventreply/list.do").setDAO(daoMap.get("replyEventDAO"));
	      serviceMap.get("/eventreply/write.do").setDAO(daoMap.get("replyEventDAO"));
	      serviceMap.get("/eventreply/update.do").setDAO(daoMap.get("replyEventDAO"));
	      serviceMap.get("/eventreply/delete.do").setDAO(daoMap.get("replyEventDAO"));
		
	      //---------[앨범 장바구니 생성 및 조립]-------------------
				daoMap.put("cartalbumDAO", new CartAlbumDAO());
				
				// Service 생성
				serviceMap.put("/cartalbum/list.do", new CartAlbumListService());
				serviceMap.put("/cartalbum/write.do", new CartAlbumWriteService());
				serviceMap.put("/cartalbum/update.do", new CartAlbumUpdateService());
				serviceMap.put("/cartalbum/delete.do", new CartAlbumDeleteService());
				serviceMap.put("/cartalbum/minus.do", new CartAlbumMinusService());
				serviceMap.put("/cartalbum/add.do", new CartAlbumAddService());
				serviceMap.put("/cartalbum/checkalbumno.do", new CartAlbumCheckAlbumNoService());
				
				// 조립
				serviceMap.get("/cartalbum/list.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/write.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/update.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/delete.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/minus.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/add.do").setDAO(daoMap.get("cartalbumDAO"));
				serviceMap.get("/cartalbum/checkalbumno.do").setDAO(daoMap.get("cartalbumDAO"));
		
		
				// ---- [앨범 관리 객체 생성과 조립 ] -----------------------
				 //dao 생성
				daoMap.put("albumDAO", new AlbumDAO());
				//service 생성
				serviceMap.put("/album/list.do", new AlbumListService());
				serviceMap.put("/album/view.do", new AlbumViewService());
				serviceMap.put("/album/write.do", new AlbumWriteService());
				serviceMap.put("/album/update.do", new AlbumUpdateService());
				serviceMap.put("/album/delete.do", new AlbumDeleteService());
				serviceMap.put("/album/changeImage.do", new AlbumChangeService());
				serviceMap.put("/album/musicList.do", new AlbumMusicListService());
				serviceMap.put("/album/include.do", new AlbumIncludeService());
				serviceMap.put("/ajax/getTotalMusic.do", new AlbumTotalMusicService());
				 //조립 dao->service
				serviceMap.get("/album/list.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/view.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/write.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/update.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/delete.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/changeImage.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/musicList.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/album/include.do").setDAO(daoMap.get("albumDAO"));
				serviceMap.get("/ajax/getTotalMusic.do").setDAO(daoMap.get("albumDAO"));
				//---------[앨범 댓글 객체 생성과 조립]---------------------------------
				daoMap.put("albumreplyDAO", new AlbumReplyDAO());
				// service 생성
				serviceMap.put("/albumreply/list.do", new AlbumReplyListService()); 
				serviceMap.put("/albumreply/write.do", new AlbumReplyWriteService()); 
				serviceMap.put("/albumreply/update.do", new AlbumReplyUpdateService()); 
				serviceMap.put("/albumreply/delete.do", new AlbumReplyDeleteService());		
				// 조립 dao -> servive
				serviceMap.get("/albumreply/list.do").setDAO(daoMap.get("albumreplyDAO"));
				serviceMap.get("/albumreply/write.do").setDAO(daoMap.get("albumreplyDAO"));
				serviceMap.get("/albumreply/update.do").setDAO(daoMap.get("albumreplyDAO"));
				serviceMap.get("/albumreply/delete.do").setDAO(daoMap.get("albumreplyDAO"));		
		
		System.out.println("Init.static 초기화 블록 ----- 객체 생성과 로딩 ------");
	}
	
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
	
}
