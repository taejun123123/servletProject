package com.musaic.cartalbum.service;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class CartAlbumDeleteService implements Service {

	private CartAlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (CartAlbumDAO) dao; 
	}
	
	@Override
	public Integer service(Object obj) throws Exception {

		// DB 처리는 DAO에서 처리 - CartAlbumDAO.delete()
		// CartAlbumController - (Execute) - [CartAlbumDeleteService] - CartAlbumDAO.delete()
		return dao.delete((String)obj);
	}

	

}
