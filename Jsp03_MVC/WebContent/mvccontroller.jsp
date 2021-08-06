<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBizImpl"%>
<%@page import="com.mvc.biz.MVCBiz"%>
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
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	
	MVCBiz biz = new MVCBizImpl();
	
	if (command.equals("list")) {
		// 1. 전달된 값이 있으면 받아주자.
		
		// 2. 필요하다면, db와 연결하자.
		List<MVCDto> list = biz.selectList();
		
		// 3. 보내줄 값이 있다면, request에 담자.
		request.setAttribute("list", list);
		
		// 4. 보내자.
		pageContext.forward("mvclist.jsp");
	} else if(command.equals("insertform")) {
		// 1. 
	 	// 2.
	 	// 3.
	 	// 4.
	 	response.sendRedirect("mvcinsert.jsp");
	} else if (command.equals("insertres")) {
		// 1.
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 2.
		MVCDto dto = new MVCDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.insert(dto);
		// 3.
		// 4.
		if (res > 0) {
%>
	    	<script type="text/javascript">
				alert("글 작성 성공!");
				location.href="mvccontroller.jsp?command=list";
			</script>
<%			
		} else {
%>
			<script type="text/javascript">
				alert("글 작성 실패!");
				location.href="mvccontroller.jsp?command=insertform";
			</script>	
<%	
		}
	} else if(command.equals("detail")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2.
		MVCDto dto = biz.selectOne(seq);
		// 3. 값을 object 타입으로 받는 이유? object가 제일 큰 범위라서
		request.setAttribute("dto", dto);
		// 4.
		pageContext.forward("mvcselect.jsp");
	} else if(command.equals("updateform")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2.
		MVCDto dto = biz.selectOne(seq);
		// 3.
		request.setAttribute("dto", dto);
		// 4.
		pageContext.forward("mvcupdate.jsp");
	} else if(command.equals("updateres")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 2.
		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);

		if (res > 0) {
			// 3.
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);
			// 4.
			pageContext.forward("mvclist.jsp");
		} else {
			// 3.
			MVCDto updateDto = biz.selectOne(seq);
			request.setAttribute("dto", updateDto);
			// 4.
			pageContext.forward("mvcselect.jsp");
		}
	} else if (command.equals("delete")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2.
		int res = biz.delete(seq);
		// 3.
		// 4.
		if (res > 0) {
%>

		<script type="text/javascript">
			alert("삭제 성공");
			location.href="mvccontroller.jsp?command=list";
		</script>
		
<%
		} else {
%>

	<script type="text/javascript">
		alert("삭제 실패");
		location.href="mvccontroller.jsp?command=detail&seq=<%=seq%>";
	</script>

<%
		}
	}
%>




	<h1>잘못왔다...</h1>

</body>
</html>