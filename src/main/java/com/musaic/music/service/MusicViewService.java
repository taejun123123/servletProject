package com.musaic.music.service;

import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;

public class MusicViewService implements Service {
    
    private MusicDAO dao;
    
    // DAO setter
    public void setDAO(DAO dao) {
        this.dao = (MusicDAO) dao;
    }

    @Override
    public MusicVO service(Object obj) throws Exception {
    	return dao.view((Long) obj);
    }
}
