package Presentation;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Business.Product;
import Business.ProductList;
import Data.DBAccessFactory;
import Data.JDBCProductDAO;

public class ProductPanel extends JPanel {
	
	private JTabbedPane tpCategories;	
	private JPanel jpMilkTea, jpChunYang, jpFreshMilk, jpFreshFruit, jpTradTaste;
	private ProductButton[] milkTeaButtons = new ProductButton[4];
	private ProductButton[] chunYangButtons = new ProductButton[4];
	private ProductButton[] freshMilkButtons = new ProductButton[4];
	private ProductButton[] freshFruitButtons = new ProductButton[4];
	private ProductButton[] tradTasteButtons = new ProductButton[7];
	private ProductList list;
	private ArrayList<Product> products;
	private JDBCProductDAO productDAO = DBAccessFactory.getProductDAO();
	
	public ProductPanel() {
		products = productDAO.getAllProducts();
		list = new ProductList();
		tpCategories = new JTabbedPane();
		
		//Creating panel with Milk Tea
		jpMilkTea = new JPanel();
		jpMilkTea.setLayout(new GridLayout(2,2));

		
		//Creating panel with Chun Yang Tea		
		jpChunYang = new JPanel();
		jpChunYang.setLayout(new GridLayout(2,2));
		
		//Creating panel with Fresh Milk Tea		
		jpFreshMilk = new JPanel();
		jpFreshMilk.setLayout(new GridLayout(2,2));

		
		//Creating panel with Fresh Milk Tea		
		jpFreshFruit = new JPanel();
		jpFreshFruit.setLayout(new GridLayout(2,2));

		
		//Creating panel with Traditional Taste
		jpTradTaste = new JPanel();
		jpTradTaste.setLayout(new GridLayout(5,2));

		
		for (Product product:products) {
			if (product.getCategory().equals("Milk Tea"))
				jpMilkTea.add(new ProductButton(product));
			else if (product.getCategory().equals("Chung Yang Tea"))
				jpChunYang.add(new ProductButton(product));
			else if (product.getCategory().equals("Fresh Milk Tea"))
				jpFreshMilk.add(new ProductButton(product));
			else if (product.getCategory().equals("Fresh Fruit Tea"))
				jpFreshFruit.add(new ProductButton(product));
			else if (product.getCategory().equals("Traditional Taste"))
				jpTradTaste.add(new ProductButton(product));
				
		}
		
		tpCategories.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tpCategories.setTabPlacement(JTabbedPane.LEFT);
		tpCategories.addTab("Milk Tea", jpMilkTea);
		tpCategories.addTab("Chung Yang Tea", jpChunYang);
		tpCategories.addTab("Fresh Milk Tea", jpFreshMilk);
		tpCategories.addTab("Fresh Fruit Tea", jpFreshFruit);
		tpCategories.addTab("Traditional Taste", jpTradTaste);

		
		System.out.println(jpMilkTea.getSize());
		
		
		this.add(tpCategories);
		this.setVisible(true);
		
	}

}
