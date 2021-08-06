<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- directive tag 지시자 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Test</h1>
	
	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<!-- Jsp Standard Tag Library -->
		<!-- collection 받아서 iterate(반복) 한다. / var: forEach안에서 사용할 변수 명. / for (score : list)와 같은 의미 / list 안에 있는 갯수만큼 반복 -->
		<!-- choose 태그 안에 주석 달면 오류가 생김 -->
		<c:forEach items="${list }" var="score">
			<tr>
				<td>
					<!-- JSTL에서 if는 자바와 달리 한 번으로 끝남. true면 실행 false면 넘어감. / eq : == / ne : != / empty : null -->
					<c:if test="${score.name eq '이름10' }">
						<c:out value="gildong"></c:out>
					</c:if>
					<!-- when, otherwise를 넣어 내가 원하는 조건 여러개를 가져올 수 있다. 자바의 switch와 비슷한 기능-->
					<c:choose>
						<c:when test="${score.name eq '이름20' }">
							<c:out value="${score.name } 님!!"></c:out>
						</c:when>
						<c:when test="${score.name eq '이름30' }">
							<c:out value="${score.name }"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="누구지?"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${score.kor_score }</td>
				<td>
					<c:if test="${score.eng_score > 70 }">
						<c:if test="${score.eng_score == 90 }">
							<c:out value="멋져"></c:out>
						</c:if>
					</c:if>
				</td>
				<td>${score.math_score }</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>
				<td>
					<c:choose>
						<c:when test="${score.grade eq 'A' || score.grade eq 'B' }">
							<c:out value="PASS"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="FAIL"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<hr/>
	
	<c:forEach begin="1" end="30" step="3" var="i">
		${i }<br/>
	</c:forEach>
	
	<hr/>
	
	<h3>구구단</h3>
	<table border="1">
		<c:forEach begin="2" end="9" step="1" var="i">
			<tr>
				<td>${i }단</td>
			</tr>
			<c:forEach begin="1" end="9" step="1" var="j">
				<tr>
					<td>${i }*${j } = ${i*j }</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
	
	

</body>
</html>