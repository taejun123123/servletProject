package com.musaic.album.service;

import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class AlbumIncludeService implements Service {
	
	private AlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumDAO) dao;
		
	}

	public Integer service(Object obj) throws Exception {
		// DB board에서 리스트 쿼리 실행해서 데이터 처리 
		
		// AlbumController - (Execute) - [AlbumListService] - [AlbumDAO.update()]
		return dao.include((AlbumVO)obj);
		
		
	}


}
