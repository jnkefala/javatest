<%response.setContentType("text/html;charset=UTF-8"); %>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="db.ManageUser"%>
<jsp:useBean id="userBeans" scope="request" class="db.mapping.User" type="db.mapping.User" />
<jsp:setProperty name="userBeans" property="*"/>
<%ManageUser data = new ManageUser();%>
<%=data.updateUserData(userBeans, request.getUserPrincipal().getName())%>