package com.musaic.member.controller;

import java.awt.Point;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.member.db.MemberVO;
import com.musaic.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;

public class MemberController {
	public String execute(HttpServletRequest request) {
		String uri=request.getRequestURI();
		
		String jsp=null;
		Object result=null;
		String id=null;
		
		HttpSession session=request.getSession();
		LoginVO login=(LoginVO) session.getAttribute("login");
		if(login!=null) id=login.getId();
		
		String imgpath="/upload/member";
		String realimgpath=request.getServletContext().getRealPath(imgpath);
		
		System.out.println();
		try {
			switch(uri) {
			case "/member/list.do":
				System.out.println("======== Member Controller list ========");
				//Execute-Service-DAO
				PageObject po=PageObject.getInstance(request);
				result=Execute.execute(Init.get(uri), po);
				//실행 결과 request 속성으로 담기
				request.setAttribute("list", result);
				request.setAttribute("po", po);
				jsp="member/list";
				break;
			case "/member/writeform.do":
				System.out.println("======== Member Controller write form ========");
				jsp="member/writeform";
				break;
			case "/member/write.do":
				System.out.println("======== Member Controller write ========");
				//데이터 수집
				id=request.getParameter("id");
				String photo="/upload/member/icon.png";
				String pw=request.getParameter("pw");
				String name=request.getParameter("name");
				String gender=request.getParameter("gender");
				String birth=request.getParameter("birth");

				String[] emails=request.getParameterValues("email");
				String email="";
				if(emails!=null) {
					for(String e:emails)
						email+=e;
				}
				
				String[] adrs=request.getParameterValues("address");
				String address="";
				if(adrs!=null) {
					for(String a:adrs) {
						address+=a;
						address+=" ";
					}
				}
				
				String[] tels=request.getParameterValues("tel");
				String tel="";
				if(tels!=null) {
					for(String t:tels) {
						if(t.equals("")) {
							tel="";
							break;
						}else
							tel+=t;
					}
				}
				
				//vo 데이터 세팅
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setGender(gender);
				vo.setBirth(birth);
				vo.setTel(tel);
				vo.setPhoto(photo);
				vo.setEmail(email);
				vo.setAddress(address);
				
				//execute
				Execute.execute(Init.get(uri), vo);
				
				jsp="redirect:/main/main.do";
				break;
			case "/member/loginform.do":
				System.out.println("======== Member Controller login form ========");
				jsp="member/loginform";
				break;
			case "/member/login.do":
				System.out.println("======== Member Controller login ========");
				
				id=request.getParameter("id");
				pw=request.getParameter("pw");
				
				login=new LoginVO();
				login.setId(id);
				login.setPw(pw);
				
				LoginVO loginvo=new LoginVO();
				loginvo=(LoginVO) Execute.execute(Init.get(uri), login);
				
				if(loginvo==null) {
					session.setAttribute("loginfailed", true);
					jsp="redirect:/member/loginform.do";
					break;
				}else{
					session.setAttribute("login", loginvo);
					jsp="redirect:/main/main.do";
					break;
				}
			case "/member/logout.do":
				System.out.println("======== Member Controller logout ========");
				session.removeAttribute("login");
				jsp="redirect:/main/main.do";
				break;
			case "/member/view.do":
				System.out.println("======== Member Controller view ========");
				
				id=request.getParameter("id");
				
				//일반회원은 id로 접속하고, admin은 no로 접속해야 하는데 미친ㅠ
				vo=(MemberVO) Execute.execute(Init.get(uri),id);
				
				request.setAttribute("login", login);
				request.setAttribute("vo", vo);
				
				jsp="member/view";
				break;
			case "/member/updateform.do":
				System.out.println("======== Member Controller update form ========");
				
				vo=new MemberVO();
				id=request.getParameter("id");
				vo=(MemberVO) Execute.execute(Init.get("/member/view.do"), id);
				
				request.setAttribute("vo", vo);
				
				jsp="member/updateform";
				break;
			case "/member/update.do":
				System.out.println("======== Member Controller update ========");
				//데이터 수집
				
				MultipartRequest multi=new MultipartRequest(request, realimgpath, 100*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
				
				vo=new MemberVO();
				vo=(MemberVO) Execute.execute(Init.get("/member/view.do"), id);
				String originphoto=vo.getPhoto();
				
				id=multi.getParameter("id");
				name=multi.getParameter("name");
				gender=multi.getParameter("gender");
				birth=multi.getParameter("birth");
				address=multi.getParameter("address");
				photo=multi.getFilesystemName("photo");
				System.out.println("photo file name: "+photo);
				emails=multi.getParameterValues("email");
				email="";
				if(emails!=null) {
					for(String e:emails)
						email+=e;
				}
				
				tels=multi.getParameterValues("tel");
				tel="";
				if(tels!=null) {
					for(String t:tels)
						tel+=t;
				}
				System.out.println("tel: "+tel);
				
				//vo 데이터 세팅
				vo=new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setGender(gender);
				vo.setBirth(birth);
				if(photo==null) vo.setPhoto(originphoto);
				else vo.setPhoto(imgpath+"/"+photo);
				vo.setTel(tel);
				vo.setEmail(email);
				vo.setAddress(address);
				
				//execute
				Execute.execute(Init.get(uri), vo);
				
				jsp="redirect:view.do?id="+id;
				break;
			case "/member/pwform.do":
				System.out.println("======== Member Controller pw form ========");
				//입력한 정보가 적절한지 판단해야 함. 적절하면 pwform, 적절하지 않으면 
				if(id!=null) request.setAttribute("id", id);
				jsp="member/pwform";
				break;
			case "/member/changepw.do":
				System.out.println("======== Member Controller change pw ========");
				
				if(id==null)
					id=request.getParameter("id");
				pw=request.getParameter("pw");
				
				vo=new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				
				Execute.execute(Init.get(uri), vo);
				
				if(login==null) {
					jsp="member/loginform";
				}else {
					jsp="redirect:/member/updateform.do?id="+id;					
				}
				break;			
			case "/member/idform.do":
				System.out.println("======== Member Controller id form ========");
				jsp="member/idform";
				break;
			case "/member/delete.do":
				System.out.println("======== Member Controller delete ========");
				id=request.getParameter("id");
				Execute.execute(Init.get(uri), id);
				
				session.removeAttribute("login");
				jsp="redirect:/main/main.do";
				break;
			case "/member/changeGrade.do":
				System.out.println("======== Member Controller change grade ========");
				
				id=request.getParameter("id");
				Long gradeNo=Long.parseLong(request.getParameter("gradeno"));
				
				po=PageObject.getInstance(request);
				
				vo=new MemberVO();
				vo.setId(id);
				vo.setGradeNo(gradeNo);
				
				Execute.execute(Init.get(uri), vo);
				
				jsp="redirect:/member/list.do?"+po.getPageQuery();
				break;			
			case "/member/changeStatus.do":
				System.out.println("======== Member Controller change status ========");
				
				id=request.getParameter("id");
				String status=request.getParameter("status");
				
				po=PageObject.getInstance(request);
				
				vo=new MemberVO();
				vo.setId(id);
				vo.setStatus(status);
				
				Execute.execute(Init.get(uri), vo);
				
				jsp="redirect:/member/list.do?"+po.getPageQuery();
				break;			
			}//end of switch
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		return jsp;
	}
}