package com.musaic.music.service;

import java.util.List;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.music.dao.MusicDAO;
import com.musaic.music.vo.MusicVO;
import com.webjjang.util.page.PageObject;

public class MusicNewListService implements Service {

   private MusicDAO dao;
   
   // dao setter
   public void setDAO(DAO dao) {
      this.dao = (MusicDAO) dao;
   }
   
   @Override
   public List<MusicVO> service(Object obj) throws Exception {
      
      PageObject pageObject = (PageObject) obj;
      // DB Image에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
      // 전체 데이터의 개수
      pageObject.setTotalRow(dao.getTotalRow(pageObject));	
      // DB 처리는 DAO에서 처리 - ImageDAO.list()
      // ImageController - (Execute) - [ImageListService] - ImageDAO.list()
      return dao.list(pageObject);
   }

}
