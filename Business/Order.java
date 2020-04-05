package Business;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import Presentation.OrderPanel;

public class Order {
	
	private static DefaultListModel<OrderItem> orderList = new DefaultListModel<OrderItem>();
	
	
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
