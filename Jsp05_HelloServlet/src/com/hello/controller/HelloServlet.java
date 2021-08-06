package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* url-pattern과 경로가 같으면 에러남! 절대로 같으면 안됨! 그리고 /는 무조건 있어야 함! 중요중요!! */
@WebServlet("/controller.do")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String initParam;     
    
    public HelloServlet() {
        System.out.println("HelloServlet 생성!");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("HelloServlet init!");
    	
    	initParam = config.getInitParameter("actor");
    	String contextParam = config.getServletContext().getInitParameter("singer");
    	
    	System.out.println("initParam : " + initParam);
    	System.out.println("contextParam : " + contextParam);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* controller 오면 아래 2줄을 가장 먼저 해줘야 함! 중요!!*/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("get 방식으로 들어옴!!");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		/* html이라는 문자열을 응답시켜준다. */
		PrintWriter out = response.getWriter();
		out.print("<h1 style='color: red;'>Hello Servlet</h1>");
		out.print("<h2>계층구조/LifeCycle/url-mapping</h2>");
		out.print("<a href='home.html'>home...</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("post 방식으로 들어옴!!");
		
		String command = request.getParameter("command");
		System.out.println("{"+command+"}");
		
		String result = "<h1 style='color: blue;'>Hello Servlet</h1>"
				      + "<h2>계층구조/LifeCycle/url-mapping</h2>"
				      + "<a href='home.html'>home...</a>";
		response.getWriter().append(result);
	}

	@Override
	public void destroy() {
		System.out.println("HelloServlet destroy!");
	}
	
/*
 * Jsp05_HelloServlet에서 서블릿이 2개 만들어졌다.
 * 정확히는 같은 타입의 서블릿 객체가 2개이다.
 * 여기서 타입은 com.hello.controller 패키지 안에 있는 HelloServlet이라는 class가 타입이다.
 * HelloServlet hello = new HelloServlet();
 * HelloServlet controller = new HelloServlet();
 * 
 * controller/do 에서 요청된 서블릿은 이름이 없다. 
 * 이 서블릿은 post 방식이기 때문에 doPost 메소드로 이동해 그 안에 있는 코드들을 읽어준다.
 * */	
	
}
