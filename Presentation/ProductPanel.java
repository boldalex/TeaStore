package Presentation;
import java.awt.*;

import javax.swing.*;

import Business.Product;
import Business.ProductList;

public class ProductPanel extends JPanel {
	
	private JTabbedPane tpCategories;	
	private JPanel jpMilkTea, jpChunYang, jpFreshMilk, jpFreshFruit, jpTradTaste;
	private ProductButton[] milkTeaButtons = new ProductButton[4];
	private ProductButton[] chunYangButtons = new ProductButton[4];
	private ProductButton[] freshMilkButtons = new ProductButton[4];
	private ProductButton[] freshFruitButtons = new ProductButton[4];
	private ProductButton[] tradTasteButtons = new ProductButton[7];
	private ProductList list;
	
	public ProductPanel() {
		list = new ProductList();
		tpCategories = new JTabbedPane();
		
		//Creating panel with Milk Tea
		jpMilkTea = new JPanel();
		jpMilkTea.setLayout(new GridLayout(2,2));
		int i = 0;
		for (Product item : list.getListByCategory("Milk Tea")) {
			milkTeaButtons[i] = new ProductButton(item);
			jpMilkTea.add(milkTeaButtons[i]);
			i++;
		}
		
		//Creating panel with Chun Yang Tea		
		jpChunYang = new JPanel();
		jpChunYang.setLayout(new GridLayout(2,2));
		i = 0;
		for (Product item : list.getListByCategory("Chung Yang Tea")) {
			chunYangButtons[i] = new ProductButton(item);
			jpChunYang.add(chunYangButtons[i]);
			i++;
		}
		
		//Creating panel with Fresh Milk Tea		
		jpFreshMilk = new JPanel();
		jpFreshMilk.setLayout(new GridLayout(2,2));
		i = 0;
		for (Product item: list.getListByCategory("Fresh Milk Tea")) {
			freshMilkButtons[i] = new ProductButton(item);
			jpFreshMilk.add(freshMilkButtons[i]);
			i++;
		}
		
		//Creating panel with Fresh Milk Tea		
		jpFreshFruit = new JPanel();
		jpFreshFruit.setLayout(new GridLayout(2,2));
		i = 0;
		for (Product item: list.getListByCategory("Fresh Fruit Tea")) {
			freshFruitButtons[i] = new ProductButton(item);
			jpFreshFruit.add(freshFruitButtons[i]);
			i++;
		}
		
		//Creating panel with Traditional Taste
		jpTradTaste = new JPanel();
		jpTradTaste.setLayout(new GridLayout(5,2));
		i = 0;
		for (Product item: list.getListByCategory("Traditional Taste")) {
			tradTasteButtons[i] = new ProductButton(item);
			jpTradTaste.add(tradTasteButtons[i]);
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
