package Presentation;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.*;

import Business.Order;
import Data.DBAccessFactory;
import Data.JDBCOrderDAO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class DailyReportPanel extends OrderReportPanel {
	public DailyReportPanel() {
		table.setBounds(11, 37, 417, 154);
		SqlDateModel dateModel = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		datePicker.setSize(161, 30);
		datePicker.setLocation(441, 10);
		datePicker.setBackground(new Color(191, 241, 245));
		 
		add(datePicker);
		
		JButton btnSearchByDay = new JButton("Search by day");
		btnSearchByDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = ((java.sql.Date) datePicker.getModel().getValue()).toString();
				model.setRowCount(0);
				showOrdersByDate(date);
				System.out.println(date);
			}
		});
		btnSearchByDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchByDay.setBounds(441, 50, 161, 33);
		add(btnSearchByDay);
	}
	
	private void showOrdersByDate(String date) {
		ArrayList<Order> orders = orderDAO.getAllOrders();
		for (Order order:orders) {
			if (order.getDate().toString().equals(date))
				model.insertRow(model.getRowCount(), new Object[] {order.getOrderId(), order.getDate(), order.getSubTotal(),
					order.getTax(), order.getTotal()});
		}
		
	}
}
