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

	<h1>RESULT</h1>
	
	page : <%=pageContext.getAttribute("pageScope") %>
	<br/>
	request : <%=request.getAttribute("requestScope") %>
	<br/>
	session : <%=session.getAttribute("sessionScope") %>
	<br/>
	application : <%=application.getAttribute("applicationScope") %>
	<br/>
<%
	String requestVal = request.getParameter("requestVal");
%>

	requestVal : <%=requestVal %>

</body>
</html>