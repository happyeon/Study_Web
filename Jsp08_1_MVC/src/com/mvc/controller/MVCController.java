package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;


@WebServlet("/MVCController")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MVCController() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("{" + command + "}");
		
		MVCBiz biz = new MVCBizImpl();
		
		try {
			if (command.equals("list")) {
				// 1.
				// 2.
				List<MVCDto> list = biz.selectList();
				// 3.
				request.setAttribute("list", list);
				// 4.
				dispatch(request, response, "mvclist.jsp");
			} else if (command.equals("insertform")) {
				// 1.
				// 2.
				// 3.
				// 4.
				response.sendRedirect("mvcinsert.jsp");
				
			} else if (command.equals("insertres")) {
				// 1.
				String writer = request.getParameter("writer");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				// 2.
				MVCDto dto = new MVCDto(0, writer, title, content, null);
				int res = biz.insert(dto);
				// 3.
				// 4.
				if (res > 0) {
					dispatch(request, response, "mvc.do?command=list");
					// dispatch 사용하지 않고 list.jsp로 가고 싶을 때
					// request.setAttribute("list", "mvclist.jsp");
					// 이렇게 잘 사용하지 않는 이유는? 커다란 기능 안에 기능이 또 들어가면 안됨 (인서트 기능안에 리스트 기능). 틀린건 아니지만 권장X
				} else {
					dispatch(request, response, "mvc.do?command=insertform");
				}
			} else if (command.equals("select")) {
				// 1.
				int seq = Integer.parseInt(request.getParameter("seq"));
				// 2.
				MVCDto dto = biz.selectOne(seq);
				// 3.
				request.setAttribute("dto", dto);
				// 4.
				dispatch(request, response, "mvcselect.jsp");
			} else if (command.equals("updateform")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				response.sendRedirect("mvcupdate.jsp");
			}
		
		} catch (Exception e) {
			request.setAttribute("error", e);
			dispatch(request, response, "error.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		// requestdispatcher : request를 전달해주는 놈
		// forward or include
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
