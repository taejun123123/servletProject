package com.musaic.member.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.member.db.MemberDAO;
import com.musaic.member.db.MemberVO;
import com.webjjang.util.page.PageObject;

public class MemberListService implements Service {
	MemberDAO dao=null;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
	}

	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject po=(PageObject) obj;
		
		po.setTotalRow(dao.getTotalRow());
		
		System.out.println(po.getTotalRow());
		
		return dao.list(po);
	}

}