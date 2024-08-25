package com.musaic.cartalbum.service;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class CartAlbumAddService implements Service {

	private CartAlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (CartAlbumDAO) dao; 
	}
	@Override
	public Long service(Object obj) throws Exception {
		
		// CartAlbumController - (Execute) - [CartAlbumAddService] - CartAlbumDAO.add()
		return (long) dao.add((Long)obj);
	}

}
