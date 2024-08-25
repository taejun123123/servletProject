package com.musaic.playlist.service;

import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.playlist.dao.PlaylistDAO;

public class PlaylistDeleteservice implements Service{
   private PlaylistDAO dao;

   @Override
   public void setDAO(DAO dao) {
      // TODO Auto-generated method stub
      this.dao= (PlaylistDAO) dao;
      
   }

   @Override
   public Object service(Object obj) throws Exception {
      // TODO Auto-generated method stub
      return dao.delete((long[]) obj);
   }

   

}
