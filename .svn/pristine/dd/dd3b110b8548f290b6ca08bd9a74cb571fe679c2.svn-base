package com.musaic.album.service;

import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class AlbumViewService implements Service {
	
	private AlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumDAO) dao;
		
	}
	@Override
	public AlbumVO service(Object obj) throws Exception {
	
		
		// DB 처리는 DAO에서 처리 - AlbumDAO.list()
		// AlbumController - (Execute) - [AlbumViewService] - [AlbumDAO.list()]
		return dao.view((Long) obj);
	}

}
