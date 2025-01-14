package com.musaic.pay.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.pay.vo.OrderVO;
import com.musaic.pay.vo.PayVO;
import com.musaic.util.exe.Execute;

// Pay Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class PayController {

	public String execute(HttpServletRequest request) {
		System.out.println("PayController.execute() --------------------------");
		// uri
		String uri = request.getRequestURI();
		
		Object result = null;
		Object result2 = null;
		
		String jsp = null;
		
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id =login.getId();
		
		try { // 정상 처리
		
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/pay/itemlist.do":					
				System.out.println("결제 페이지로 이동");
				String [] strCartNos = request.getParameterValues("cartNo");
				
				long[] cartNos = new long[strCartNos.length];
				for(int i=0;i<strCartNos.length;i++) {
					cartNos[i] = Long.parseLong(strCartNos[i]);
				}
				System.out.println(cartNos);
				result = Execute.execute(Init.get(uri),cartNos);
				System.out.println(result);
				request.setAttribute("list", result);
				jsp="pay/payWriteForm";
				break;
			case "/pay/payOrderList.do":
				System.out.println("결제 목록");
				
				result =Execute.execute(Init.get(uri), id);
				request.setAttribute("list", result);
				jsp="pay/payOrderList";
				break;
			case "/pay/payWrite.do":
				System.out.println("1-2. 결제 등록 처리");
				
				// 데이터 수집(사용자->서버 : form - input - name)
				//상품 번호 여러개 받아서 update에준다 
				strCartNos = request.getParameterValues("cartNo");
				cartNos = new long[strCartNos.length];
				for(int i=0;i<strCartNos.length;i++) {
					cartNos[i] = Long.parseLong(strCartNos[i]);
				}
				
				
				Long totalPrice = Long.parseLong(request.getParameter("totalPrices"));
				String address = request.getParameter("address");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String cardType = request.getParameter("cardType");
				String cardBank = request.getParameter("cardBank");
				String cardNum = request.getParameter("cardNum");
				String phonePay = request.getParameter("phonePay");
				String payTel = request.getParameter("payTel");
				String bankType = request.getParameter("bankType");
				String bankNum = request.getParameter("bankNum");
				
				
				
				
				// 변수 - vo 저장하고 Service
				PayVO vo = new PayVO();
				vo.setMemberId(id);
				vo.setAddress(address);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setCartPrice(totalPrice);
				vo.setCartNos(strCartNos);
				
				if(cardType != null) {
				vo.setCardType(cardType);
				vo.setCardBank(cardBank);
				vo.setCardNum(cardNum);
				}
				if(phonePay !=null) {
					vo.setPhonePay(phonePay);
					vo.setPayTel(payTel);
				}
				if(bankNum !=null && !bankNum.equals("")) {
					vo.setBankType(bankType);
					vo.setBankNum(bankNum);
				}
	
				
				// [PayController] - PayWriteService - PayDAO.write(vo)
				Execute.execute(Init.get(uri), vo);
	            // URL 인코딩
	
				
				// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
				// 아니면 jsp로 forward로 시킨다.
				jsp = "redirect:payList.do";
				System.out.println(jsp);
				break;	
			case "/pay/payList.do":
				// [PayController] - (Execute) - PayListService - PayDAO.list()
				System.out.println("2.결제 리스트");
				 vo = new PayVO();
				vo.setMemberId(id);
			
				result = Execute.execute(Init.get(uri),null);
				result2 = Execute.execute(Init.get("/pay/payWriteForm.do"),vo);
				
				request.setAttribute("list", result);
				request.setAttribute("cart", result2);
				
				jsp = "pay/payList";
				break;
			case "/pay/payView.do":
				System.out.println("3.결제 상세 보기");
				// 넘어오는 글번호 수집한다.(request에 들어 있다.)
				long payNo = Long.parseLong(request.getParameter("payNo"));
				vo = new PayVO();
				vo.setMemberId(id);
				vo.setPayNo(payNo);
				result = Execute.execute(Init.get(uri),vo);
				OrderVO vo2 = new OrderVO();
				vo2.setId(id);
				vo2.setNo(payNo);
				
				System.out.println("vo2="+vo2);
				result2 = Execute.execute(Init.get("/pay/orderCartList.do"),vo2);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				request.setAttribute("order", result2);
				jsp = "pay/payView";
				break;
			case "/pay/payUpdate.do":
				System.out.println("4.결제 배송지 수정 처리");
				
				// 데이터 수집(사용자->서버 : form - input - name)
				 address = request.getParameter("address");
				 name = request.getParameter("name");
				 payNo = Long.parseLong(request.getParameter("payNo"));
				 vo = new PayVO();
				vo.setAddress(address);
				vo.setName(name);
				vo.setPayNo(payNo);

				 
				// DB 적용하는 처리문 작성. BoardUpdateservice
				Execute.execute(Init.get(uri), vo);
				jsp = "redirect:payList.do";
				break;
			case "/pay/payDelete.do":
				System.out.println("5.취소 사유 등록");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 비밀번호 - BoardVO
				String cencel = request.getParameter("cencel");
				String status = request.getParameter("status");
				payNo = Long.parseLong(request.getParameter("payNo"));
				PayVO deleteVO = new PayVO();
				deleteVO.setCancel(cencel);
				deleteVO.setStatus(status);
				deleteVO.setPayNo(payNo);
				// DB 처리
				session.setAttribute("msg", "배송이 취소 되었습니다.");
				Execute.execute(Init.get(uri), deleteVO);
				jsp ="redirect:payView.do?payNo="+payNo;
				
				break;
			case "/pay/adminList.do":
				System.out.println("6.관리자 리스트");	
				result = Execute.execute(Init.get(uri),null);
				request.setAttribute("list", result);
				jsp ="pay/adminList";
				break;
			case "/pay/changeStatus.do":
				System.out.println("6-2.관리자 상태 변경");	
				status =request.getParameter("status");
				payNo = Long.parseLong(request.getParameter("payNo"));				
				 vo = new PayVO();
				 vo.setStatus(status);
				 vo.setPayNo(payNo);
				 result = Execute.execute(Init.get(uri),vo);
				 jsp = "redirect:adminList.do";
				break;
			case "/pay/paySatus.do":
				System.out.println("6-3 관리자 라디오 버튼 값 변경");
				String payStatus = request.getParameter("paySatus");
				System.out.println("status="+payStatus);
				 vo = new PayVO();
				 vo.setRadioStatus(payStatus);
				result = Execute.execute(Init.get(uri),vo);
				request.setAttribute("list", result);
				 jsp = "pay/adminList";
				break;
			default:
				jsp = "error/404";
				break;
			} // end of switch
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			//예외객체를 jsp에서 사용하기 위해 request에 담는다
			request.setAttribute("e", e);
			
			jsp = "error/500";
		} // end of try~catch
		return jsp;
	} // end of execute()
	
} // end of class
