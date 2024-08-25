package com.musaic.member.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.member.db.MemberDAO;
import com.musaic.member.db.MemberVO;

public class MemberFindIdService implements Service {
	MemberDAO dao=null;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MemberVO vo=(MemberVO) obj;
		return dao.findId(vo);
	}

}