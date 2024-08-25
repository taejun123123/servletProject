package com.musaic.notice.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.notice.dao.NoticeDAO;
import com.musaic.notice.vo.NoticeVO;
import com.webjjang.util.page.PageObject;


public class NoticeListService implements Service{

	private NoticeDAO dao;
	
	public List<NoticeVO> service(Object obj) throws Exception{
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		System.out.println("NoticeListService.service().dao =" + dao);
		
		return dao.list(pageObject);
	}
	public void setDAO(DAO dao) {
		this.dao = (NoticeDAO) dao;
	}
}
