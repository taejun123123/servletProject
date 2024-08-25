package com.musaic.album.service;

import java.util.List;

import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class AlbumMusicListService implements Service {

	private AlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumDAO) dao;
		
	}
		
	public List<AlbumVO> service(Object obj) throws Exception {
	
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardController - (Execute) - [BoardListService] - [BoardDAO.list()]
		return dao.musicList((Long)obj);
		
		
	}

}
