package Presentation;

import javax.swing.*;

import Business.Employee;
import Data.EmployeeDAO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class SearchEmployeeFrame extends JFrame {
	private EmployeeMGMPanel parent;
	private JTextField txtId;
	private EmployeeDAO empDAO = new EmployeeDAO();
	
	public SearchEmployeeFrame(EmployeeMGMPanel panel) {
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSearchEmployeeBy = new JLabel("Search employee by Id");
		lblSearchEmployeeBy.setBounds(10, 27, 166, 15);
		lblSearchEmployeeBy.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblSearchEmployeeBy);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(21, 62, 17, 15);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(43, 58, 116, 25);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(50, 100, 78, 30);
		getContentPane().add(btnSearch);
		parent = panel;
		this.getContentPane().setBackground(new Color(210, 232, 204));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					Employee emp = empDAO.getEmployee(txtId.getText());
					if (emp != null) {
						parent.showEmployee(emp);
						SearchEmployeeFrame.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"Employee is not found","Not Found",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	public boolean isValidData() {
		if (!Validator.isPresent(txtId, "ID")) return false;
		return true;
	}
}
