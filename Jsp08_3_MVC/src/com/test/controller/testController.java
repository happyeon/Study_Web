package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.biz.testBiz;
import com.test.biz.testBizImpl;
import com.test.dto.testDto;


@WebServlet("/testController")
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		testBiz biz = new testBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("["+ command +"]");
		
		if(command.equals("list")) {
			// 1.
			// 2.
			List<testDto> list = biz.selectList();
			// 3.
			request.setAttribute("list", list);
			// 4.
			dispatch(request, response, "testboardlist.jsp");
		} else if (command.equals("insertform")) {
			response.sendRedirect("testboardinsert.jsp");
		} else if (command.equals("insertres")) {
			// 1.
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// 2.
			testDto dto = new testDto(0, writer, title, content, null);
			int res = biz.insert(dto);
			// 3.
			// 4.
			if (res > 0) {
				jsResponse(response, "글 작성 성공", "test.do?command=list");
			} else {
				jsResponse(response, "글 작성 실패", "test.do?command=insertform");
			}
		} else if (command.equals("select")) {
			// 1.
			int seq = Integer.parseInt(request.getParameter("seq"));
			// 2.
			testDto dto = biz.selectOne(seq);
			// 3.
			request.setAttribute("dto", dto);
			// 4.
			dispatch(request, response, "testboardselect.jsp");
		} else if (command.equals("updateform")) {
			// 1.
			int seq = Integer.parseInt(request.getParameter("seq"));
			// 2.
			testDto dto = biz.selectOne(seq);
			// 3.
			request.setAttribute("dto", dto);
			// 4.
			dispatch(request, response, "testboardupdate.jsp");
		} else if (command.equals("updateres")) {
			// 1. 
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// 2.
			testDto dto = new testDto(seq, null, title, content, null);
			int res = biz.update(dto);
			// 3.
			// 4.
			if (res > 0) {
				jsResponse(response, "글 수정 성공", "test.do?command=select&seq="+seq);
			} else {
				jsResponse(response, "글 수정 실패", "test.do?command=updateform&seq="+seq);
			}
		} else if (command.equals("delete")) {
			// 1.
			int seq = Integer.parseInt(request.getParameter("seq"));
			// 2.
			int res = biz.delete(seq);
			// 3
			// 4.
			if (res > 0) {
				jsResponse(response, "글 삭제 성공", "test.do?command=list");
			} else {
				jsResponse(response, "글 삭제 실패", "test.do?command=select&seq=" +seq);
			}
		}
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>"
				+ "alert('" + msg + "');"
				+ "location.href='" + url + "';"
				+ "</script>";
		response.getWriter().print(responseText);
	}

}
