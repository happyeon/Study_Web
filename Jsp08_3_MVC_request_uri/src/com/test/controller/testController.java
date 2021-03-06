package com.test.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.biz.testBiz;
import com.test.biz.testBizImpl;
import com.test.dto.testDto;


@WebServlet(urlPatterns = {"/mylist", "/myselect", "/myinsert", "/myinsertres", "/myupdate", "/mydelete", "/myupdateres"})
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private testBiz biz = new testBizImpl();
	
	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getRequestURI();
		System.out.println("["+command+"]");
		
		if(command.endsWith("/mylist")) {
			doSelectList(request, response);
		} else if (command.endsWith("/myselect")) {
			doSelectOne(request, response);
		} else if (command.endsWith("/myinsert")) {
			doInsert(request, response);
		} else if (command.endsWith("/myinsertres")) {
			doInsertres(request, response);
		} else if (command.endsWith("/myupdate")) {
			doUpdate(request, response);
		} else if (command.endsWith("/mydelete")) {
			doDel(request, response);
		} else if (command.endsWith("/myupdateres")) {
			doUpdateres(request, response);
		}
	}
	
	private void doUpdateres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int myseq = Integer.parseInt(request.getParameter("myseq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		testDto dto = new testDto(myseq, null, title, content, null);
		int res = biz.update(dto);
		if(res > 0) {
			jsResponse(response, "글 수정 성공", "./myselect?myseq="+myseq);
		} else {
			jsResponse(response, "글 수정 실패", "./myupdate?myseq="+myseq);
		}
	}
	
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int myseq = Integer.parseInt(request.getParameter("myseq"));
		testDto dto = biz.selectOne(myseq);
		request.setAttribute("dto", dto);
		dispatch(request, response, "testboardupdate.jsp");
	}
	
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int myseq = Integer.parseInt(request.getParameter("myseq"));
		int res = biz.delete(myseq);
		if(res > 0) {
			jsResponse(response, "글 삭제 성공", "./mylist");
		} else {
			jsResponse(response, "글 삭제 실패", "./myselect?myseq="+myseq);
		}
	}
	
	private void doInsertres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		testDto dto = new testDto(0, writer, title, content, null);
		int res = biz.insert(dto);
		if(res > 0) {
			jsResponse(response, "글 작성 성공", "./mylist");
		} else {
			jsResponse(response, "글 작성 실패", "./myinsert");
		}
	}
	
	private void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response, "testboardinsert.jsp");
	}
	
	private void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int myseq = Integer.parseInt(request.getParameter("myseq"));
		testDto dto = biz.selectOne(myseq);
		request.setAttribute("dto", dto);
		dispatch(request, response, "testboardselect.jsp");
		
	}
	
	private void doSelectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<testDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch(request, response, "testboardlist.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequest(request, response);
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
