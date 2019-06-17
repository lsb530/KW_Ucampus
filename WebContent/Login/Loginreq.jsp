<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="login" class="boki.Loginservice" scope="session" />
<jsp:setProperty name="login" property="*" />
<%
    //디비 접속
    login.initDB();
    //아이디 비밀번호 체크
    login.CheckUser();
    //디비접속해체
    login.disDB();
%>
<html>
    <head>
        <title>로그인 상태</title>
    </head>
    <body>
        <jsp:getProperty name="login" property="result" />
    <body>
</html>