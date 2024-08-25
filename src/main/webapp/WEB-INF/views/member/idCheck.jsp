<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty id }">
사용 가능한 아이디입니다.
</c:if>
<c:if test="${!empty id }">
이미 존재하는 아이디입니다. 새로운 아이디를 입력하십시오.
</c:if>