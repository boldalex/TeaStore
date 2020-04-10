package Data;

import java.util.ArrayList;

import Business.Order;
import Business.OrderItem;

public interface OrderDAO {
	
	public Order getOrder(int id);
	public OrderItem getOrderItem();
	public ArrayList<Order> getAllOrders();
	public ArrayList<OrderItem> getOrderItems(int Order_Id);
	
	public int createOrder(Order order);
	public void createOrderItem();

}
