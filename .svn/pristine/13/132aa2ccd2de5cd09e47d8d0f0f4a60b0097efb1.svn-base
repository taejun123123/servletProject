<%@page import="com.musaic.playlist.vo.PlaylistVO"%>
<%@page import="com.musaic.util.exe.Execute"%>
<%@page import="com.musaic.playlist.service.PlaylistWriteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("utf-8");
String no = request.getParameter("musicNo");
Long musicNo = Long.parseLong(no);
String image = request.getParameter("image");
System.out.println("musicNo =" + musicNo);
System.out.println("image =" + image);

PlaylistVO vo= new PlaylistVO();
vo.setMusicNo(musicNo);
vo.setImage(image);
Execute.execute(new PlaylistWriteService(), vo);

response.sendRedirect("list.jsp");
%>
