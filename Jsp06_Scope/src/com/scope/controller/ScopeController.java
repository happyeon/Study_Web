package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScopeController() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestScope = (String) request.getAttribute("requestScope");
		HttpSession session = request.getSession();
		String sessionScope = (String) session.getAttribute("sessionScope");
		ServletContext application = getServletContext();
		String applicationScope = (String) application.getAttribute("applicationScope");
		
		System.out.println("request : " + requestScope);
		System.out.println("session : " + sessionScope);
		System.out.println("application : " + applicationScope);
		
		// PrintWriter out = response.getWriter();
		
		String responseText = "<h1>RESULT</h1>"
				            + "<table border=1>"
				            + "	  <tr>"
				            + "      <th>request</th>"
				            + "      <td>"+requestScope+"</td>"
				            + "   </tr>"
				            + "   <tr>"
				            + "      <th>session</th>"
				            + "      <td>"+sessionScope+"</td>"
				            + "   </tr>"
				            + "   <tr>"
				            + "      <th>application</th>"
				            + "      <td>"+applicationScope+"</td>"
				            + "</tr>"
				            + "</table>";
		// out.print(responseText);
		
		String requestVal = request.getParameter("requestVal");
		System.out.println("requestVal : " + requestVal);
		
		request.setAttribute("requestScope", "request forward value");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
				
		// 1. result를 눌렀을 때 request가 null인 이유?
		//    index의 request에 값을 담아줬는데 result의 request에서 값을 달라고 하고 있기 때문에 
		// 2. servlet을 눌렀을 때 console 창에 출력되는 request가 null인 이유?
		//    1번과 같은 이유
		// 3. servlet을 눌렀을 때 result.jsp의 request가 null이 아닌 이유?
		//    setAttribute하고 forward해줬기 때문
	}

}
