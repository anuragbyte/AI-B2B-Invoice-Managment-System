package com.higradius;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLDriver {
	
	public Integer countPages() {
		Connection conn = null ;
		Statement stmt = null ;
		Integer pages = -1;
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			
			String sql = String.format("SELECT COUNT(*) AS total FROM invoices;");
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			Integer countInteger = rs.getInt("total");
			pages = (int) Math.ceil(countInteger/11);
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out. println ( "Goodbye!" );
		
		return pages;
	}
	
	public HashMap<Integer, HashMap<String, String>> getInvoiceList(Integer pageno){
		Connection conn = null ;
		Statement stmt = null ;
		HashMap<Integer, HashMap<String, String>> responseList = new HashMap<Integer, HashMap<String,String>>();
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			String sql = String.format("SELECT * FROM invoices LIMIT 11 OFFSET %s;", (pageno-1) * 11);
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			ArrayList<Invoice> invoiceList = new ArrayList<>();
			
			
			
			while (rs.next()){
				Invoice invoiceObj= new Invoice();
				invoiceObj.setRowNo(rs.getRow());
				invoiceObj.setCustomerName(rs.getString("name_customer"));
				invoiceObj.setCustomerNumber(rs.getString("cust_number"));
				invoiceObj.setID(rs.getString("invoice_id"));
				invoiceObj.setAmount(rs.getDouble("total_open_amount"));
				invoiceObj.setDueDate(rs.getString("due_in_date"));
				invoiceObj.setPredictedDate(rs.getString("predicted_date"));
				invoiceObj.setNotes(rs.getString("notes"));
				invoiceList.add(invoiceObj);
			}
			
			System.out.println(invoiceList.size());
			for(Invoice obj: invoiceList) {
				HashMap<String, String> responseObj = new HashMap<>();
				responseObj.put("Customer Name", obj.getCustomerName());
				responseObj.put("Customer #", obj.getCustomerNumber());
				responseObj.put("Invoice #", obj.getID());
				responseObj.put("Invoice Amount", obj.getAmount().toString());
				responseObj.put("Due Date", obj.getDueDate());
				responseObj.put("Predicted Date", obj.getPredictedDate());
				responseObj.put("Notes", obj.getNotes());
				responseList.put(obj.getRowNo(), responseObj);
			}
			System.out.println(responseList.size());
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println( "Goodbye!" );
		return responseList;
	}
	
	public HashMap<String, String> searchInvoice(String invoice) {
		Connection conn = null ;
		Statement stmt = null ;
		HashMap<String, String> responseObj = new HashMap<>();
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			
			
			String sql = String.format("SELECT * FROM invoices "
					+ "WHERE invoice_id = %s;", invoice);
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			responseObj.put("Customer Name", rs.getString("name_customer"));
			responseObj.put("Customer #", rs.getString("cust_number"));
			responseObj.put("Invoice #", rs.getString("invoice_id"));
			responseObj.put("Invoice Amount", String.valueOf(rs.getDouble("total_open_amount")));
			responseObj.put("Due Date", rs.getString("due_in_date"));
			responseObj.put("Predicted Date", rs.getString("predicted_date"));
			responseObj.put("Notes", rs.getString("notes"));
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out. println ( "Goodbye!" );
		return responseObj;
	}
	
	public void addInvoice(Invoice invoice) {
		Connection conn = null ;
		Statement stmt = null ;
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			String sql = String.format("INSERT INTO invoices (name_customer, cust_number, invoice_id, total_open_amount, due_in_date, notes) "
					+ "VALUES ('%s', '%s', '%s', '%s', '%s', %s);", 
					invoice.getCustomerName(), invoice.getCustomerNumber(), invoice.getID(), 
					invoice.getAmount().toString(), invoice.getDueDate(), invoice.getNotes());
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out. println ( "Goodbye!" );
	}
	
	public void editInvoice(Invoice invoice) {
		Connection conn = null ;
		Statement stmt = null ;
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			
			
			if(invoice.getAmount()!=null) {
				String sql = String.format("UPDATE Invoices "
						+ "SET total_open_amount = '%s', notes = %s "
						+ "WHERE invoice_id = %s;", 
						invoice.getAmount(), invoice.getNotes(), invoice.getID());
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out. println ( "Goodbye!" );
	}
	
	public void deleteInvoice(String[] invoiceList) {
		Connection conn = null ;
		Statement stmt = null ;
		try {
			conn = DatabaseConnection.initializeDatabase();
			stmt = conn.createStatement();
			
			for(String obj: invoiceList) {
				String sql = String.format("DELETE FROM invoices WHERE invoice_id = %s;", obj);
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
		}
		catch (SQLException se){
			se.printStackTrace();
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{
				if (stmt!= null )
					stmt.close();
			} 
			catch (SQLException se2){
				
			} 
			try {
				if (conn!= null )
					conn.close();
			} 
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		System.out. println ( "Goodbye!" );
	}
	
	public static void main(String args[]) {
		Invoice invoiceObj= new Invoice();
		invoiceObj.setCustomerName("KRFT FDS foundation");
		invoiceObj.setCustomerNumber("CCU002");
		invoiceObj.setID("3924575555");
		invoiceObj.setAmount(34632.34);
		invoiceObj.setDueDate("2019-02-24");
		invoiceObj.setNotes(null);
		
		SQLDriver exec = new SQLDriver();
		exec.addInvoice(invoiceObj);
		
		
		invoiceObj.setID("3924575555");
		invoiceObj.setNotes("'Aenean ullamcorper orci et vulputate fermentum.'");
		invoiceObj.setAmount(71552.46);
		
		exec.editInvoice(invoiceObj);
		
		String[] invoiceList = new String[] {"3924575555"};
		exec.deleteInvoice(invoiceList);
	}
}
