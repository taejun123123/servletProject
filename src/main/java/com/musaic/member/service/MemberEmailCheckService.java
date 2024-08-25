package com.musaic.member.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.member.db.MemberDAO;

public class MemberEmailCheckService implements Service {
	MemberDAO dao=null;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		String email=(String) obj;
		return dao.emailCheck(email);
	}

}