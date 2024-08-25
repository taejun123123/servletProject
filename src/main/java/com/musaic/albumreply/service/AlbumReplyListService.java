package com.musaic.albumreply.service;

import java.util.List;

import com.musaic.albumreply.dao.AlbumReplyDAO;
import com.musaic.albumreply.vo.AlbumReplyVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.webjjang.util.page.ReplyPageObject;

public class AlbumReplyListService implements Service {

	private AlbumReplyDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumReplyDAO) dao;
		
	}
		
	public List<AlbumReplyVO> service(Object obj) throws Exception {
	
		ReplyPageObject replyPageObject = (ReplyPageObject) obj;
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴 
		// 전체 페이지 개수
		replyPageObject.setTotalRow(dao.getTotalRow(replyPageObject));
		
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardReplyController - (Execute) - [BoardReplyListService] - [BoardReplyDAO.update()]
		return dao.list(replyPageObject);
		
		
	}

}
