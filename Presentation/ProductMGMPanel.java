package Presentation;

import java.util.ArrayList;

import javax.swing.*;

import Business.Product;
import Data.DBAccessFactory;
import Data.JDBCProductDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ProductMGMPanel extends JPanel {
	private JDBCProductDAO productDAO = DBAccessFactory.getProductDAO();
	private DefaultTableModel model;
	private JTable table;
	
	public ProductMGMPanel() {
		setLayout(null);
		
		String[] categories = {"-All Categories-","Milk Tea","Chung Yang Tea","Fresh Milk Tea","Fresh Fruit Tea","Traditional Taste"};
		JComboBox cbCategory = new JComboBox(categories);
		cbCategory.setBounds(600, 10, 120, 21);
		add(cbCategory);
		
		JButton btnShowCategory = new JButton("Show Category");
		btnShowCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String targetCategory = String.valueOf(cbCategory.getSelectedItem());
				model.setRowCount(0);
				if (targetCategory.equalsIgnoreCase("-All Categories-")) {
					showAllProducts();
				}
				else
					showProductsByCategory(targetCategory);
			}
		});
		btnShowCategory.setBounds(600, 41, 120, 30);
		add(btnShowCategory);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateProductFrame().setVisible(true);
			}
		});
		btnCreate.setBounds(600, 172, 120, 30);
		add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateProductFrame().setVisible(true);
			}
		});
		btnUpdate.setBounds(600, 212, 120, 30);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(600, 253, 120, 30);
		add(btnDelete);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product_ID", "Product_Name", "Category", "Price"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		table.getColumnModel().getColumn(1).setPreferredWidth(245);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		table.setBounds(10, 335, 566, 153);
		table.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 12, 566, 352);
		add(scrollPane);
		
		model = (DefaultTableModel) table.getModel();
		showAllProducts();
	}
	
	private void showAllProducts() {
		ArrayList<Product> products = productDAO.getAllProducts();
		for (Product product:products) {
			model.insertRow(model.getRowCount(), new Object[] {product.getID(), product.getName(), product.getCategory(), product.getPrice()});
		}
	}
	
	private void showProductsByCategory(String category) {
		ArrayList<Product> products = productDAO.getAllProducts();
		for (Product product:products) {
			if (product.getCategory().equalsIgnoreCase(category))
				model.insertRow(model.getRowCount(), new Object[] {product.getID(), product.getName(), product.getCategory(), product.getPrice()});
		}
	}

}
