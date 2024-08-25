package com.musaic.pay.vo;

import lombok.Data;

@Data
public class OrderVO {

	// private 변수

	private long no;
	private String id;
	//--------------(장바구니 데이터)----------------------	
	private String cartTitle;  //장바구니 제목
	private String cartImage; //장바구니 이미지
	private int albumCnt; //장바구니 수량
	private Long cartPrice; //총 가격
	private Long price; //가격 원가
	private Long goodsNo; //상품 번호 
	private Long payNoUpdate;
	private Long[] pn; // 상품 번호 배열 
	private String [] cartNos; // 카트 배열
	private long cartNo; //
	private String today; //상품 구매날짜
}
