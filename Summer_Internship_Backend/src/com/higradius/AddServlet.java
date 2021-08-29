package com.higradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5299420557563766266L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Invoice invoiceObj = new Invoice();
		
		invoiceObj.setCustomerName(req.getParameter("custname"));
		invoiceObj.setCustomerNumber(req.getParameter("custno"));
		invoiceObj.setID(req.getParameter("invno"));
		invoiceObj.setAmount(Double.parseDouble(req.getParameter("amount")));
		invoiceObj.setDueDate(req.getParameter("duedt"));
		if(req.getParameter("notes")==null || req.getParameter("notes").isBlank())
			invoiceObj.setNotes(null);
		else
			invoiceObj.setNotes("'"+req.getParameter("notes")+"'");
				
		new SQLDriver().addInvoice(invoiceObj);
		res.setStatus(200);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(new Gson().toJson("[Status = success]"));		
		res.getWriter().flush();
		
	}
}
