package com.musaic.cartalbum.service;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.cartalbum.vo.CartAlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class CartAlbumUpdateService implements Service {

	private CartAlbumDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (CartAlbumDAO) dao; 
	}
	@Override
	public Integer service(Object obj) throws Exception {
		
		// CartAlbumController - (Execute) - [CartAlbumUpdateService] - CartAlbumDAO.update()
		return dao.update((CartAlbumVO)obj);
	}

}
