package Presentation;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Business.Order;
import Business.OrderItem;

public class OrderPanel extends JPanel {
	private static JList<OrderItem> jlOrder;
	private static JLabel jlSubTotal = new JLabel("Subtotal:"), jlTotal = new JLabel("Total:"), jlTax = new JLabel("Tax:");
	
	public OrderPanel() {
		jlOrder = new JList<OrderItem>(Order.getList());
		jlOrder.setVisibleRowCount(4);
		JScrollPane scrollPane = new JScrollPane( jlOrder );
		
		jlOrder.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				PropertiesPanel.showItemProperties(jlOrder.getSelectedValue());
			}	
		});
		
		jlSubTotal.setFont(new Font("Tahoma", Font.BOLD,14));
		jlTax.setFont(new Font("Tahoma", Font.BOLD,14));
		jlTotal.setFont(new Font("Tahoma", Font.BOLD,14));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1d;
		c.weighty = 1d;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		scrollPane.setSize(600, 200);
		this.add(scrollPane, c);
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 1;
		c.gridx = 0;
		this.add(jlSubTotal, c);
		c.gridx = 1;
		this.add(jlTax, c);
		c.gridx = 2;
		this.add(jlTotal, c);
	}
	
	public static void setSelected(int index) {
		jlOrder.setSelectedIndex(index);
	}
	public static void setSelectedNone() {
		jlOrder.clearSelection();
	}
	public static void updateCost() {
		double subTotal = 0, tax, total;
		DefaultListModel<OrderItem> orderList;
		orderList = Order.getList();
		for (int i = 0; i < orderList.size(); i++) {
			subTotal += orderList.get(i).getPrice();
		}
		
		System.out.println(subTotal);
		tax = subTotal * 0.13;
		total = subTotal + tax;
		
		jlSubTotal.setText("Subtotal: " + String.valueOf(subTotal) + "$");
		jlTax.setText("Tax: " + tax + "$");
		jlTotal.setText("Total: " + String.valueOf(total) + "$");
	}
	public static boolean isSelection() {
		if (jlOrder.isSelectionEmpty())
			return false;
		return true;
	}


}
