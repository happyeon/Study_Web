<%@page import="com.project.fp.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%
	MemberDto dto = (MemberDto)session.getAttribute("dto");
	String pay_method = (String)request.getAttribute("pay_method");
	String product = (String)request.getAttribute("product");
    String name = dto.getMember_name();
    String email = dto.getMember_email();
    String phone = dto.getMember_phone();
    String address = dto.getMember_addr();
    int totalPrice = (int)request.getAttribute("totalPrice"); 
    int pur = (int)request.getAttribute("pur"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Family|Pet</title>
<link rel="icon" href="resources/images/logo/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function(){
	    IMP.init('imp92199014'); // 가맹점 식별코드
	    var msg;
	    
	    IMP.request_pay({
	        pg : 'inicis',										
	        pay_method : '<%=pay_method%>',						
	        merchant_uid : 'merchant_' + new Date().getTime(),
	        name : '<%=product%>',
	        amount : '<%=totalPrice%>',
	        buyer_email : '<%=email%>',
	        buyer_name : '<%=name%>',
	        buyer_tel : '<%=phone%>',
	        buyer_addr : '<%=address%>',
	        buyer_postcode : '123-456',
	        m_redirect_url : 'https://www.naver.com' 			// 모바일 결제 시, 결제가 끝나고 랜딩되는 url 
	    }, function(rsp) {
	        if ( rsp.success ) {
	            msg = '결제가 완료되었습니다.';
                // msg += '\n고유ID : ' + rsp.imp_uid;
                // msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                msg += '\n결제 금액 : ' + rsp.paid_amount + '원';
                // msg += '\n카드 승인번호 : ' + rsp.apply_num;
	            //성공시 이동할 페이지
	            location.href='semi.do?command=paysuccess&pur='+<%=pur%>;
	        } else {
	            msg = '결제에 실패하였습니다.';
	            msg += '\n에러내용 : ' + rsp.error_msg;
	            //실패시 이동할 페이지
	            location.href='index.jsp';
	        }
	        alert(msg);
	    });
	    
	});

</script>
</head>
<body>
</body>
</html>