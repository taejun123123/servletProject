package com.musaic.event.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.event.vo.EventVO;
import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.ReplyPageObject;

public class EventController {

    public String execute(HttpServletRequest request) {
        System.out.println("EventController.execute() --------------------");
        
        String uri = request.getRequestURI();
        Object result = null;
        String jsp = null;
        Long no = 0L;
        Long gradeNo = 0L;
        HttpSession session = request.getSession();
        LoginVO login = (LoginVO) session.getAttribute("login");
        if (login != null) {
            gradeNo = login.getGradeNo();
        }

        // 파일의 상대적인 저장 위치
        String savePath = "/upload/event";

        // 파일 시스템에서는 절대 저장 위치가 필요. - 업로드 시 필요
        String realSavePath = request.getServletContext().getRealPath(savePath);

        // 업로드 파일 용량 제한
        int sizeLimit = 100 * 1024 * 1024;

        // realSavePath 폴더가 존재하지 않으면 만들자.
        File realSavePathFile = new File(realSavePath);
        if (!realSavePathFile.exists()) realSavePathFile.mkdirs();

        try {
            switch (uri) {
                case "/event/list.do":
                    System.out.println("이벤트 리스트");

                    // 페이지 처리를 위한 객체
                    PageObject pageObject = PageObject.getInstance(request);
                    String period = request.getParameter("period");
                    System.out.println("gradeNo: " + gradeNo);
                    System.out.println("period: " + period);
                    if (gradeNo == 9) {
                        if (period == null || period.isEmpty()) {
                            pageObject.setPeriod("all");
                        } else {
                            pageObject.setPeriod(period);
                        }
                    } else if (period == null || period.isEmpty()) {
                        pageObject.setPeriod("pre_old");
                    } else {
                    	pageObject.setPeriod(period);
                    }
                    // 이미지 게시판의 한페이지 이미지의 개수의 기본 값을 6으로 처리하자.
                    String strPerPageNum = request.getParameter("perPageNum");
                    if (strPerPageNum == null) pageObject.setPerPageNum(6);

                    // DB에서 데이터 가져오기
                    result = Execute.execute(Init.get(uri), pageObject);

                    // 가져온 데이터 request에 저장 -> jsp까지 전달된다.
                    request.setAttribute("list", result);
                    request.setAttribute("pageObject", pageObject);
                    jsp = "event/list";

                    break;

                case "/event/view.do":
                    System.out.println("이벤트 상세보기");
                    no = Long.parseLong(request.getParameter("no"));
                    result = Execute.execute(Init.get(uri), no);
                    request.setAttribute("vo", result);

                    // 댓글 페이지 객체
                    ReplyPageObject replyPageObject = ReplyPageObject.getInstance(request);
                    // 가져온 댓글 데이터 request에 담기
                    request.setAttribute("replyList", Execute.execute(Init.get("/eventreply/list.do"), replyPageObject));
                    request.setAttribute("replyPageObject", replyPageObject);

                    jsp = "event/view";

                    break;

                case "/event/writeForm.do":
                    System.out.println("이벤트 등록 폼");
                    jsp = "event/writeForm";
                    break;

                case "/event/write.do":
                    System.out.println("이벤트 등록");

                    // 업로드 파일 처리
                    MultipartRequest multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8");

                    // 폼 데이터 추출
                    String title = multi.getParameter("title");
                    String image = multi.getFilesystemName("imageFile");
                    String content = multi.getParameter("content");
                    String startDate = multi.getParameter("startDate");
                    String endDate = multi.getParameter("endDate");
                    String anocDate = multi.getParameter("anocDate");

                    // EventVO 객체 생성 및 데이터 설정
                    EventVO vo = new EventVO();
                    vo.setTitle(title);
                    vo.setImage(savePath + "/" + image);
                    vo.setContent(content);
                    vo.setStartDate(startDate);
                    vo.setEndDate(endDate);
                    vo.setAnocDate(anocDate);

                    Execute.execute(Init.get(uri), vo);
                    
                    session.setAttribute("msg", "이벤트가 등록되었습니다.");
                    
                    jsp = "redirect:list.do?perPageNum=" 
    						+ multi.getParameter("perPageNum");

                    break;



                case "/event/updateForm.do":
                    System.out.println("4-1.이벤트 수정 폼");

                    no = Long.parseLong(request.getParameter("no"));

                    result = Execute.execute(Init.get("/event/view.do"), no);
                    request.setAttribute("vo", result);

                    jsp = "event/updateForm";

                    break;

                case "/event/update.do":
                    System.out.println("4-2.이벤트 수정 처리");

                    no = Long.parseLong(request.getParameter("no"));
                    title = request.getParameter("title");
                    content = request.getParameter("content");
                    startDate = request.getParameter("startDate");
                    endDate = request.getParameter("endDate");
                    anocDate = request.getParameter("anocDate");

                    vo = new EventVO();
                    vo.setNo(no);
                    vo.setTitle(title);
                    vo.setContent(content);
                    vo.setStartDate(startDate);
                    vo.setEndDate(endDate);
                    vo.setAnocDate(anocDate);

                    Execute.execute(Init.get(uri), vo);

                    session.setAttribute("msg", "이벤트가 수정되었습니다.");

                    pageObject = PageObject.getInstance(request);
                    jsp = "redirect:view.do?no=" + no + "&inc=0" + "&" + pageObject.getPageQuery();
                    break;

                case "/event/delete.do":
                    System.out.println("5. 이벤트 글삭제");
                    no = Long.parseLong(request.getParameter("no"));

                    EventVO deleteVO = new EventVO();
                    deleteVO.setNo(no);

                    Execute.execute(Init.get(uri), deleteVO);
                    System.out.println();
                    System.out.println("***************************");
                    System.out.println("**  " + deleteVO.getNo() + "글이 삭제되었습니다.  **");
                    System.out.println("***************************");

                    // 파일 삭제
                    String deleteImage = request.getParameter("deleteImage");
                    File deleteFile = new File(request.getServletContext().getRealPath(deleteImage));
                    if (deleteFile.exists()) deleteFile.delete();

                    session.setAttribute("msg", "이벤트 글이 삭제 되었습니다.");

                    jsp = "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");

                    break;

                case "/event/changeImage.do":
                    System.out.println("6.이미지 변경 처리");

                    multi = new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

                    no = Long.parseLong(multi.getParameter("no"));
                    String newImageFileName = multi.getFilesystemName("imageFile");
                    String deleteImageFileName = multi.getParameter("deleteImage");

                    vo = new EventVO();
                    vo.setNo(no);
                    vo.setImage(savePath + "/" + newImageFileName);

                    Execute.execute(Init.get(uri), vo);

                    // 지난 이미지 파일이 존재하면 지운다.
                    File oldFile = new File(request.getServletContext().getRealPath(deleteImageFileName));
                    if (oldFile.exists()) oldFile.delete();

                    session.setAttribute("msg", "이미지가 변경 되었습니다.");

                    pageObject = PageObject.getInstance(request);
                    jsp = "redirect:view.do?no=" + no + "&" + pageObject.getPageQuery();
                    break;

                default:
                    jsp = "error/404";
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("e", e);
            jsp = "error/500";
        }

        return jsp;
    } // end of execute()
}
