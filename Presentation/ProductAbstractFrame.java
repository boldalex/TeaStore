package Presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;

import Business.Product;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ProductAbstractFrame extends JFrame {
	protected JTextField txtName;
	protected JTextField txtPrice;
	protected JComboBox cbCategory;
	public ProductAbstractFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblProductName = new JLabel("Product Name: ");
		lblProductName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProductName.setBounds(24, 26, 98, 24);
		getContentPane().add(lblProductName);
		
		JLabel lblCategory = new JLabel("Category: ");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategory.setBounds(24, 62, 98, 24);
		getContentPane().add(lblCategory);
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(24, 96, 98, 24);
		getContentPane().add(lblPrice);
		
		txtName = new JTextField();
		txtName.setBounds(132, 29, 123, 23);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(132, 99, 123, 24);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		String[] categories = {"Milk Tea","Chung Yang Tea","Fresh Milk Tea","Fresh Fruit Tea","Traditional Taste"};
		cbCategory = new JComboBox(categories);
		cbCategory.setBounds(132, 66, 123, 21);
		getContentPane().add(cbCategory);
		
		this.setLocationRelativeTo(null);
		this.setSize(322,260);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
