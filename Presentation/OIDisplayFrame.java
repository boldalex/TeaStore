package Presentation;

import javax.swing.*;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Business.Order;
import Business.OrderItem;
import Data.DBAccessFactory;
import Data.JDBCOrderDAO;

public class OIDisplayFrame extends JFrame {
	private JTable table;
	private DefaultTableModel model;
	private JDBCOrderDAO orderDAO = DBAccessFactory.getOrderDAO();
	private Order order;
	private JTextField txtTotal;
	private JTextField txtDate;
	private JTextField txtId;
	
	public OIDisplayFrame(Order order) {
		this.order = order;
		
		this.setSize(454, 281);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Order details");
		
		getContentPane().setLayout(null);
		
		JLabel lblOrderId = new JLabel("Order Id:");
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrderId.setBounds(10, 23, 62, 22);
		getContentPane().add(lblOrderId);
		
		JLabel lblOrderDate = new JLabel("Order Date:");
		lblOrderDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrderDate.setBounds(137, 23, 62, 22);
		getContentPane().add(lblOrderDate);
		
		JLabel lblOrderTotal = new JLabel("Order Total:");
		lblOrderTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrderTotal.setBounds(291, 23, 72, 22);
		getContentPane().add(lblOrderTotal);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Size", "Ice", "Sugar", "Price"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		table.setBounds(10, 77, 298, 100);
		table.setRowHeight(25);
		model = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 419, 176);
		getContentPane().add(scrollPane);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(373, 23, 56, 25);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(209, 23, 72, 25);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(66, 23, 47, 25); 
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		
		txtId.setText(Integer.toString(order.getOrderId()));
		txtDate.setText(order.getDate().toString());
		txtTotal.setText(Double.toString(order.getTotal()));
		
		showOrderItems();
	}
	
	private void showOrderItems() {
		ArrayList<OrderItem> items = orderDAO.getOrderItems(order.getOrderId());
		for (OrderItem item:items) {
			model.insertRow(model.getRowCount(), new Object[] {item.getProductName(), item.getSize(), item.getIce(),
					item.getSugar(), item.getPrice()});
		}
	}
}
