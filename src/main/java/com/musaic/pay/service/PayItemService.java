package com.musaic.pay.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.PayVO;

public class PayItemService implements Service {

	private PayDAO dao;
	
	// DAO setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;
	}
	
	@Override
	public List<PayVO> service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - CartAlbumDAO.view()
		return dao.itemlist((long[])obj);
	}
}
