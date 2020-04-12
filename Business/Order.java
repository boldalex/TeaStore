package Business;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import Presentation.OrderPanel;

public class Order {
	
	private DefaultListModel<OrderItem> orderList = new DefaultListModel<OrderItem>();
	
	private int OrderId;
	private Date date;
	private double total, subTotal, tax;
	
	public Order() {
		date = Date.valueOf(LocalDate.now());
	}
	
	public void setId(int id) {
		this.OrderId = id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
	public int getOrderId() {
		return OrderId;
	}
	public Date getDate() {
		return date;
	}
	public double getTotal() {
		return total;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public double getTax() {
		return tax;
	}
	
	
	public String toString() {
		return OrderId + "\t" + date + "\t" + "\t" + total;
	}
	
	
	public void addItem(OrderItem item) {
		orderList.addElement(item);
	}
	public DefaultListModel<OrderItem> getList() {
		return orderList;
	}
	public void deleteItem(OrderItem item) {
		orderList.removeElement(item);
	}
	public OrderItem getFirstItem() {
		return orderList.get(0);
	}
	public void clear() {
		orderList.clear();
		OrderPanel.getOrderPanel().updateCost();
	}

}
