<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Family|Pet</title>
<link rel="icon" href="resources/images/logo/favicon.ico" type="image/x-icon">
<style>
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

.tab button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
	font-size: 17px;
}

.tab button:hover {
	background-color: #ddd;
}

.tab button.active {
	background-color: #ccc;
}

.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function check(){
	var member_email = $('input[name=member_email_1]').val() +"@"+ $('select[name=member_email_2]').val();
	$('input[name=member_email]').attr('value',member_email);
}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
	<h3>계정정보 찾기</h3>

	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'findid')" id="defaultOpen">아이디찾기</button>
		<button class="tablinks" onclick="openTab(event, 'findpw')">비밀번호 찾기</button>
	</div>

	<div id="findid" class="tabcontent">
		<form action="semi.do" method="post">
			<input type="hidden" name="command" value="findidres" />

			<h1>아이디 찾기</h1>
			<p>회원 정보에 등록된 이메일과 입력한 이메일이 같아야 아이디를 찾을 수 있습니다.</p>
			<table>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="member_name" required="required">
					</td>
				</tr>

				<tr>
					<th>이메일</th>
					<td>
						<input type="hidden" name="member_email" value=""/>
						<input type="text" name="member_email_1" maxlength="30">
						@
						<select name="member_email_2">
							<option>naver.com</option>
							<option>daum.net</option>
							<option>gmail.com</option>
							<option>nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="조회하기" onclick="check();"/>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<div id="findpw" class="tabcontent">
		<form action="semi.do" method="post">
			<h1>비밀번호 찾기</h1>
			<p>아이디를 입력 후, 본인확인을 통해 비밀번호를 다시 설정할 수 있습니다.</p>
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="member_id" required="required">
					</td>
				</tr>

				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="member_email" maxlength="30" onclick="">
						@
						<select name="member_email_2">
							<option>naver.com</option>
							<option>daum.net</option>
							<option>gmail.com</option>
							<option>nate.com</option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<button type="button" id="searchpw" onclick="location.href='findpwres.jsp'">본인확인</button>
					</td>
				</tr>

			</table>
		</form>

	
	</div>
<jsp:include page="bottom.jsp" />
	<script>
		function openTab(evt, tabName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(tabName).style.display = "block";
			evt.currentTarget.className += " active";
		}

		document.getElementById("defaultOpen").click();
	</script>
</body>
</html>