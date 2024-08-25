package com.musaic.member.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.member.db.LoginVO;
import com.musaic.member.db.MemberDAO;

public class MemberLoginService implements Service {
	MemberDAO dao=null;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
	}

	@Override
	public LoginVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		LoginVO vo=(LoginVO) obj;
		
		return dao.login(vo);
	}

}