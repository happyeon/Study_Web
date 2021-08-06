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
	
	<jsp:useBean id="dto" class="com.answer.dto.AnswerDto" scope="request"></jsp:useBean>

	<h1>UPDATE</h1>
	
	<form action="answer.do" method="post">
		<input type="hidden" name="command" value="updateres" />
		<input type="hidden" name="boardno" value='<jsp:getProperty property="boardno" name="dto"/>'>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" readonly="readonly" value="<jsp:getProperty property="writer" name="dto"/>" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<jsp:getProperty property="title" name="dto"/>" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"><jsp:getProperty property="content" name="dto"/></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="" /> 
				</td>
			</tr>
		</table>
	</form>

</body>
</html>