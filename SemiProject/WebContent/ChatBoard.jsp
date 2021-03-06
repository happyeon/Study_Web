<%@page import="com.project.fp.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Family|Pet</title>
<link rel="icon" href="resources/images/logo/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
* {
	font-family: ëëęł ë;
}

#messageWindow {
	background: black;
	color: greenyellow;
}

#inputMessage {
	width: 500px;
	height: 20px
}

#btn-submit {
	background: white;
	background: #F7E600;
	width: 60px;
	height: 30px;
	color: #607080;
	border: none;
}

#main_con {
	width: 600px;
	height: 700px;
	border: 1px solid black;
	margin: 10px;
	display: inline-block;
}

#chat_con {
	vertical-align: bottom;
	border: 1px solid black;
	margin: 10px;
	min-height: 600px;
	max-height: 600px;
	overflow: scroll;
	overflow-x: hidden;
	background: #9bbbd4;
}

#bottom-con {
	margin: 10px;
}

.chat {
	font-size: 20px;
	color: black;
	margin: 5px;
	min-height: 20px;
	padding: 5px;
	min-width: 50px;
	text-align: left;
	height: auto;
	word-break: break-all;
	background: #ffffff;
	width: auto;
	display: inline-block;
	border-radius: 10px 10px 10px 10px;
}

.notice {
	color: #607080;
	font-weight: bold;
	border: none;
	text-align: center;
	background-color: #9bbbd4;
	display: block;
}

.my-chat {
	text-align: right;
	background: #F7E600;
	border-radius: 10px 10px 10px 10px;
}

.chat-box {
	text-align: left;
}

.my-chat-box {
	text-align: right;
}
</style>
</head>
<%
MemberDto dto = (MemberDto) session.getAttribute("dto");
int ch_num = (int) request.getAttribute("ch_num");
%>
<body>

	<div id="main_con">
		<div id="chat_con">
			<c:set var="name" value="<%=dto.getMember_nicname() %>"></c:set>
			<c:forEach items="${list }" var ="dto">
				<c:choose>
					<c:when test="${dto.member_nicname eq name}">
						<div class="my-chat-box">
							<div class="chat my-chat">
								${dto.ch_content }
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class='chat-box'>
							<div class="chat">
								${dto.ch_content }
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div id="bottom_con">
			<input id="ch_num" type="hidden" value="<%=ch_num%>">
			<input id="member_nickname" type="hidden" value="<%=dto.getMember_nicname()%>" />
			<input id="inputMessage" type="text" onkeyup="enterkey()" />
			<input type="submit" value="send" onclick="send();" />
			<br/>
			<span>ěë Ľ ě¸ě´</span>
			<select name="source">
				<option value="ko">íęľ­ě´</option>
				<option value="en">ěě´</option>
				<option value="ja">ěźëł¸ě´</option>
				<option value="zh-CN">ě¤ęľ­ě´ ę°ě˛´</option>
				<option value="zh-TW">ě¤ęľ­ě´ ë˛ě˛´</option>
				<option value="vi">ë˛ í¸ë¨ě´</option>
				<option value="id">ě¸ëë¤ěěě´</option>
				<option value="th">íęľ­ě´</option>
				<option value="de">ëěźě´</option>
				<option value="ru">ëŹěěě´</option>
				<option value="es">ě¤íě¸ě´</option>
				<option value="it">ě´íëŚŹěě´</option>
				<option value="fr">íëě¤ě´</option>
			</select>
			<br> <span>ë˛ě­ ě¸ě´</span>
			<select name="target">
				<option value="ko">íęľ­ě´</option>
				<option value="en">ěě´</option>
				<option value="ja">ěźëł¸ě´</option>
				<option value="zh-CN">ě¤ęľ­ě´ ę°ě˛´</option>
				<option value="zh-TW">ě¤ęľ­ě´ ë˛ě˛´</option>
				<option value="vi">ë˛ í¸ë¨ě´</option>
				<option value="id">ě¸ëë¤ěěě´</option>
				<option value="th">íęľ­ě´</option>
				<option value="de">ëěźě´</option>
				<option value="ru">ëŹěěě´</option>
				<option value="es">ě¤íě¸ě´</option>
				<option value="it">ě´íëŚŹěě´</option>
				<option value="fr">íëě¤ě´</option>
			</select>
			<button type="button" onclick="translation();">ë˛ě­</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	var ch_num = document.getElementById('ch_num').value;
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://localhost:8787/SemiProject/ChatServelt/'+ch_num);
	var inputMessage = document.getElementById('inputMessage');
	var member_nickname = document.getElementById('member_nickname');
	

	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};

	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	
	function onMessage(event) {
		var c_msg = event.data;
		var $chat = $("<div class='chat-box'><div class='chat'>" + c_msg + "</div></div>");
		$('#chat_con').append($chat);
	}

	function onOpen(event) {
		alert("ě°ę˛°ěąęłľ");

		
	}

	function onError(event) {
		alert(event.data);
	}

	function send() {
		var msg = inputMessage.value;
		if(msg == ""){
			
		}else{
		var $chat = $("<div class='my-chat-box'><div class='chat my-chat'>"
				+ msg + "</div></div>");
		$('#chat_con').append($chat);
		webSocket.send(msg);
		$.ajax({
			url:"semi.do",
			method:"post",
			data:{command:"chat_insert", member_nickname:member_nickname.value, ch_content:inputMessage.value,ch_num:ch_num},
			dataType:"text",
			success: function(data){
				alert(data);
			},
			error(){
				alert("íľě  ě¤í¨");
			}
		});
		}
		inputMessage.value = "";
	}
	
	function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
	
	function translation() {
		var msg = inputMessage.value;
		var source = $('select[name=source]').val();
		var target = $('select[name=target]').val();
		$.ajax({
			url:"semi.do",
			method:"post",
			data:{command:"translation",msg:msg,source:source,target:target},
			dataType:"text",
			success:function(msg){
				inputMessage.value=msg;
			},
			error(){
				alert("íľě  ě¤í¨");
			}
		});
	}
	window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0);
</script>
</html>