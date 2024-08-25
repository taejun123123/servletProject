package com.musaic.music.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;

public class MusicTopListService implements Service {

   private MusicDAO dao;
   
   // dao setter
   public void setDAO(DAO dao) {  
      this.dao = (MusicDAO) dao;
   }
   
   @Override
   public List<MusicVO> service(Object obj) throws Exception {
	   if (dao == null) {
		      throw new IllegalStateException("DAO has not been initialized.");
		   }
	   
	  System.out.println("MusicTopListService.getMusicSortedByHit");
      // DB 처리는 DAO에서 처리 - ImageDAO.list()
      // ImageController - (Execute) - [ImageListService] - ImageDAO.list()
      return dao.getMusicSortedByHit();                   
   }

}
