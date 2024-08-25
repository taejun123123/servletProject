package com.musaic.music.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;

public class MusicChangeStatusService implements Service {
	private MusicDAO dao;
	
	//dao setter
	public void setDAO(DAO dao) {
		this.dao = (MusicDAO) dao;
	}
	@Override
	public Integer service(Object obj) throws Exception {
		// DB Image에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - ImageDAO.write()
		// ImageController - (Execute) - [ImageWriteService] - ImageDAO.write()
		return dao.changeStatus((MusicVO)obj);
	}

}
