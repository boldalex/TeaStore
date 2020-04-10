package Business;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import Presentation.OrderPanel;

public class Order {
	
	private static DefaultListModel<OrderItem> orderList = new DefaultListModel<OrderItem>();
	
	private int OrderId;
	private Date date;
	private double total;
	
	public void setId(int id) {
		this.OrderId = id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTotal(double total) {
		this.total = total;
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
	
	public String toString() {
		return OrderId + "\t" + date + "\t" + "\t" + total;
	}
	
	
	public static void addItem(OrderItem item) {
		orderList.addElement(item);
	}
	public static DefaultListModel<OrderItem> getList() {
		return orderList;
	}
	public static void deleteItem(OrderItem item) {
		orderList.removeElement(item);
	}
	public static OrderItem getFirstItem() {
		return orderList.get(0);
	}
	public static void clear() {
		orderList.clear();
		OrderPanel.updateCost();
	}

}
