package com.musaic.eventreply.service;

import java.util.List;

import com.musaic.eventreply.dao.EventReplyDAO;
import com.musaic.eventreply.vo.EventReplyVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.webjjang.util.page.ReplyPageObject;

public class EventReplyListService implements Service {

	private EventReplyDAO dao;

	@Override
	public List<EventReplyVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ReplyPageObject pageObject = (ReplyPageObject) obj;
		
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		return dao.list(pageObject);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (EventReplyDAO) dao;
	}
	
}
