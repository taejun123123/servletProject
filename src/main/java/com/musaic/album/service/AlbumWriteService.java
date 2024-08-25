package com.musaic.album.service;

import com.musaic.album.dao.AlbumDAO;
import com.musaic.album.vo.AlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class AlbumWriteService implements Service {

	private AlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		
		this.dao = (AlbumDAO) dao;
		
	}
	public Integer service(Object obj) throws Exception {
		// DB Album에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴 
		// DB 처리는 DAO에서 처리 - AlbumDAO.list()
		// AlbumController - (Execute) - [AlbumListService] - [AlbumDAO.list()]
		return dao.write((AlbumVO)obj);
		
		
	}

}