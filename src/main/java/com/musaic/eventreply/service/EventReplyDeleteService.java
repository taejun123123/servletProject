package com.musaic.eventreply.service;


import com.musaic.eventreply.dao.EventReplyDAO;
import com.musaic.eventreply.vo.EventReplyVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class EventReplyDeleteService implements Service {

	private EventReplyDAO dao;

	@Override
	public Integer service(Object obj) throws Exception {
		
		return dao.delete((EventReplyVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (EventReplyDAO) dao;
	}
	
}
