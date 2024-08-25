package com.musaic.notice.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.notice.dao.NoticeDAO;
import com.musaic.notice.vo.NoticeVO;


public class NoticeDeleteService implements Service{

	private NoticeDAO dao;
	
	public Integer service(Object obj) throws Exception{
		
		return dao.delete((Long)obj);
	}
	public void setDAO(DAO dao) {
		this.dao = (NoticeDAO) dao;
	}
}
