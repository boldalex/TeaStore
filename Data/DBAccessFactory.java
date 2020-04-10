package Data;

public class DBAccessFactory {
	
	private static JDBCOrderDAO orderDAO = null;
	private static JDBCProductDAO productDAO = null;
	
	public static JDBCOrderDAO getOrderDAO() {
		if (orderDAO == null) {
			orderDAO = new JDBCOrderDAO();
		}
		return orderDAO;
	}
	
	public static JDBCProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new JDBCProductDAO();
		}
		return productDAO;
	}
	

}
