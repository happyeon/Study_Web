<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.my.biz.MyBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- html 기반의 JSP 코드 내에 JAVA 코드를 삽입할 수 있게 해주는 태그 -->
<%
	MyBoardBiz biz = new MyBoardBiz();
	List<MyBoardDto> list = biz.selectList();
%>
<!-- list는 배열과 달리 자료를 넣는 만큼 사이즈가 늘어나기 때문에 동적으로 활용하기에 유리하다 -->
<body>

	<h1>LIST</h1>
	
	<table border="1">
		<col width="50px" />
		<col width="100px" />
		<col width="500px" />
		<col width="100px" />
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		
		
	<%
		for (int i = 0; i < list.size(); i++) {
	%>
		
		<tr>
			<!-- get은 List의 데이터를 가져올 때 사용 -->
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<td><a href="myselect.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>
		
		
	<%
		}
	%>
		<tr>
			<!-- colspan : 합칠 셀의 개수 -->
			<td colspan="4" align="right">
				<input type="button" value="글작성" onclick="location.href='myinsert.jsp'" />
			</td>
		</tr>

	</table>

</body>
</html>