package com.musaic.event.service;

import com.musaic.event.dao.EventDAO;
import com.musaic.event.vo.EventVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class EventUpdateService implements Service {

private EventDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (EventDAO) dao;
	}
	
	
	@Override
	public Integer service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - EventDAO.list()
		// EventController - (Execute) - [EventViewService] - EventDAO.view()
		return dao.update((EventVO) obj);
	}

}
