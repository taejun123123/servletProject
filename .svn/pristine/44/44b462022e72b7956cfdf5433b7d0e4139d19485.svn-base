package com.musaic.event.service;

import java.util.List;

import com.musaic.event.dao.EventDAO;
import com.musaic.event.vo.EventVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.webjjang.util.page.PageObject;

public class EventListService implements Service {

	private EventDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (EventDAO) dao;
	}
	
	@Override
	public List<EventVO> service(Object obj) throws Exception {
		
		PageObject pageObject = (PageObject) obj;
		
		// 전체 데이터의 개수
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		// DB 처리는 DAO에서 처리 - EventDAO.list()
		// EventController - (Execute) - [EventListService] - EventDAO.list()
		return dao.list(pageObject);
	}

}
