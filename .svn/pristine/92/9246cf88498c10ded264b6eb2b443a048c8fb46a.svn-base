package com.musaic.pay.service;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.musaic.pay.dao.PayDAO;
import com.musaic.pay.vo.PayVO;

public class PayWriteService implements Service {

	private PayDAO dao;
	private CartAlbumDAO dao2 = new CartAlbumDAO();
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (PayDAO) dao;
	}
	@Override
	public Integer service(Object obj) throws Exception {
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		PayVO vo = (PayVO) obj;
		Long no = dao.getNo();
		System.out.println("dao.getNo="+no);
		vo.setPayNoUpdate(no);		
		// PayController - (Execute) - [PayListService] - BoardDAO.list()
		dao.write(vo);
		 dao.payClick(vo);
		 for(String cartNo : vo.getCartNos()) {
		 dao2.delete(cartNo);
		 }
		 return null;
	}

}
