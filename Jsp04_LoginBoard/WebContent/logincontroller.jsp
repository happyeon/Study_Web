<%@page import="java.util.List"%>
<%@page import="com.login.dto.LoginDto"%>
<%@page import="com.login.dao.LoginDao"%>
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
	System.out.println("["+command+"]");
	
	LoginDao dao = new LoginDao();
	
	if (command.equals("login")) {
		// 1.
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		// 2.
		LoginDto dto = dao.login(myid, mypw);
		
		if (dto != null) {
			// 3.
			// session : 만료되기 전까지 어디서든 사용 가능, login에서 많이 사용함.
			// session scope에 담기
			session.setAttribute("dto", dto);
			// 특정 시간 동안 활동이 없으면 session 만료
			session.setMaxInactiveInterval(10 * 60);
			// 4.
			if(dto.getMyrole().equals("ADMIN")) {
				response.sendRedirect("adminmain.jsp");
			} else if (dto.getMyrole().equals("USER")) {
				response.sendRedirect("usermain.jsp");
			}
		} else {
%>
		<script type="text/javascript">
			alert("login 실패");
			location.href="index.html";
		</script>
<%
		}
	} else if (command.equals("logout")) {
		// session 만료
		session.invalidate();
		response.sendRedirect("index.html");
	} else if (command.equals("userlistall")) {
		// 1.
		// 2.
		List<LoginDto> list = dao.selectAllList();
		// 3.
		request.setAttribute("list", list);
		// 4.
		pageContext.forward("userlistall.jsp");
	} else if (command.equals("userlistenabled")) {
		// 1.
		// 2.
		List<LoginDto> list = dao.selectEnabledList();
		// 3.
		request.setAttribute("list", list);
		// 4.
		pageContext.forward("userlistenabled.jsp");
	} else if (command.equals("updateroleform")) {
		// 1.
		int myno = Integer.parseInt(request.getParameter("myno"));
		// 2.
		LoginDto dto = dao.selectOne(myno);
		// 3.
		request.setAttribute("dto", dto);
		// 4.
		pageContext.forward("updateroleform.jsp");
	} else if (command.equals("updaterole")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		int res = dao.updateRole(myno, myrole);
		if (res > 0) {
%>
		<script type="text/javascript">
			alert("회원등급 조정 성공");
			location.href="logincontroller.jsp?command=userlistenabled";
		</script>
<%
		} else {
%>

		<script type="text/javascript">
			alert("회원등급 조정 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
<%
		}
	} else if (command.equals("registform")) {
		response.sendRedirect("regist.jsp");
	} else if (command.equals("idchk")) {
		String myid = request.getParameter("myid");
		LoginDto dto = dao.idCheck(myid);
		
		boolean idnotused = true;
		if (dto != null) {
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	} else if (command.equals("myinfo")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		LoginDto dto = dao.selectOne(myno);
		
		request.setAttribute("myno", myno);
		
		response.sendRedirect("myinfo.jsp");
	} else if (command.equals("insertuser")) {
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		LoginDto dto = new LoginDto(0, myid, mypw, myname, myaddr, myphone, myemail, null, null);
		int res = dao.insert(dto);
		
		
		if (res > 0) {
%>
		<script type="text/javascript">
			alert("회원가입 성공!");
			location.href="index.html";
		</script>	
		
<% 		
		} else {
%>
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href="regist.jsp";
		</script>
<%
	    }
	} else if (command.equals("lookmyinfo")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		LoginDto dto = new LoginDto();
		int res = dao.update(dto);
		request.setAttribute("myno", myno);
		
		response.sendRedirect("updatemyinfoform.jsp");
	} else if (command.equals("drop")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		request.setAttribute("myno", myno);
		int res = dao.delete(myno);
		if (res > 0) {
%>
		<script type="text/javascript">
			alert("탈퇴 성공");
			location.href="index.html";
		</script>
<%
		} else {
%>

		<script type="text/javascript">
			alert("탈퇴 실패");
			location.href="myinfo.jsp";
		</script>
<%
		}
	} else if (command.equals("updatemyinfo")) {

	}
	
%>


	<h1 style="color: red;">잘못왔다!</h1>
	<p>
		내가 보이면...<br/>
		command 확인<br/>
	</p>

</body>
</html>