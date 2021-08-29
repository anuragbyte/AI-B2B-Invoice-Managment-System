package com.higradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class EditServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 514586249626995403L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Invoice invoiceObj = new Invoice();
		if(req.getParameter("amt")==null || req.getParameter("amt").isBlank())
			invoiceObj.setAmount(null);
		else
			invoiceObj.setAmount(Double.parseDouble(req.getParameter("amt")));
		
		if(req.getParameter("notes")==null || req.getParameter("notes").isBlank())
			invoiceObj.setNotes(null);
		else
			invoiceObj.setNotes("'"+req.getParameter("notes")+"'");
		
		invoiceObj.setID(req.getParameter("invno"));
		
		new SQLDriver().editInvoice(invoiceObj);
		res.setStatus(200);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(new Gson().toJson("[Status = success]"));		
		res.getWriter().flush();
		
	}
}
