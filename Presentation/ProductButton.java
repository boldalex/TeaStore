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
				OrderPanel orderPanel = OrderPanel.getOrderPanel();
				OrderItem order = new OrderItem(product);
				orderPanel.getOrder().addItem(order);
				orderPanel.setSelected(order.getID()-1);
				orderPanel.updateCost();
			}
		});
	}
	

}
