package com.musaic.pay.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.PayVO;

public class PayStatusService implements Service {

	private PayDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB board에서 수정 쿼리 실행해서 데이터 처리
		// PayController - (Execute) - [PayStatusService] - PayDAO.changeStatus()
		return dao.changeStatus((PayVO)obj);
	}

}
