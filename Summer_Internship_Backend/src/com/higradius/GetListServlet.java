package com.higradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class GetListServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6525775141701928763L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(new Gson().toJson(new SQLDriver().getInvoiceList(Integer.parseInt(req.getParameter("pageno")))));		
		res.getWriter().flush();
	}
}
