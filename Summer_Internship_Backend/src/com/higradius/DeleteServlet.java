package com.higradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class DeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 389468656341155812L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		new SQLDriver().deleteInvoice(req.getParameterValues("invlist"));
		
		res.setStatus(200);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(new Gson().toJson("[Status = success]"));		
		res.getWriter().flush();
	}
}
