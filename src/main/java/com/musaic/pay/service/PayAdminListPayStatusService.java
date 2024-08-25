package com.musaic.pay.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.PayVO;
import com.webjjang.util.page.PageObject;

public class PayAdminListPayStatusService implements Service {
	
	private PayDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;
	}

	@Override
	public List<PayVO> service(Object obj) throws Exception {
		
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// PayController - (Execute) - [PayListService] - PayDAO.list()
		return dao.radioChage((PayVO) obj);
	}

}
