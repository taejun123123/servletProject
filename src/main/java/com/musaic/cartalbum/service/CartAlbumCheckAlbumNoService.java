package com.musaic.cartalbum.service;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.cartalbum.vo.CartAlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class CartAlbumCheckAlbumNoService implements Service {

	private CartAlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (CartAlbumDAO) dao; 
	}
	@Override
	public CartAlbumVO service(Object obj) throws Exception {
		
		// CartAlbumController - (Execute) - [CartAlbumCheckAlbumNoService] - CartAlbumDAO.checkalbumNo()
		return dao.checkalbumNo((CartAlbumVO)obj);
	}

}
