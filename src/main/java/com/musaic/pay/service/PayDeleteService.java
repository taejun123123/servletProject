package com.musaic.pay.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.PayVO;

public class PayDeleteService implements Service {

	private PayDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;		
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - BoardDAO.delete()
		// BoardController - (Execute) - [BoardListService] - BoardDAO.delete()
		return dao.delete((PayVO)obj);
	}

}
