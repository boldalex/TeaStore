package Data;

import java.util.ArrayList;

import Business.Product;

public interface ProductDAO {
	
	public Product getProduct(int id);
	public ArrayList<Product> getAllProducts();
	public String[] getAllCategories();
	
	public int createProduct(Product product);
	public int updateProduct(Product product);
	public int deleteProduct(Product product);

}
