package Data;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Business.Order;
import Business.OrderItem;

public class JDBCOrderDAO implements OrderDAO {

	private Connection connection;
	private ResultSet rs = null;

	public JDBCOrderDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public void close() {
		try {
			rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("Order_Id"));
		order.setDate(rs.getDate("Order_Date") );
		order.setTotal(rs.getDouble("Order_Total"));
		return order;
	}
	
	private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
		OrderItem item = new OrderItem();
		return item;
	}

	@Override
	public Order getOrder(int id) {
		Order order = null;
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM orders WHERE Order_Id = " + id;
			rs = statement.executeQuery(query);
			
			rs.next();
			order = extractOrderFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

	@Override
	public OrderItem getOrderItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM orders ORDER BY Order_Id";
			rs = statement.executeQuery(query);

			while(rs.next()) {
				orders.add(extractOrderFromResultSet(rs));
			}		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public ArrayList<OrderItem> getOrderItems(int orderId) {
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM orderitems WHERE Order_Id = " + orderId;
			rs = statement.executeQuery(query);

//			while(rs.next()) {
//				items.add(extractOrderFromResultSet(rs));
//			}		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private int chooseNewOrderId() {
		int id = 1;
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT max(Order_Id) FROM orders";
			rs = statement.executeQuery(query);
			rs.next();
			
			if(rs != null) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	@Override
	public int createOrder(Order order) {
		int count = 0;
		try {
			PreparedStatement create = null;
			String sql = "INSERT INTO orders VALUES ( ?, ?, ?)";
			create = connection.prepareStatement(sql);
			create.setInt(1, chooseNewOrderId());
			create.setDate(2, order.getDate());
			create.setDouble(3, order.getTotal());
			count = create.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public void createOrderItem() {
		// TODO Auto-generated method stub

	}




}
