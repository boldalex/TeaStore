package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Business.Product;

public class JDBCProductDAO implements ProductDAO {
	
	private Connection connection;
	private ResultSet rs = null;
	
	public JDBCProductDAO() {
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
	
	private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt("Product_Id"));
		product.setName(rs.getString("Product_Name"));
		product.setCategory(rs.getString("Category"));
		product.setPrice(rs.getDouble("Price"));
		return product;
	}

	@Override
	public Product getProduct(int id) {
		Product product = null;
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM products WHERE product_Id = " + id;
			rs = statement.executeQuery(query);
			
			rs.next();
			product = extractProductFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM products ORDER BY Product_Id";
			rs = statement.executeQuery(query);

			while(rs.next()) {
				products.add(extractProductFromResultSet(rs));
			}		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
	
	
	private int chooseNewProductId(Product product) {
		int id = 1;
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT max(Product_Id) FROM products WHERE Category = '" + product.getCategory() + "'";
			System.out.println(query);
			rs = statement.executeQuery(query);
			rs.next();
			
			if(rs != null) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id);
		return id;
	}

	@Override
	public int createProduct(Product product) {
		int count = 0;
		try {
			PreparedStatement create = null;
			String sql = "INSERT INTO products VALUES ( ?, ?, ?, ?)";
			create = connection.prepareStatement(sql);
			create.setInt(1, chooseNewProductId(product));
			create.setString(2, product.getName());
			create.setString(3, product.getCategory());
			create.setDouble(4, product.getPrice());
			count = create.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateProduct(Product product) {
		int count = 0;
		PreparedStatement update = null;
		String sql = "UPDATE products SET Product_Name = ?, category = ?, price = ? where Product_Id = ?";
		try {
			update = connection.prepareStatement(sql);
			update.setString(1, product.getName());
			update.setString(2, product.getCategory());
			update.setDouble(3, product.getPrice());
			update.setInt(4, product.getID());
			count = update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteProduct(Product product) {
		int count = 0;
		PreparedStatement update = null;
		String sql = "Delete from programs where id = ?";
		try {
			update = connection.prepareStatement(sql);
			update.setInt(1, product.getID());
			count = update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String[] getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
