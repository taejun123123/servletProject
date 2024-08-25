package com.musaic.cartalbum.service;

import java.util.List;

import com.musaic.cartalbum.dao.CartAlbumDAO;
import com.musaic.cartalbum.vo.CartAlbumVO;
import com.musaic.main.dao.DAO;
import com.musaic.main.service.Service;
import com.webjjang.util.page.PageObject;

public class CartAlbumListService implements Service {
	
    private CartAlbumDAO dao;  // CartAlbumDAO를 사용하기 위한 변수 선언
    
    // DAO의 setter 메서드
    public void setDAO(DAO dao) {
        this.dao = (CartAlbumDAO) dao; // 전달받은 DAO 객체를 CartAlbumDAO로 형변환하여 설정
    }

    @Override
    public List<CartAlbumVO> service(Object obj) throws Exception {
        // 전달받은 객체를 PageObject로 형변환
        @SuppressWarnings("unused")
		PageObject pageObject = (PageObject) obj;
        
        // 전체 데이터 개수를 가져와서 PageObject에 설정
        pageObject.setTotalRow(dao.getTotalRow(pageObject));
        
        // 디버깅을 위한 출력 - PageObject의 정보 확인
        System.out.println();
        System.out.println("pageobject 정보------------");
        System.out.println(pageObject.getStartRow()); // 시작 행 번호
        System.out.println(pageObject.getEndRow());   // 끝 행 번호
        System.out.println(pageObject.getStartPage()); // 시작 페이지 번호
        System.out.println(pageObject.getTotalPage()); // 총 페이지 수
        System.out.println();
        
        // DB에서 앨범 리스트를 가져와 반환
        return dao.list((PageObject) obj);
    }
}
