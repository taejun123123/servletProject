package com.musaic.music.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;

public class MusicUpdateService implements Service {
	private MusicDAO dao;
	
	//dao setter
	public void setDAO(DAO dao) {
		this.dao = (MusicDAO) dao;
	}
	@Override
	public Integer service(Object obj) throws Exception {
		// DB board에서 수정 쿼리 실행해서 데이터 처리
		// imageController - (Execute) - [BoardListService] - BoardDAO.update()
		return dao.update((MusicVO)obj);
	}

}
