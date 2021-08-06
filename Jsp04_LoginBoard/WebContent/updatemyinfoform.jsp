<%@page import="com.login.dto.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	LoginDto dto = (LoginDto) session.getAttribute("dto");
	if (dto == null) {
		pageContext.forward("index.html");
	}
%>

	<h1>UPDATE MYINFO</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updatemyinfo" />
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>" />
		
		<table border="1">
		
		</table>
	
	</form>

</body>
</html>