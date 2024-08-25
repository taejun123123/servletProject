package com.musaic.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.musaic.main.controller.Init;
import com.musaic.member.db.LoginVO;
import com.musaic.util.exe.Execute;

/**
 * Servlet Filter implementation class UpdateConDateFilter
 */
//@WebFilter("/UpdateConDateFilter")
public class UpdateConDateFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public UpdateConDateFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("dofilter UpdateConDateFilter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		//로그인을 한 경우 최근 접속을 수정한다.
		HttpSession session = req.getSession();
		
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id =null;
		
		if(login != null) {
			id = login.getId();
			try {
				Execute.execute(Init.get("/member/updateCondate.do"), id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
