package Presentation;

import javax.swing.*;

import Business.Employee;
import Data.EmployeeDAO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSearchEmployeeBy = new JLabel("Search employee by Id");
		lblSearchEmployeeBy.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblSearchEmployeeBy = new GridBagConstraints();
		gbc_lblSearchEmployeeBy.gridwidth = 2;
		gbc_lblSearchEmployeeBy.insets = new Insets(10, 10, 5, 0);
		gbc_lblSearchEmployeeBy.gridx = 0;
		gbc_lblSearchEmployeeBy.gridy = 0;
		getContentPane().add(lblSearchEmployeeBy, gbc_lblSearchEmployeeBy);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 10, 5, 5);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		getContentPane().add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 1;
		getContentPane().add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridwidth = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 2;
		getContentPane().add(btnSearch, gbc_btnSearch);
		parent = panel;
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
