<%@page import="com.login.dto.LoginDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function drop() {
		location.href="logincontroller.jsp?command=drop";
	}

</script>

</head>
<body>

<%
	LoginDto dto = (LoginDto) session.getAttribute("dto");
	if (dto == null) {
		pageContext.forward("index.html");
	}
%>

	<h1>MY INFO</h1>

	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="lookmyinfo" />
		<input type="hidden" name="myno" value="<%=dto.getMyno() %>" />
		<table border="1">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>ADDR</th>
				<th>PHONE</th>
				<th>EMAIL</th>
			</tr>

			<tr>
				<td><%=dto.getMyid() %></td>
				<td><%=dto.getMyname() %></td>
				<td><%=dto.getMyaddr() %></td>
				<td><%=dto.getMyphone() %></td>
				<td><%=dto.getMyemail() %></td>
			</tr>
			
		
			<tr>
				<td>
					<input type="submit" value="수정"  />
					<input type="button" value="탈퇴" onclick="drop();"/>
				</td>
			</tr>
			
		</table>

	</form>

</body>
</html>