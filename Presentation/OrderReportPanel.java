package Presentation;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Business.Employee;
import Business.Order;
import Business.Product;
import Data.DBAccessFactory;
import Data.JDBCOrderDAO;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderReportPanel extends JPanel {
	protected JDBCOrderDAO orderDAO = DBAccessFactory.getOrderDAO();
	protected JTable table;
	protected DefaultTableModel model;
	private JTextField txtId;
	
	public OrderReportPanel() {
		setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order_Id", "Order_Date", "SubTotal", "Tax", "Total"
			}
		));
		table.setBounds(10, 10, 324, 223);
		table.setRowHeight(25);
		model = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 12, 419, 405);
		add(scrollPane);
		
		JButton btnShowOrderDetails = new JButton("Show order details");
		btnShowOrderDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int id = (int)table.getValueAt(row, 0);
					Order order = orderDAO.getOrder(id);
					new OIDisplayFrame(order).setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Please, select order to display","Select Order",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnShowOrderDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShowOrderDetails.setBounds(439, 295, 168, 33);
		add(btnShowOrderDetails);
		
		txtId = new JTextField();
		txtId.setBounds(439, 252, 59, 33);
		add(txtId);
		txtId.setColumns(10);
		
		JButton btnSearchById = new JButton("Search by Id");
		btnSearchById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					Order order = orderDAO.getOrder(Integer.parseInt((txtId.getText())));
					if (order != null) {
						model.setRowCount(0);
						showOrder(order);
					}
					else {
						JOptionPane.showMessageDialog(null,"Order is not found","Not Found",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSearchById.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchById.setBounds(508, 251, 99, 33);
		add(btnSearchById);
		
		JButton btnShowAllOrders = new JButton("Show all orders");
		btnShowAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showAllOrders();
			}
		});
		btnShowAllOrders.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShowAllOrders.setBounds(439, 205, 168, 33);
		add(btnShowAllOrders);
		
		showAllOrders();
	}
	
	private void showAllOrders() {
		ArrayList<Order> orders = orderDAO.getAllOrders();
		for (Order order:orders) {
			model.insertRow(model.getRowCount(), new Object[] {order.getOrderId(), order.getDate(), order.getSubTotal(),
					order.getTax(), order.getTotal()});
		}
	}
	
	private void showOrder(Order order) {
		model.insertRow(model.getRowCount(), new Object[] {order.getOrderId(), order.getDate(), order.getSubTotal(),
					order.getTax(), order.getTotal()});
	}
	
	
	private boolean isValidData() {
		if (!Validator.isPresent(txtId, "ID")) return false;
		if (!Validator.isInteger(txtId, "ID")) return false;
		return true;
	}

}
