package com.musaic.cartalbum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.musaic.cartalbum.vo.CartAlbumVO;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.webjjang.util.page.PageObject;

public class CartAlbumController {

    public String execute(HttpServletRequest request) {
        System.out.println("CartAlbumController.execute() --------------------------");
        
        // URI를 얻어 현재 요청의 경로를 확인
        String uri = request.getRequestURI();
        System.out.println("@@@@@@@@@@" + uri);
        Object result = null;
        String jsp = null;

        // 세션에서 로그인된 사용자 정보를 얻어옴
        HttpSession session = request.getSession();
        LoginVO login = (LoginVO) session.getAttribute("login");
        String id = null;
        if (login != null) {
            id = login.getId(); // 로그인된 사용자의 ID를 가져옴
        }

        try {
            // 요청 URI에 따라 적절한 처리 로직을 실행
            switch (uri) {
                case "/cartalbum/list.do":
                    System.out.println("1. 장바구니 리스트");

                    // 페이지 객체 생성 및 요청 파라미터 설정
                    PageObject pageObject = PageObject.getInstance(request);
                    System.out.println(pageObject);
                    CartAlbumVO vo = new CartAlbumVO();
                    pageObject.setAccepter(id); // 페이지 객체에 로그인된 사용자 ID 설정

                    // Execute 클래스의 execute 메서드를 호출하여 장바구니 목록을 가져옴
                    result = Execute.execute(Init.get(uri), pageObject);
                    
                    // 결과 데이터를 request에 저장하여 JSP에서 사용할 수 있도록 설정
                    request.setAttribute("list", result);
                    request.setAttribute("pageObject", pageObject);
                    
                    // JSP 페이지 설정
                    jsp = "cartalbum/list";
                    break;
                    
                case "/cartalbum/write.do":
                    System.out.println("3. 장바구니 담기 처리");
                    
                    // 요청 파라미터에서 앨범 번호와 수량을 가져옴
                    Long albumNo = Long.parseLong(request.getParameter("albumNo"));
                    id = login.getId();
                    System.out.println("id: " + id);
                    Long albumCnt = Long.parseLong(request.getParameter("albumCnt"));
                    
                    vo = new CartAlbumVO();
                    vo.setAlbumNo(albumNo);
                    vo.setMemberid(id);
                    
                    // 장바구니에 앨범이 이미 존재하는지 체크
                    CartAlbumVO checkNo = (CartAlbumVO) 
                    		Execute.execute(Init.get("/cartalbum/checkalbumno.do"), vo);
                    System.out.println("checkNo=" + checkNo);
                    System.out.println("checkNo.getCartNo()=" + checkNo.getCartNo());
                    // 앨범이 장바구니에 없으면 새로 추가
                    if (checkNo.getCartNo() == null) {
                        vo.setAlbumNo(albumNo);
                        vo.setAlbumCnt(albumCnt);
                        vo.setMemberid(id);
                        Execute.execute(Init.get(uri), vo);
                    } else {
                        // 앨범이 장바구니에 있으면 수량을 증가
                        vo.setCartNo(checkNo.getCartNo());
                        vo.setAlbumCnt(albumCnt);
                        Execute.execute(Init.get("/cartalbum/update.do"), vo); // 장바구니에서 수량을 업데이트
                    }

                    // 장바구니 리스트 페이지로 리다이렉트
                    jsp = "redirect:/cartalbum/list.do";
                    break;

               case "/cartalbum/update.do":
                   System.out.println("4. 장바구니 수정 처리");
                    
                    // 요청 파라미터에서 앨범 수량을 가져옴
                    albumCnt = Long.parseLong(request.getParameter("albumCnt"));
           
                    // CartAlbumVO 객체 생성 및 값 설정
                    vo = new CartAlbumVO();
                    vo.setAlbumCnt(albumCnt);
                    
                    
                   // Execute 클래스의 execute 메서드를 호출하여 장바구니 항목을 업데이트
                    Execute.execute(Init.get(uri), vo);
                    break;
                    
                

                case "/cartalbum/minus.do":
                    System.out.println("4. 장바구니 수량 감소 처리");
                    
                    // 요청 파라미터에서 카트 번호를 가져옴
                    String strNo = request.getParameter("cartNo");
                    Long no = Long.parseLong(strNo);
                    
                    // Execute 클래스의 execute 메서드를 호출하여 장바구니 항목의 수량을 감소
                    result = Execute.execute(Init.get(uri), no);
                    
                    // 장바구니 리스트 페이지로 리다이렉트
                    jsp = "redirect:/cartalbum/list.do";
                    break;

                case "/cartalbum/add.do":
                    System.out.println("4. 장바구니 수량 증가 처리");
                    
                    // 요청 파라미터에서 카트 번호를 가져옴
                    strNo = request.getParameter("cartNo");
                    no = Long.parseLong(strNo);
                    
                    // Execute 클래스의 execute 메서드를 호출하여 장바구니 항목의 수량을 증가
                    result = Execute.execute(Init.get(uri), no);
                    
                    // 장바구니 리스트 페이지로 리다이렉트
                    jsp = "redirect:/cartalbum/list.do";
                    break;

                case "/cartalbum/delete.do":
                    System.out.println("5. 장바구니 삭제 처리");
                    
                    // 삭제할 항목의 카트 번호를 가져옴
                    String passNo = request.getParameter("checkedCartNos");
                    System.out.println("@@@@@@" + passNo);
                    
                    // 카트 번호를 쉼표로 분리하여 배열로 만듦
                    String[] passNoList = passNo.split(",");
                    
                    // 각 카트 번호에 대해 삭제 작업을 수행
                    for (String currentPassNo : passNoList) {
                        result = Execute.execute(Init.get(uri), currentPassNo);
                    }
                    
                    // 장바구니 리스트 페이지로 리다이렉트
                    jsp = "redirect:/cartalbum/list.do";
                    break;

                case "/cartalbum/payclick.do":
                    System.out.println("결제 페이지로 이동");
                    
                    // 결제 페이지로 리다이렉트
                    jsp = "redirect:/pay/payWriteForm.do";
                    break;
                    
                default:
                    // 요청 URI가 정의된 케이스에 없으면 404 에러 페이지로 이동
                    jsp = "error/404";
                    break;
            } // end of switch
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            jsp = "error/500";
            
            // 예외 객체를 JSP에서 사용하기 위해 request에 담음
            request.setAttribute("e", e);
        } // end of try~catch
        
        return jsp; // 결정된 JSP 페이지 반환
    } // end of execute()
} // end of class
