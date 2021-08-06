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
	
	<!-- session에 sc라는 객체가 있는지 확인한다. 아까 usebean.jsp에서 만들어 줬으니까 그 값을 가져와서 담아준다. -->
	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>
	
	<jsp:getProperty property="name" name="sc"/><br/>
	
	<!-- page scope에 있니? 없어. request scope에 있니? 없어. session scope에 있니? 있어! 그럼 그거 가져와서 출력해줘. -->
	${sc.name }

</body>
</html>