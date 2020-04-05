package Presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Business.Order;
import Business.OrderItem;
import Business.Product;

public class ProductButton extends JButton{
	
	private Product product;
	
	//Constructor **once created the button will stay connected to specific product;
	public ProductButton (Product newProduct) {
		product = newProduct;
		this.setText(product.getName());
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderItem order = new OrderItem(product);
				Order.addItem(order);
				OrderPanel.setSelected(order.getID()-1);
				OrderPanel.updateCost();
			}
		});
	}
	

}
