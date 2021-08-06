<%@page import="com.cal.dto.CalDto"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.controller.Util"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	#calendar{
		border-collapse: collapse;
		border: 1px solid gray;
	}
	
	#calendar th{
		width: 80px;
		border: 1px solid gray;
	}
	
	#calendar td{
		width: 80px;
		height: 80px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	
	a{
		text-decoration: none;
	}
	
	.list > p {
		font-size: 5px;
		margin: 1px;
		background-color: skyblue;
	}
	
	.preview {
		position: absolute;
		top: -30px;
		left: 10px;
		background-color: skyblue;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}

</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	
		$(function(){
			$(".countview").hover(function(){
				
				var countView = $(this);
				var year = $(".y").text().trim();
				var month = $(".m").text().trim();
				var countDate = countView.text().trim();
				
				var yyyyMMdd = year + isTwo(month) + isTwo(countDate);
				
				$.ajax({
					url: "count.do",
					data: "id=kh&yyyyMMdd="+yyyyMMdd,
					type: "post",
					dataType: "json",
					async: false,
					success: function(msg){
						var count = msg.count;
						countView.after("<div class='preview'>"+count+"</div>");
					},
					error: function(){
						alert("통신 실패");
					}
				});
				
			}, function(){
				$(".preview").remove();
			});
		});
		
		function isTwo(n) {
			return (n.length < 2)? "0"+n : n;
		}
	
</script>

</head>
<%
	// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Calendar.html
	// calendar 클래스는 메소드 getInstance를 제공한다. 이 메소드는 calendar 객체를 리턴한다.
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;		// 1월이 0부터 시작
	
	// request -> 화살표를 눌러서 받았다.
	// paramYear -> 받은 값이 있으면
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	if (paramYear != null) {
		year = Integer.parseInt(paramYear);
	}
	
	if (paramMonth != null) {
		month = Integer.parseInt(paramMonth);
	}
	
	if (month > 12) {
		year++;
		month = 1;
	}
	if (month < 1) {
		year--;
		month = 12;
	}
	
	// 날짜 설정하기
	cal.set(year, month-1, 1);
	
	// 해당 년,월의 1일의 요일값
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	// 해당 월의 마지막 날
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	CalDao dao = new CalDao();
	String yyyyMM = year + Util.isTwo(String.valueOf(month));
	List<CalDto> list = dao.calendarViewList("kh", yyyyMM);
	
%>
<body>

	<table id="calendar">
		
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◁</a>
			<a href="calendar.jsp?year=<%=year %>&month=<%=month-1 %>">◀</a>
			
			<span class="y"><%=year %></span>년
			<span class="m"><%=month %></span>월
			
			<a href="calendar.jsp?year=<%=year %>&month=<%=month+1 %>">▶</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▷</a>
		</caption>
		
		
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		
		<tr>
<%
	// 1일 전의 요일 / 왜 i를 0부터 시작해서 굳이 -1까지의 범위로 지정해줬을까?         
	for (int i = 0; i < dayOfWeek-1; i++) {
		out.print("<td></td>");
	}

	for (int i = 1; i <= lastDay; i++) {
%>

			<td>
				<a class="countview" href="cal.do?command=calendarlist&year=<%=year %>&month=<%=month %>&date=<%=i %>" style="color: <%=Util.fontColor(i, dayOfWeek) %>"><%=i %></a>
				
				<a href="insertcalendar.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastDay%>">
					<img alt="일정추가" src="resources/image/pen.png" style="width: 10px; height: 10px;" />
				</a>
				
				<div class="list">
					<%=Util.getCalView(i, list) %>
				</div>
				
			</td>

<%
		if ((dayOfWeek-1+i)%7 == 0) {
			out.print("</tr><tr>");
		}
	}
	
	for (int i = 0; i < (7-(dayOfWeek-1 + lastDay)%7)%7 ; i++) {
		out.print("<td></td>");
	}
%>
		</tr>
	</table>

	

</body>
</html>