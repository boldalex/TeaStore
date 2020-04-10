package Business;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Data.DBAccessFactory;
import Data.JDBCProductDAO;
import Presentation.LogInFrame;

public class ProductList {
	
	private List<Product> list = new ArrayList<Product>();
	
	private static String[] milkTeaNames = { "ChunYang Oolong Milk Tea", "ChunYang Milk Tea", "BiLuo Chun Green Milk Tea", "ChunYang Milk Tea with Pearl" };
	private static String[] chunYangNames = { "ChunYang Oolong Tea", "ChunYang Black Tea", "BiLuo Chun Green Tea", "Mountain Tea" };
	private static String[] freshMilkNames = { "ChunYang Black Fresh Milk Tea", "ChunYang Oolong Fresh Milk Tea", "Brown Sugar Pearl With Fresh Milk Tea", "Red Bean With Fresh Milk Tea" };
	private static String[] freshFruitNames = { "Honey Lemon Oolong Tea", "Lemon Green Tea With Aiyu Jelly", "Grape Fruit Green Tea", "Plum Green Tea" };
	private static String[] tradTasteNames = { "Oolong Tea With Osmanthus Honey", "Lemon Tea With Osmanthus Honey", "Winter Melon Drink", "Smoked Plum Drink", "Smoked Plum Drink With Lemon", "Herbal Tea", "Herbal Black Tea" };
	private static double[] milkTeaPrices = { 5, 5, 5, 5.5 };
	private static double[] freshMilkPrices = { 5.5, 5.5, 6, 6 };
	private static double[] freshFruitPrices = { 5.5, 5.5, 6, 4.75 };
	private static double[] tradTastePrices = { 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5 };
	
	
	
	//Constructor
	public ProductList() {
		for (int i = 0; i< milkTeaNames.length; i++) {
			list.add(new Product( milkTeaNames[i], "Milk Tea", milkTeaPrices[i]));
		}
		for (int i = 0; i< chunYangNames.length; i++) {
			list.add(new Product( chunYangNames[i], "Chung Yang Tea", 5.99));
		}
		for (int i = 0; i< freshMilkNames.length; i++) {
			list.add(new Product( freshMilkNames[i], "Fresh Milk Tea", freshMilkPrices[i]));
		}
		for (int i = 0; i< freshFruitNames.length; i++) {
			list.add(new Product( freshFruitNames[i], "Fresh Fruit Tea", freshFruitPrices[i]));
		}
		for (int i = 0; i< tradTasteNames.length; i++) {
			list.add(new Product( tradTasteNames[i], "Traditional Taste", tradTastePrices[i]));
		}
	}
	
	public List<Product> getListByCategory (String category) {
		List<Product> listByCategory = new ArrayList<Product>();
		for (Product item : list) {
			if (item.getCategory().equals(category))
				listByCategory.add(item);
		}
		return listByCategory;
	}
	
//	public static void main(String[] args) {
//		
//		JDBCProductDAO productDAO = DBAccessFactory.getProductDAO();
//		
//		
//		for (int i = 1; i< milkTeaNames.length; i++) {
//			Product product = new Product( milkTeaNames[i], "Milk Tea", milkTeaPrices[i]);
//			productDAO.createProduct(product);
//		}
//		for (int i = 1; i< chunYangNames.length; i++) {
//			Product product = new Product( chunYangNames[i], "Chung Yang Tea", 5.99);
//			productDAO.createProduct(product);
//		}
//		for (int i = 1; i< freshMilkNames.length; i++) {
//			Product product = new Product( freshMilkNames[i], "Fresh Milk Tea", freshMilkPrices[i]);
//			productDAO.createProduct(product);
//		}
//		for (int i = 1; i< freshFruitNames.length; i++) {
//			Product product = new Product( freshFruitNames[i], "Fresh Fruit Tea", freshFruitPrices[i]);
//			productDAO.createProduct(product);
//		}
//		for (int i = 1; i< tradTasteNames.length; i++) {
//			Product product = new Product( tradTasteNames[i], "Traditional Taste", tradTastePrices[i]);
//			productDAO.createProduct(product);
//		}
//		
//		
//		productDAO.close();
//		
//
//
//	}

}
