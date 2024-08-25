package com.musaic.event.service;

import com.musaic.event.dao.EventDAO;
import com.musaic.event.vo.EventVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class EventChangeService implements Service {

private EventDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (EventDAO) dao;
	}
	
	
	@Override
	public Integer service(Object obj) throws Exception {
		// DB Image에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - ImageDAO.write()
		// ImageController - (Execute) - [ImageChangeService] - ImageDAO.ChangeImage()
		return dao.changeImage((EventVO)obj);
	}

}
