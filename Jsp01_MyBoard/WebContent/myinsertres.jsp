<%@page import="com.my.biz.MyBoardBiz"%>
<%@page import="com.my.dto.MyBoardDto"%>
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
<body>

<%
	String myname = request.getParameter("myname");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	// MyBoardDto dto = new MyBoardDto(0, myname, mytitle, mycontent, null); 이렇게 작성해도 된다.
	MyBoardDto dto = new MyBoardDto();
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	
	MyBoardBiz biz = new MyBoardBiz();
	int res = biz.insert(dto);
	if (res > 0) {
%>

	<script type="text/javascript">
		alert("글 작성 성공");
		location.href="mylist.jsp";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("글 작성 실패");
		history.back();
		// location.href="myinsert.jsp";
	</script>

<%
	}
%>

</body>
</html>