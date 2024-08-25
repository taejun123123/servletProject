package com.musaic.pay.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.OrderVO;

public class PayOrderCartListService implements Service {

	private PayDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;
	}

	@Override
	public List<OrderVO> service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardController - (Execute) - [BoardListService] - BoardDAO.view()
		
		return dao.orderCartlist((OrderVO) obj);
	}

}
