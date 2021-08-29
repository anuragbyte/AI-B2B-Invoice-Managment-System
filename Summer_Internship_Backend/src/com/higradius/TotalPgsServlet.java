package com.higradius;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class TotalPgsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1003425937122522914L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(new Gson().toJson(new SQLDriver().countPages()));		
		res.getWriter().flush();
	}
}
