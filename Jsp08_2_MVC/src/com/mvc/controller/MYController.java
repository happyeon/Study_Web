package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MYBiz;
import com.mvc.biz.MYBizImpl;
import com.mvc.dto.MYDto;


@WebServlet("/MYController")
public class MYController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MYBiz biz = new MYBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("{" + command + "}");
		
		if (command.equals("list")) {
			// 1.
			// 2.
			List<MYDto> list = biz.selectList();
			// 3.
			request.setAttribute("list", list);
			// 4.
			dispatch(request, response, "myboardlist.jsp");
		} else if (command.equals("insertform")) {
			// 1.
			// 2.
			// 3.
			// 4.
			response.sendRedirect("myboardinsert.jsp");
		} else if (command.equals("insertres")) {
			// 1.
			String myname = request.getParameter("myname");
			String mytitle = request.getParameter("mytitle");
			String mycontent = request.getParameter("mycontent");
			// 2.
			MYDto dto = new MYDto(0, myname, mytitle, mycontent, null);
			int res = biz.insert(dto);
			// 3.
			// 4.
			if (res > 0 ) {
				jsResponse(response, "글 작성 성공", "mymvc.do?command=list");
			} else {
				jsResponse(response, "글 작성 실패", "mymvc.do?command=insertform");
			}
		} else if (command.equals("select")) {
			// 1.
			int myseq = Integer.parseInt(request.getParameter("myseq"));
			// 2.
			MYDto dto = biz.selectOne(myseq);
			// 3.
			request.setAttribute("dto", dto);
			// 4.
			dispatch(request, response, "myboardselect.jsp");
		} else if (command.equals("updateform")) {
			// 1.
			int myseq = Integer.parseInt(request.getParameter("myseq"));
			// 2.
			MYDto dto = biz.selectOne(myseq);
			// 3.
			request.setAttribute("dto", dto);
			// 4.
			dispatch(request, response, "myboardupdate.jsp");
		} else if (command.equals("updateres")) {
			// 1.
			int myseq = Integer.parseInt(request.getParameter("myseq"));
			String mytitle = request.getParameter("mytitle");
			String mycontent = request.getParameter("mycontent");
			// 2.
			MYDto dto = new MYDto(myseq, null, mytitle, mycontent, null);
			int res = biz.update(dto);
			// 3.
			// 4.
			if (res > 0) {
				jsResponse(response, "글 수정 성공", "mymvc.do?command=select&myseq="+myseq);
			} else {
				jsResponse(response, "글 수정 실패", "mymvc.do?command=updateform&myseq="+myseq);
			}
			
		} else if (command.equals("delete")) {
			// 1.
			int myseq = Integer.parseInt(request.getParameter("myseq"));
			// 2.
			int res = biz.delete(myseq);
			// 3.
			// 4.
			if (res > 0) {
				jsResponse(response, "글 삭제 성공", "mymvc.do?command=list");
			} else {
				jsResponse(response, "글 삭제 실패", "mymvc.do?command=select&myseq=" + myseq);
			}
		}

	}
	
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		// requestdispatcher : request를 전달해주는 놈
		// forward or include
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
