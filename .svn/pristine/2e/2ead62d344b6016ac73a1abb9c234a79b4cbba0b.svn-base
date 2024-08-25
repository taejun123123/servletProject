package com.musaic.album.service;

import java.util.List;

import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.webjjang.util.page.PageObject;

public class AlbumListService implements Service {

	private AlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumDAO) dao;
		
	}
		
	public List<AlbumVO> service(Object obj) throws Exception {
	
		PageObject pageObject = (PageObject) obj;
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴 
		// 전체 페이지 개수
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardController - (Execute) - [BoardListService] - [BoardDAO.list()]
		return dao.list(pageObject);
		
		
	}

}
