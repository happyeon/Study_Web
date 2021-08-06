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

	<h1>jsp:useBean</h1>
	
	<!-- Score sc = new Score(); 와 같은 뜻/ jsp:useBean : jsp에서 자바 객체 사용할거야 / session scope에 set된다. session scope에 이미 같은 이름(sc)이 있으면 그게 담기고 없으면 새로 담는다.-->
	<!-- 세션 객체에 sc라는 이름이 있는지 없는지 확인한다. 없으면 여기서(com.eljstl.score.Score) 객체 만들어서 sc를 넣어준다.  -->
	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>
	
	<!-- sc.setName("hong"); property의 name의 첫번째글자를 대문자로 바꿔서 set다음에 Name이 나오는 것임! -->
	<jsp:setProperty property="name" name="sc" value="hong" />
	
	<!-- sc.getName(); -->
	<jsp:getProperty property="name" name="sc"/>
	
	<a href="result.jsp">scope</a>

</body>
</html>