package Data;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Business.Order;
import Business.OrderItem;
import Business.Product;

public class JDBCOrderDAO implements OrderDAO {

	private Connection connection;
	private ResultSet rs = null;
	private JDBCProductDAO productDAO = DBAccessFactory.getProductDAO();

	public JDBCOrderDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public void close() {
		try {
			if (rs != null)
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
		order.setSubTotal(rs.getDouble("SubTotal"));
		order.setTax(rs.getDouble("Tax"));
		return order;
	}
	
	private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
		OrderItem item = new OrderItem();
		Product product = productDAO.getProduct(rs.getInt("Product_Id"));
		item.setProduct(product);
		item.setSize(rs.getString("Item_Size"));
		item.setIce(rs.getString("Ice"));
		item.setSugar(rs.getString("Sugar"));
		item.setPrice(rs.getDouble("Price"));
		return item;
	}

	@Override
	public Order getOrder(int id) {
		Order order = null;
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM orders WHERE Order_Id = " + id;
			rs = statement.executeQuery(query);
			if (rs.next() != false) {
				order = extractOrderFromResultSet(rs);
			}
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
			String query = "SELECT * FROM orderitems WHERE Order_Id = '" + orderId + "'";
			rs = statement.executeQuery(query);

			while(rs.next()) {
				items.add(extractOrderItemFromResultSet(rs));
			}		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
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
			int id = chooseNewOrderId();
			PreparedStatement create = null;
			String sql = "INSERT INTO orders VALUES ( ?, ?, ?, ?, ?)";
			create = connection.prepareStatement(sql);
			create.setInt(1, id);
			create.setDate(2, order.getDate());
			create.setDouble(3, order.getTotal());
			create.setDouble(4, order.getSubTotal());
			create.setDouble(5, order.getTax());
			count = create.executeUpdate();
			
			DefaultListModel<OrderItem> orderList = order.getList();
			for (int i = 0; i < orderList.size(); i++) {
				count += createOrderItem(orderList.get(i), id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public int createOrderItem(OrderItem item, int orderId) {
		int count = 0;
		try {
			PreparedStatement create = null;
			String sql = "INSERT INTO orderitems VALUES ( ?, ?, ?, ?, ?, ?)";
			create = connection.prepareStatement(sql);
			create.setInt(1, orderId);
			create.setInt(2, item.getProductId());
			create.setString(3, item.getSize());
			create.setString(4, item.getIce());
			create.setString(5, item.getSugar());
			create.setDouble(6, item.getPrice());
			count = create.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}




}
