package com.musaic.playlist.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.playlist.dao.PlaylistDAO;
import com.musaic.playlist.vo.PlaylistVO;

public class PlaylistWriteService implements Service{
	private PlaylistDAO dao;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method 
		this.dao = (PlaylistDAO)dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		PlaylistVO vo = (PlaylistVO) obj;
		dao.musicdelete(vo);
		// TODO Auto-generated method stub
		return dao.wrtie(vo);
	}


}
