package Presentation;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Business.Order;
import Data.DBAccessFactory;
import Data.JDBCOrderDAO;

import javax.swing.JTextArea;

public class OrderReportPanel extends JPanel {
	private JDBCOrderDAO orderDAO = DBAccessFactory.getOrderDAO();
	private JTextArea txtDisplay;
	
	public OrderReportPanel() {
		setLayout(null);
		
		txtDisplay = new JTextArea();
		txtDisplay.setBounds(10, 10, 312, 203);
		add(txtDisplay);
		
		showAllOrders();
	}
	
	private void showAllOrders() {
		String output = "";
		ArrayList<Order> orders = orderDAO.getAllOrders();
		for (Order element:orders) {
			output += element + "\n";
		}
		txtDisplay.setText(output);
	}

}
