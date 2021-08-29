package com.higradius;

public class Invoice {
	private String name_customer, cust_number, due_in_date,  clear_date, predicted_date, invoice_id, notes;
	private Double total_open_amount;
	private Integer row_number;
	
	public Integer getRowNo() {
		return row_number;
	}
	
	public void setRowNo(Integer row_number) {
		this.row_number = row_number;
	}
	
	public String getCustomerName(){
		return name_customer;
	}
	
	public void setCustomerName(String name_customer) {
		this.name_customer = name_customer;
		return;
	}
	
	public String getCustomerNumber(){
		return cust_number;
	}
	
	public void setCustomerNumber(String cust_number) {
		this.cust_number = cust_number;
		return;
	}
	
	public String getDueDate(){
		return due_in_date;
	}
	
	public void setDueDate(String due_in_date) {
		this.due_in_date = due_in_date;
		return;
	}
	
	public String getClearDate(){
		return clear_date;
	}
	
	public void setClearDate(String clear_date) {
		this.clear_date = clear_date;
		return;
	}
	
	public String getPredictedDate(){
		return predicted_date;
	}
	
	public void setPredictedDate(String predicted_date) {
		this.predicted_date = predicted_date;
		return;
	}
	
	public String getID(){
		return invoice_id;
	}
	
	public void setID(String invoice_id){
		this.invoice_id = invoice_id;
		return;
	}
	
	public String getNotes(){
		return notes;
	}
	
	public void setNotes(String notes){
		this.notes = notes;
		return;
	}
	
	public Double getAmount() {
		return total_open_amount;
	}
	
	public void setAmount(Double total_open_amount) {
		this.total_open_amount = total_open_amount;
		return;
	}
}
