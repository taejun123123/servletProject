package com.musaic.notice.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.notice.dao.NoticeDAO;
import com.musaic.notice.vo.NoticeVO;


public class NoticeViewService implements Service{

	private NoticeDAO dao;
	
	public NoticeVO service(Object obj) throws Exception{
		
		return dao.view((Long)obj);
	}
	public void setDAO(DAO dao) {
		this.dao = (NoticeDAO) dao;
	}
}
